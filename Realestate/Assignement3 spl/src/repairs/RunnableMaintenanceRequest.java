package repairs;
import java.util.HashMap;
import java.util.Vector;

import assets.Asset;
import assets.AssetContent;


public class RunnableMaintenanceRequest implements Runnable{

	private Vector<RepairMaterialInformationCollection> matInfo;
	private Vector<RepairToolInformationCollection> toolInfo;
	private Asset asset;
	private Warehouse warehouse;
	
	private HashMap<String, RepairToolInformation> tools=new HashMap<String, RepairToolInformation>();
	private HashMap<String, RepairMaterialInformation> materials=new HashMap<String, RepairMaterialInformation>();
	private RepairMaterialInformationCollection repMatCol=null;
	private RepairToolInformationCollection repToolCol=null;
	private static volatile  Integer numberOfDamagedAssets;
	private static volatile Integer numberOfActiveMaintanancePeople;
	public void run(){
		if(asset.getHealth()>=65) return;// asset undamaged
		System.out.println("maintance operation started on:" + asset.getName());
		initialize();//initializes the collections
		System.out.println("Got the tools and materials");
		//Orders tools and materials needed for repairs
		for (RepairMaterialInformation repMat : repMatCol) {
			warehouse.orderMaterial(repMat.getMaterial(), repMat.getQuantity());
		}
		for (RepairToolInformation repTool : repToolCol) {
			warehouse.orderTools(repTool.getTool(), repTool.getQuantity());
		}
		//assuming every assetcontent has the same damage

		double health=0;
		double multiplier=0;
		for (AssetContent assetContent : asset.getContents()) {
			health=assetContent.getHealth();
			multiplier=multiplier+assetContent.getRepairCostMultiplier();
		}
		System.out.println("assetContents health is:" + health);
		try{
			Thread.sleep((long)((100-health)*multiplier));
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (AssetContent assetContent : asset.getContents()) {
			assetContent.HealthToFull();
		}
		for (RepairToolInformation repTool : repToolCol) {
			warehouse.releaseTools(repTool.getTool(), repTool.getQuantity());
		}
		numberOfDamagedAssets--;
		numberOfActiveMaintanancePeople--;
		System.out.println("maintanance operation finished");
	}

	private void initialize() {
		//finds the correct collection that holds the information
		for (RepairMaterialInformationCollection mats : matInfo) {
			if (mats.getACName()==asset.getName()) repMatCol=mats;
		}

		for (RepairToolInformationCollection tools : toolInfo) {
			if (tools.getACName()==asset.getName()) repToolCol=tools;
		}

		for (RepairMaterialInformation repMat : repMatCol) {//goes over the collection. if the map contains the material, inserts quantity. else, takes the sum of current value and quantity and reinserts it.
			if(!materials.containsKey(repMat.getMaterial()))materials.put(repMat.getMaterial(), repMat);
			else materials.put(repMat.getMaterial(), new RepairMaterialInformation(repMat.getMaterial(), materials.get(repMat.getMaterial()).getQuantity()+repMat.getQuantity()));
		}
		for (RepairToolInformation repTool : repToolCol) {//goes over the collection. if the map contains the tool, inserts quantity. else, if the new value is higher quantity, inserts the new value it.
			if(!tools.containsKey(repTool.getQuantity()))tools.put(repTool.getTool(), repTool);
			else if((tools.get(repTool.getTool())).getQuantity()<repTool.getQuantity()) tools.put(repTool.getTool(), repTool);
		}
	}

	public RunnableMaintenanceRequest(Asset asset, Warehouse warehouse,	Vector<RepairMaterialInformationCollection> matInfo,
	Vector<RepairToolInformationCollection> toolInfo,int numberOfActiveMaintanancePeople, int numberOfDamagedAssets) {
		RunnableMaintenanceRequest.numberOfActiveMaintanancePeople=numberOfActiveMaintanancePeople;
		RunnableMaintenanceRequest.numberOfDamagedAssets=numberOfDamagedAssets;
		this.matInfo=matInfo;
		this.toolInfo=toolInfo;
		this.asset=asset;
		this.warehouse=warehouse;
	}
	
	
}
