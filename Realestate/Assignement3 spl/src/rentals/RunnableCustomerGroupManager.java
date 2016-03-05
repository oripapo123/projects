package rentals;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import repairs.DamageReport;
import assets.Asset;
import assets.CallableSimulateStayInAsset;

public class RunnableCustomerGroupManager implements Runnable{
	private Collection<DamageReport> damageReports;
	private CustomerGroupDetails details;
	private LinkedBlockingQueue<RentalRequest> rentalQueue;
	private RentalRequest requestToDeliver;
	private CallableSimulateStayInAsset customerVacancySimulator;
	private final static Logger LOGGER = Logger.getLogger(RunnableCustomerGroupManager.class.getName());

	/**
	 * run method for runnable customer group manager. while there are still requests at the group, the runnable will
	 * wait for the last request sent to be completed and then send a new request to be fulfilled by the clerks.
	 */
	public void run(){
		//for each group check if there are any requests left to send to queue
		while(details.getRequestsLeft() > 0){
			//draws a new request from the group
			requestToDeliver = details.getRequests(); //TODO:: change request vector in customergroupdetails into queue. // why ?
			LOGGER.log(Level.INFO, "sending request number " + requestToDeliver.getId() + 
					" for " + details.getGroupManager() + " to request queue...\n");
			//adding the request to the queue for clerks to obtain
			rentalQueue.add(requestToDeliver);
			//waiting for the request to be fulfilled by one of the clerks, sleeping 1 second at a time
			while(requestToDeliver.getRequestStatus() != "FULFILLED"){ 
				try {
					//	System.out.println(sdf.format(new Date()) + " " + details.getGroupManager() + " is waiting for request " + requestToDeliver.getId() + " to be fulfilled...");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//TODO:: for no duplicate vacates of two different assets, the group should wait for the customers to get out of the asset in order to send a new request.
			//when the above condition is not met that means that the request IS fulfilled, time to complete it.
			details.RequestCompleted();
			//simulating a stay in asset for the request
			beginCallableStayInAsset(requestToDeliver.getAsset(), requestToDeliver);
			//after simulation is over - TODO:: generate damage report and send to maintenance
			requestToDeliver.getAsset().setStatus("AVAILABLE");
			requestToDeliver.setRequestStatus("COMPLETE");
			LOGGER.log(Level.INFO, " requests left on " + details.getGroupManager() + ": " + details.getRequestsLeft());
		}
		//TODO:: add sleeptime until after all customers leave the asset (think how?) maybe a boolean for which simulation is over for all customers?
		//after all requests are being pushed to the queue - terminate the thread. (end of run())
		LOGGER.log(Level.INFO, details.getGroupManager() + " is out of requests and thus terminated.");
	}

	//simulation of a stay in asset
	private void beginCallableStayInAsset(Asset assetToBeVacated, RentalRequest requestToDeliver) {
		DamageReport dmgReport=new DamageReport(requestToDeliver.getAsset());
		damageReports.add(dmgReport);
		//setting asset status from 'booked' to 'occupied'
		assetToBeVacated.setStatus("OCCUPIED");
		//setting request status from 'fulfilled' to 'inprogress'
		requestToDeliver.setRequestStatus("INPROGRESS");
		for(Customer customer : details.getCustomers()){
			//for each customer - simulate a stay.
			dmgReport.addDamage(customerVacancySimulator.simulate(assetToBeVacated, customer));
		}
		try {
			Thread.sleep(requestToDeliver.getDurationOfStay() * 24000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//TODO: damage variable for each asset to know if asset needs fixing (below 65% -- what is below 65%?? how do you calculate it??)
		//assetToBeVacated.setDamage(damageDone);
	}
	/**
	 * constructor for Runnable Customer Group Manager
	 * @param groupDetails : group details
	 * @param rentalQueue : the shared rental requests queue send requests to
	 * @param damageReports : damage reports
	 */
	public RunnableCustomerGroupManager(CustomerGroupDetails groupDetails, LinkedBlockingQueue<RentalRequest> rentalQueue, Collection<DamageReport> damageReports) {
		details=groupDetails;
		this.rentalQueue = rentalQueue;
		this.damageReports=damageReports;
		customerVacancySimulator = new CallableSimulateStayInAsset();
	}

}
