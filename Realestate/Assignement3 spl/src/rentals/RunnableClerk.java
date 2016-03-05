package rentals;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import assets.Asset;
import assets.Assets;

public class RunnableClerk implements Runnable{
	
	private Assets assets;
	private ClerkDetails details;
	private LinkedBlockingQueue<RentalRequest> rentalQueue;
	private static Integer numRequests; //static so that all clerks share same number of request and influence it. Integer somehow does not work well here. (as an object that 'int' field does not change from different objects...)
	private double shiftTime = 0;
	private static Object lockObject = new Object(); //static so that all clerks will share same lock for synchronization
	private RentalRequest requestToDo;
	private final static Logger LOGGER = Logger.getLogger(RunnableClerk.class.getName());
	/**
	 * run method for RunnableClerk. while there are Rental Requests at the system, the clerk will fetch a request from the
	 * request queue and fulfill it. when the clerk's shift is over he waits to be notified of a new shift from the management.
	 */
	public void run(){
		//checks if there are requests left at all (not on queue - in program cycle)
		while(numRequests > 0){
			//resets the request to do variable
			requestToDo = null;
			//checks if shift is over.
			if(shiftTime>=8){
				LOGGER.log(Level.INFO, details.getName() + " has ended his Shift\n");
				//waits to being notified about all clerks finishing their shifts(from management)
				synchronized(this){
					try {
						wait();
						shiftTime = 0;
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			//starts a new shift
			LOGGER.log(Level.INFO, details.getName() + " Has started his shift.\n");
			//checks whether there are request at the cycle that did not arrive to the queue yet. (queue is empty)
			//if this situation occures, clerk sleeps for a second - then checks again
			while((rentalQueue.isEmpty()) && (numRequests > 0)){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//if the above condition is not met, that means that either there is a request in queue or there are no requests left
			//either way if there is a request in the queue - draw it from the queue SYNCHRONIZED so that no two different clerks
			//find the rental queue not empty (with 1 request only) and try do draw the request.
				synchronized(lockObject){
						if(!rentalQueue.isEmpty())
						requestToDo = drawRequest();
				}
				//if the draw was successful - find and book the asset (by input - findSuitableAsset CANNOT return null.
					if(requestToDo != null){
						LOGGER.log(Level.INFO, details.getName() +" has taken request number " + requestToDo.getId() + "\n");
						Asset desiredAsset = findSuitableAsset(requestToDo); //TODO:: check if priority is for AVAILABLE asset or a SUITABLE asset.
						LOGGER.log(Level.INFO, " " + details.getName() + " Has found an asset!\nAsset that was found is: " + desiredAsset.getName() + "\n");
						//if the asset is not available (occupied\booked\broken) wait for it to be available
						//TODO:: make the maintenance run between occupied -> available so no infinite while will occur
						boolean isBooked = false;
						while((desiredAsset.getStatus().compareTo("AVAILABLE") != 0) && (!isBooked)){
						//	System.out.println(sdf.format(new Date()) + " " + desiredAsset.getName() + " is currently " + desiredAsset.getStatus() + ", waiting for availability...");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							synchronized(desiredAsset){
								//if the asset is still available make it 'booked'
							if(desiredAsset.getStatus().compareTo("AVAILABLE") == 0)
								desiredAsset.setStatus("BOOKED");
								isBooked = true;
							}
						}
						LOGGER.log(Level.INFO, details.getName() + " has booked " + desiredAsset.getName() + "!\n");
						//assign asset to request
						requestToDo.setAsset(desiredAsset);
						//walk to asset
						walk(desiredAsset);
						//after returning set the request to fulfilled
						requestToDo.setRequestStatus("FULFILLED");
						LOGGER.log(Level.INFO, details.getName() +  " has fulfilled request " + requestToDo.getId() + "\n");
					}
		}
		//after no more requests that are not completed are in the system terminate the thread (ending of run())
		LOGGER.log(Level.INFO, details.getName() + " is terminated.");
	}
	
	//simulates walking to the asset - calculates distance and sleeps the distance in 1minute\second
	private void walk(Asset desiredAsset) {
		double walkTime = details.getLocation().calculateDistance(desiredAsset.getLocation());
		try {
			Thread.sleep(((long)(walkTime * 2000)));
			//accumulates shift time with walking time
			shiftTime = shiftTime + walkTime;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//finds a suitable asset from Assets, by size and type.
	private Asset findSuitableAsset(RentalRequest requestToDo) {
			String desiredType = requestToDo.getType();
			int desiredSize = requestToDo.getAssetSize();
			LOGGER.log(Level.INFO, details.getName() + " is looking for asset of type " + desiredType + " and of size " + desiredSize+"\n");
			return assets.findAsset(desiredType, desiredSize);
	}

	//draws request from the queue (maybe no synchronized is needed here because the call to drawRequest is found in synchronized
	//itself. TODO:: check if synchronized is needed.
	private RentalRequest drawRequest() {
		synchronized(lockObject){
			try {
				synchronized(lockObject){
					LOGGER.log(Level.INFO, details.getName() + " is taking a request from the queue. requests number is down to " + (numRequests - 1) + "\n");
				numRequests--;
				return rentalQueue.take();
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * 
	 * @param clerk : details for the runnable clerk
	 * @param rentalQueue : a shared queue for all parts of the system to maintain, holds the rental requests of the system
	 * @param assets : a shared collection of the assets available in the system
	 * @param numRequests : the number of requests that are in the system
	 */
	public RunnableClerk(ClerkDetails clerk, LinkedBlockingQueue<RentalRequest> rentalQueue, Assets assets, Integer numRequests) {
		details=clerk;
		this.rentalQueue=rentalQueue;
		this.assets = assets;
		RunnableClerk.numRequests = numRequests;
	}
	/**
	 * static getter for number of requests field
	 * @return : the numRequests field of the runnable clerks.
	 */
	public static Integer getNumRequests(){
		return numRequests;
	}
}
