package main;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import rentals.ClerkDetails;
import rentals.Customer;
import rentals.CustomerGroupDetails;
import rentals.RentalRequest;
import repairs.RepairMaterial;
import repairs.RepairMaterialInformation;
import repairs.RepairMaterialInformationCollection;
import repairs.RepairTool;
import repairs.RepairToolInformation;
import repairs.RepairToolInformationCollection;
import repairs.Warehouse;
import assets.Asset;
import assets.AssetContent;
import assets.Assets;
import assets.Location;

public final class Parser {

	static int numberOfMaintenancePersons;
	static int totalNumberOfRentalRequests;
	static String xmlPath;
	static Management management;
	static Assets assets;
	static Warehouse warehouse;
	static Vector<AssetContent> ACVector;
	static Vector<RepairToolInformationCollection> RTIVector;
	static Vector<RepairMaterialInformationCollection> RMIVector;
	static Vector<ClerkDetails> CDVector;
	static Vector<CustomerGroupDetails> CGDVector;
	static Vector<RepairToolInformationCollection> RTICVector;
	static Vector<RepairMaterialInformationCollection> RMICVector;
	
	static Document dom;
	/**
	 * constructor for the system parser
	 * @param input : an array of which input files will be fetched.
	 */
	public static void Parse(String[] input){
		//initializing all necessary variables for successful creation of objects needed for simulation
		assets = new Assets();
		warehouse = new Warehouse();
		ACVector = new Vector<AssetContent>();
		RTIVector = new Vector<RepairToolInformationCollection>();
		RMIVector =  new Vector<RepairMaterialInformationCollection>();
		CDVector = new Vector<ClerkDetails>();
		CGDVector = new Vector<CustomerGroupDetails>();
		RMICVector = new Vector<RepairMaterialInformationCollection>();
		RTICVector = new Vector<RepairToolInformationCollection>();
		
		//sending to suitable parsing function
		for(int i=0; i< input.length; i++){
			xmlPath = input[i];
			parseXmlFile();
			parseDocument(i);
		}
	}
	
	private static void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(xmlPath);
			

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void parseDocument(int i){
		//parsing suitable *.xml file through input
		switch(i){
		case(0):
			parseInitialData();
			break;
		
		case(1):
			parseAssetContentsRepairDetails();
			break;
	
		case(2):
			parseAssets();
			break;
		
		case(3):
			parseCustomersGroups();
			break;
		}	
	}

	private static void parseAssets() {
		//get the root elememt
				Element docEle = dom.getDocumentElement();
				
				//get a nodelist of <Asset> elements
				NodeList nl = docEle.getElementsByTagName("Asset");
				if(nl != null && nl.getLength() > 0) {
					for(int i = 0 ; i < nl.getLength();i++) {
						
						//get the Asset element
						Element el = (Element)nl.item(i);
						//get the Asset object
						Asset tempAsset = getAsset(el);
						
						//add it to assets
						assets.addAsset(tempAsset);
					}
				}
			assets.sort();
	}

