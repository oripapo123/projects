package main;



public class Driver {


	public static void main(String[] args) {
		String[] arr = new String[4];
		arr[0] = "InitialData.xml";
		arr[1] = "AssetContentsRepairDetails.xml";
		arr[2] = "Assets.xml";
		arr[3] = "CustomersGroups.xml";
		Parser.Parse(arr);
		Management mgt = Parser.getNewManagement();
		mgt.run();
	}

}
