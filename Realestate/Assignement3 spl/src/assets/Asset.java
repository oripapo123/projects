package assets;

import java.util.Vector;


public class Asset implements Comparable<Asset>{

	private String name;
	private double health;
	private String type;
	private Location location;
	private Vector<AssetContent> contents;
	private String status;
	private int costPerNight;
	private int size;
	
	/**
	 * constructor to the Asset class
	 * @param name : name of the asset
	 * @param type : type of the asset
	 * @param location : cartesian location of the asset
	 * @param contents : a collection of assetcontents of the asset
	 * @param costPerNight : cost per night of the asset
	 * @param size : size of the asset
	 */
	public Asset(String name, String type, Location location, Vector<AssetContent> contents, int costPerNight, int size) {
		this(name,type,location,costPerNight,size);
		this.contents=contents;
	}
	
	/**
	 * constructor for the Asset class
	 * @param name : name of the asset
	 * @param type : type of the asset
	 * @param location : cartesian location of the asset
	 * @param costPerNight : cost per night of the asset
	 * @param size : size of the asset
	 */
	public Asset(String name, String type, Location location, int costPerNight, int size) {
		this.name=name;
		this.type=type;
		this.location=location;
		this.contents=new Vector<AssetContent>();
		this.size=size;
		this.status="AVAILABLE";
		this.costPerNight=costPerNight;
		health=100;
	}

	/**
	 * adds an assetContent to the asset's collection of asset contents
	 * @param content : the content to be added
	 */
	public void addAssetContent(AssetContent content){
		contents.add(content);
	}
	
	/**
	 * a getter to the 'name' field of the asset
	 * @return 'name' variable
	 */
	public String getName() {
		return name;
	}

	/**
	 * a getter to the 'type' field of the asset
	 * @return 'type' variable
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * a getter to the 'Location' field of the asset
	 * @return 'Location' variable
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * a method that returns a copy of the asset's assetContent vector
	 * @return a copy of asset's assetContent vector
	 */
	public Iterable<AssetContent> getContents() {
		Vector<AssetContent> vreturn= (Vector<AssetContent>)contents.clone();
		return vreturn;
	}
	
	/**
	 * a getter to the 'Status' field of the asset
	 * @return 'Status' variable
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * a getter to the 'costPerNight' field of the asset
	 * @return 'costPerNight' variable
	 */
	public int getCostPerNight() {
		return costPerNight;
	}
	
	/**
	 * a getter to the 'size' field of the asset
	 * @return 'size' variable
	 */
	public int getSize() {
		return size;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * a getter to the 'health' field of the asset
	 * @return 'health' variable
	 */
	public double getHealth(){
		return health;
	}
	/**
	 * updates asset damage.
	 * @param damage : damage done to the asset that helps recalculating asset health.
	 */
	public void damageHealth(double damage){
		health=health-damage;
		if (health<0)health=0;
	}
	/**
	 * asset implements comparable, thus implements compareTo method.
	 * comapring asset 'size' field.
	 * returns 1 if object is bigger than the one compared to,
	 * returns 0 if objects are equal at size
	 * returns -1 if object is smaller than the one compared to
	 */
	@Override
	public int compareTo(Asset o) {
		int toReturn = 0;
			if(o.getSize() > this.getSize())
				toReturn = 1;
			else if(o.getSize() == this.getSize())
				toReturn = 0;
			else toReturn = -1;
		return toReturn;
	}
	
	@Override
	/**
	 * returns a string of the asset's properties.
	 */
	public String toString(){
		String toReturn = "";
		toReturn =  name + "\n"  + "\n" +
		"Type is: " + type + "\n" + location + "\n" + 
		"Status is: " + status + "\n" + "Cost per night is: " + costPerNight +
		"\n" + "size is: " + size + "\n" + "\n" + "AssetContents in Asset Are: " + "\n";
		for (AssetContent t : contents){
			toReturn = toReturn + t.toString();
		}
		return toReturn;
	}
}

