package main;
import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import rentals.ClerkDetails;
import rentals.CustomerGroupDetails;
import rentals.RentalRequest;
import rentals.RunnableClerk;
import rentals.RunnableCustomerGroupManager;
import repairs.DamageReport;
import repairs.RepairMaterialInformationCollection;
import repairs.RepairToolInformationCollection;
import repairs.RunnableMaintenanceRequest;
import repairs.Warehouse;
import assets.Asset;
import assets.AssetContent;
import assets.Assets;

public class Management {
	private Vector<ClerkDetails> clerks;
	private Vector<CustomerGroupDetails> customers;
	private Assets assets;
	private Warehouse warehouse;
	private Vector<RepairToolInformationCollection> toolsInfo;
	private Vector<RepairMaterialInformationCollection> materialsInfo;
	private int numberOfMaintenancePeople;
	private static Integer numberOfRentalRequests;
	private static volatile Integer numberOfDamagedAssets;
	private static volatile Integer numberOfActiveMaintanancePeople;
	private Map<RunnableClerk, Thread> clerkMap;
	private Map<RunnableCustomerGroupManager, Thread> customerGroupMap;
	private LinkedBlockingQueue<RentalRequest> rentalQueue; //FIFO queue, Links based
	private Vector<DamageReport> damageReports;
	private final static Logger LOGGER = Logger.getLogger(Management.class.getName()); 
	
