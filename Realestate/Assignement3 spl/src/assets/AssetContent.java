package assets;

public class AssetContent{
	private String name;
	private double health;
	private double repairCostMultiplier;
	
	/**
	 * constructor for an asset content
	 * @param other : an assetcontent in which a copy of that assetcontent will be created
	 */
	public AssetContent(AssetContent other){
		this.name = other.name;
		this.health = other.health;
		this.repairCostMultiplier = other.repairCostMultiplier;
		
	}
	
	
	/**
	 * constructor of an asset content
	 * @param name : name of the asset content
	 * @param repairCostMultiplier : repair cost multiplier of the asset content
	 */
	public AssetContent(String name, double repairCostMultiplier) {
		this.name=name;
		this.health=100;
		this.repairCostMultiplier=repairCostMultiplier;
	}
	
	
	/**
	 * a getter to the 'name' field of the asset
	 * @return 'name' variable
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * a getter to the 'health' field of the asset
	 * @return 'health' variable
	 */
	public double getHealth() {
		return health;
	}

	
	/**
	 * a getter to the 'repairCostMultiplier' field of the asset
	 * @return 'repairCostMultiplier' variable
	 */
	public double getRepairCostMultiplier() {
		return repairCostMultiplier;
	}
	
	/**
	 * damages the health of the asset content
	 * @param damage : how much damage occured to the asset content
	 */
	public void damageHealth(double damage){
		health=health-damage;
		if(health < 0)
			health = 0;
	}
	/**
	 * returns the health of the asset content to full
	 */
	public void HealthToFull(){
		health=100;
	}

	/**
	 * sets a new repair cost multiplier
	 * @param assetContentRepairMultiplier : new repair cost multiplier to be applied.
	 * if new repair cost multiplier is not positive, nothing will be changed.
	 */
	public void setRepairMultiplier(double assetContentRepairMultiplier) {
		if(assetContentRepairMultiplier >= 0){
			repairCostMultiplier = assetContentRepairMultiplier;
		}
		
	}
	
	@Override
	/**
	 * returns a string of the assetContent
	 */
	public String toString(){
		String toReturn = "";
		toReturn = name + "\n" +
		"Health is: " + health + "\n" + "Repair Cost Multiplier is: " + repairCostMultiplier + "\n";
		
		return toReturn;
	}

}