	private static Asset getAsset(Element ele) {
		//getting the 'name' and 'type' values from the xml nodes
		String name = getTextValue(ele,"Name");
		String type = getTextValue(ele,"Type");
		int size = getIntValue(ele,"Size");
		int costpernight = getIntValue(ele,"CostPerNight");
		Location loc = getLocation(ele);
		//Create a new Asset with the value read from the xml nodes
		Asset newAsset = new Asset(name, type, loc, costpernight, size);
		
		
		//creating a new AssetContent through copy of one in ACVector,
		//adding the RepairMultiplier. adding the AssetContent to newAsset
		NodeList nl = ele.getElementsByTagName("AssetContent");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the AssetContent element
				Element el = (Element)nl.item(i);
				//search and update AssetContent in ACVector and add it to newAsset
				AssetContent tempAssetContent = new AssetContent(findAssetContent(el));
				tempAssetContent.setRepairMultiplier(getDoubleValue(el, "RepairMultiplier"));
				newAsset.addAssetContent(tempAssetContent);
			}
		}
		return newAsset;
	}
	
	private static AssetContent findAssetContent(Element ele) {
		String AssetContentName = getTextValue(ele,"Name");
		for (AssetContent t : ACVector){
				if(t.getName().compareTo(AssetContentName) == 0){
					return t;
				}
		}
		return null;
	}

	private static void parseAssetContentsRepairDetails() {
		//get the root element
		Element docEle = dom.getDocumentElement();
		//get a nodelist of <AssetContent> elements
		NodeList nl = docEle.getElementsByTagName("AssetContent");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the AssetContent element
				Element ele = (Element)nl.item(i);
				//get the AssetContent object
				AssetContent tempAssetContent = getAssetContent(ele);
				//adding AssetContent to <AssetContent> vector 
				ACVector.add(tempAssetContent);
			}
		}
		
		
	}

	private static AssetContent getAssetContent(Element ele) {
		//getting the 'name' , 'tools' and 'material' values from the xml nodes
				String name = getTextValue(ele,"Name");
				//create a new AssetContent with the value read from the xml nodes
				AssetContent toReturn = new AssetContent(name, 100);
				RepairToolInformationCollection RTICollection = new RepairToolInformationCollection(name);
				RepairMaterialInformationCollection RMICollection = new RepairMaterialInformationCollection(name);
				//create the RepairToolInformation objects needed to repair the AssetContent
				NodeList nl = ele.getElementsByTagName("Tool");
				if(nl != null && nl.getLength() > 0) {
					for(int i = 0 ; i < nl.getLength();i++) {
						//get the RepairToolInformation element
						Element el = (Element)nl.item(i);
						//get the RepairToolInformation object
						RepairToolInformation tempRepairToolInformation = getRepairToolInformation(el);
						//adding RepairToolInformation to AssetContent
						RTICollection.addTool(tempRepairToolInformation);
					}
				}
				
				RTICVector.add(RTICollection);
				
				nl = ele.getElementsByTagName("Material");
				if(nl != null && nl.getLength() > 0) {
					for(int i = 0 ; i < nl.getLength();i++) {
						//get the RepairMaterialInformation element
						Element el = (Element)nl.item(i);
						//get the RepairMaterialInformation object
						RepairMaterialInformation tempRepairMaterialInformation = getRepairMaterialInformation(el);
						//adding RepairMaterialInformation to AssetContent
						RMICollection.addMaterial(tempRepairMaterialInformation);
					}
				}
				
				RMICVector.add(RMICollection);
			return toReturn;
	}

	private static RepairMaterialInformation getRepairMaterialInformation(Element el) {
		//getting the 'name' and 'quantity' values from the xml nodes
		String name = getTextValue(el,"Name");
		int quantity = getIntValue(el, "Quantity");
		//create a new RepairMaterialInformation with the value read from the xml nodes
		RepairMaterialInformation toReturn = new RepairMaterialInformation(name, quantity);
		return toReturn;
	}

	private static RepairToolInformation getRepairToolInformation(Element el) {
		//getting the 'name' and 'quantity' values from the xml nodes
				String name = getTextValue(el,"Name");
				int quantity = getIntValue(el, "Quantity");
				//create a new RepairToolInformation with the value read from the xml nodes
				RepairToolInformation toReturn = new RepairToolInformation(name, quantity);
				return toReturn;
	}

	private static void parseCustomersGroups() {
Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <CustomerGroupDetails> elements
		NodeList nl = docEle.getElementsByTagName("CustomerGroupDetails");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the CustomerGroupDetails element
				Element ele = (Element)nl.item(i);
				//get the CustomerGroupDetails object
				CustomerGroupDetails tempCustomerGroupDetails = getCustomerGroupDetails(ele);
				//add the CustomerGroupDetails to the CGDVectoer
				CGDVector.add(tempCustomerGroupDetails);
			}
		}
	}

	private static CustomerGroupDetails getCustomerGroupDetails(Element ele) {
		//get the CustomerGroupDetails manager name
		String managerName = getTextValue(ele, "GroupManagerName");
		//create the CustomerGroupDetails object
		CustomerGroupDetails toReturn = new CustomerGroupDetails(managerName);
		//get a nodelist of <Customer> elements
				NodeList nl = ele.getElementsByTagName("Customer");
				if(nl != null && nl.getLength() > 0) {
					for(int i = 0 ; i < nl.getLength();i++) {
						//get the Customer element
						Element el = (Element)nl.item(i);
						//get the Customer object
						Customer tempCustomer = getCustomer(el);
						//add the Customer to the CustomerGroupDetails;
						toReturn.addCustomer(tempCustomer);
					}
				}
				
				//get a nodelist of <RentalRequests> elements
				nl = ele.getElementsByTagName("Request");
				if(nl != null && nl.getLength() > 0) {
					for(int i = 0 ; i < nl.getLength();i++) {
						//get the RentalRequests element
						Element el = (Element)nl.item(i);
						//get the RentalRequests object
						RentalRequest tempRentalRequest = getRentalRequest(el);
						//add the RentalRequest to the CustomerGroupDetails;
						toReturn.addRentalRequest(tempRentalRequest);
					}
				}
				return toReturn;
	}

	private static RentalRequest getRentalRequest(Element ele) {
		//get the request ID
		int id = Integer.parseInt(ele.getAttribute("id"));
		//get the request type
		String type = getTextValue(ele, "Type");
		//get the request size
		int size = getIntValue(ele, "Size");
		//get the request duration
		int duration = getIntValue(ele, "Duration");
		return new RentalRequest(id, type, size, duration);
	}

	private static Customer getCustomer(Element ele) {
		//get the Customer name
		String name = getTextValue(ele, "Name");
		//get the vandalism type
		String vandalismType = getTextValue(ele, "Vandalism");
		//get the minimum damage number
		double minDamage = getDoubleValue(ele, "MinimumDamage");
		//get the maximum damage number
		double maxDamage = getDoubleValue(ele, "MaximumDamage");
		return new Customer(name, vandalismType, minDamage, maxDamage);
	}

	private static void parseInitialData() {
		//get the root element
		Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <Tool> elements
		NodeList nl = docEle.getElementsByTagName("Tool");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the Tool element
				Element ele = (Element)nl.item(i);
				//get the Tool object
				RepairTool tempRepairTool = getTool(ele);
				//add the tool to the warehouse
				warehouse.addTool(tempRepairTool);
			}
		}
		//get a nodelist of <Material> elements
		nl = docEle.getElementsByTagName("Material");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the Material element
				Element ele = (Element)nl.item(i);
				//get the Material object
				RepairMaterial tempRepairMaterial = getMaterial(ele);
				//add the material to the warehouse
				warehouse.addMaterial(tempRepairMaterial);
			}
		}
		
		//get a nodelist of <Clerk> elements
		nl = docEle.getElementsByTagName("Clerk");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the Clerk element
				Element ele = (Element)nl.item(i);
				//get the Clerk object
				ClerkDetails tempClerkDetail = getClerkDetails(ele);
				//add the clerk to the CDVector
				CDVector.add(tempClerkDetail);
			}
		}
		
		//getting the 'number of maintenance' and 'total number of rental requests' values
		nl = docEle.getElementsByTagName("Staff");
		Element Staff = ((Element) nl.item(0));
		numberOfMaintenancePersons = Integer.parseInt(getTextValue(Staff , "NumberOfMaintenancePersons"));
		totalNumberOfRentalRequests = Integer.parseInt(getTextValue(Staff , "TotalNumberOfRentalRequests"));
	}

	private static ClerkDetails getClerkDetails(Element ele) {
		//getting the 'name' and 'location' values from the xml nodes
		String name = getTextValue(ele,"Name");
		Location loc = getLocation(ele);
		//create a new ClerkDetails with the value read from the xml nodes
		ClerkDetails toReturn = new ClerkDetails(name, loc);
		return toReturn;
	}

	private static RepairMaterial getMaterial(Element ele) {
		//getting the 'name' and 'quantity' values from the xml nodes
		String name = getTextValue(ele,"Name");
		int quantity = getIntValue(ele, "Quantity");
		////create a new RepairMaterial with the value read from the xml nodes
		RepairMaterial toReturn = new RepairMaterial(name, quantity);
		return toReturn;
	}

	private static RepairTool getTool(Element ele) {
		//getting the 'name' and 'quantity' values from the xml nodes
		String name = getTextValue(ele,"Name");
		int quantity = getIntValue(ele, "Quantity");
		//create a new RepairTool with the value read from the xml nodes
		RepairTool toReturn = new RepairTool(name, quantity);
		return toReturn;
	}

	private static Location getLocation(Element ele) {
		//parsing the Location values out of Element 'ele' provided
		Location toReturn = null;
		NodeList nl = ele.getElementsByTagName("Location");
		if(nl != null && nl.getLength() > 0) {
			Node node = nl.item(0);
			//parsing x and y values out of xml nodes
			double x = Double.parseDouble((((Element) node).getAttribute("x")));
			double y = Double.parseDouble((((Element) node).getAttribute("y")));
			//create a new Location from values out of xml nodes.
			toReturn = new Location(x,y);
		}
		return toReturn;
	}

	private static String getTextValue(Element ele, String tagName) {
		//parsing the 'tagName' string out of Element 'ele' provided
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	private static int getIntValue(Element ele, String tagName) {
		//parsing the 'tagName' int out of Element 'ele' provided
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	private static double getDoubleValue(Element ele, String tagName) {
		//parsing the 'tagName' int out of Element 'ele' provided
		return Double.parseDouble(getTextValue(ele,tagName));
	}
	
	/**
	 *constructs and returns a new management object
	 * @return : management object
	 */
	public static Management getNewManagement() {
		management = new Management(assets, warehouse, numberOfMaintenancePersons, totalNumberOfRentalRequests);
		for(CustomerGroupDetails t : CGDVector){
			management.addCustomerGroup(t);
		}
		
		for(ClerkDetails t : CDVector){
			management.addclerk(t);
		}
		
		for(RepairToolInformationCollection t : RTICVector){
			management.addItemRepairTool(t);
		}
		
		for(RepairMaterialInformationCollection t : RMICVector){
			management.addItemRepairMaterial(t);
		}
		return management;
	}
	
}