	/**
	 * starts the simulation of the system
	 */
	public void run(){
		boolean allClerksFinished = false;
		initializeThreads();
		//as long as there are requests left on program (checks through runnable clerk static field 'numRequests' - again Integer does not work well here.
		while(RunnableClerk.getNumRequests() > 0){
			//checking if all clerks are finished, enough for one of the clerkThreads to not be on 'waiting' state so that while
			//will keep on running, waiting 1 second between each check.
			while(!allClerksFinished){
				//TODO: fix the clerk picking order, groups should damage instead of reports. // damaging is done at callablestayinasset...
				allClerksFinished = true;
				for(Map.Entry<RunnableClerk, Thread> runClerk : clerkMap.entrySet()){
					if(runClerk.getValue().getState() != State.WAITING){
						allClerksFinished = false;
					}
					else try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			damageReportsHandling();
			startMaintanance();
			//if the condition above is not met, means that all clerks are in wait mode - time to notify!
			for(Map.Entry<RunnableClerk, Thread> runClerk : clerkMap.entrySet()){
				synchronized(runClerk.getKey()){
					runClerk.getKey().notify();
				}
			}
			
		}
	}

	private void damageReportsHandling() {
		LOGGER.log(Level.INFO, "started damage reports handling");
		for (DamageReport dmgReport : damageReports) {
			dmgReport.getAsset().damageHealth(dmgReport.getDamagePercentage());
			for (AssetContent assetContent : dmgReport.getAsset().getContents()) {
				assetContent.damageHealth(dmgReport.getDamagePercentage());
			}
		}
		LOGGER.log(Level.INFO, "damage reports handling finished");
		damageReports.clear();
		
	}

	private void startMaintanance() {
		LOGGER.log(Level.INFO, "Entered maintanance phase");
		numberOfDamagedAssets=assets.numOfDamagedAssets();
		for (Asset asset : assets.getDamagedAssets()) {
			LOGGER.log(Level.INFO, "initiating new maintanace operation");
				numberOfActiveMaintanancePeople++;
				RunnableMaintenanceRequest tempRunnableMaintenanceRequest = new RunnableMaintenanceRequest(asset,warehouse,materialsInfo,toolsInfo, numberOfActiveMaintanancePeople, numberOfDamagedAssets);
				Thread tempRunnableMaintenanceThread = new Thread(tempRunnableMaintenanceRequest);
				while(numberOfActiveMaintanancePeople==numberOfMaintenancePeople){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
		while (numberOfDamagedAssets>0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		LOGGER.log(Level.INFO, "finished maintanance phase");
	}

	/**
	 * initializing the threads of the system 
	 */
	public void initializeThreads(){
		//creates the threads - using hashMap of pairs <runnable, Thread> to run the threads and obtain control over runnable's wait\notify
		for (ClerkDetails clerk : clerks) {
			RunnableClerk tempRunnableClerk = new RunnableClerk(clerk, rentalQueue, assets, numberOfRentalRequests);
			Thread tempClerkThread = new Thread(tempRunnableClerk);
			clerkMap.put(tempRunnableClerk, tempClerkThread);
		}
		for (CustomerGroupDetails customer : customers) {
			RunnableCustomerGroupManager tempRunnableGroupManager = new RunnableCustomerGroupManager(customer, rentalQueue, damageReports);
			Thread tempCustomerGroupThread = new Thread(tempRunnableGroupManager);
			customerGroupMap.put(tempRunnableGroupManager, tempCustomerGroupThread);
		}

		//starting the threads
		for (Map.Entry<RunnableClerk, Thread> runClerk : clerkMap.entrySet()){
			runClerk.getValue().start();
		}

		for (Map.Entry<RunnableCustomerGroupManager, Thread> runGroup : customerGroupMap.entrySet()){
			runGroup.getValue().start();
		}
	}

	//	public Iterator<RepairMaterialInformation> getMaterialInformationIterator(String contentName){
	//		for (int i = 0; i < materialsInfo.size(); i++) {
	//			if (materialsInfo.get(i).getACName()==contentName){
	//				return materialsInfo.get(i).getIterator();
	//			}
	//		}
	//		return null;
	//	}
	//	public Iterator<RepairToolInformation> getToolInformationIterator(String contentName){
	//		for (int i = 0; i < toolsInfo.size(); i++) {
	//			if (toolsInfo.get(i).getACName()==contentName){
	//				return toolsInfo.get(i).getIterator();
	//			}
	//		}
	//		return null;
	//	}
	/**
	 * adds a clerk to the system
	 * @param clerkDetails clerk to be added
	 */
	public void addclerk(ClerkDetails clerkDetails){
		clerks.add(clerkDetails);
	}
	/**
	 * adds a customer group to the system
	 * @param customerGroup the customerGroup to be added
	 */
	public void addCustomerGroup(CustomerGroupDetails customerGroup){
		customers.add(customerGroup);
	}
	/**
	 * adds a repair tool information collection to the system
	 * @param toolInfo the repair tool information collection to be added
	 */
	public void addItemRepairTool(RepairToolInformationCollection toolInfo){
		toolsInfo.add(toolInfo);
	}
	/**
	 * adds a repair material information collection to the system
	 * @param matInfo the repair material information collection to be added
	 */
	public void addItemRepairMaterial(RepairMaterialInformationCollection matInfo){
		materialsInfo.add(matInfo);
	}
	/**
	 * constructor for management 
	 * @param assets : assets collection of the whole system
	 * @param warehouse : tools and materials warehouse
	 * @param numberOfMaintenancePeople
	 * @param numberOfRentalRequests
	 */
	public Management(Assets assets, Warehouse warehouse, int numberOfMaintenancePeople, int numberOfRentalRequests) {
		rentalQueue= new LinkedBlockingQueue<RentalRequest>();
		clerks=new Vector<ClerkDetails>();
		customers=new Vector<CustomerGroupDetails>();
		this.assets=assets;
		this.warehouse=warehouse;
		toolsInfo=new Vector<RepairToolInformationCollection>();
		materialsInfo=new Vector<RepairMaterialInformationCollection>();
		this.numberOfMaintenancePeople=numberOfMaintenancePeople;
		Management.numberOfRentalRequests=new Integer (numberOfRentalRequests);
		damageReports= new Vector<DamageReport>();
		clerkMap = new HashMap<RunnableClerk, Thread>();
		customerGroupMap = new HashMap<RunnableCustomerGroupManager, Thread>();
	}

	@Override
	public String toString(){
		String toReturn = "";
		toReturn = assets.toString() + "\n" + warehouse.toString() + "\n";
		for(CustomerGroupDetails t : customers){
			toReturn = toReturn + t.toString() + "\n";
		}
		for(ClerkDetails t : clerks){
			toReturn = toReturn + t.toString() + "\n";
		}
		return toReturn;
	}

}
