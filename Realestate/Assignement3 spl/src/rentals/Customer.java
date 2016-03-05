package rentals;

public class Customer {

	 private String name;
	 private String VandalismType;
	 private double minDamage;
	 private double maxDamage;
	 /**
	  * constructor of customer object
	  * @param name : name of the customer
	  * @param VandalismType : vandalism type of the customer
	  * @param minDamage : minimal damage done by the customer
	  * @param maxDamage : maximal damage done by the customer
	  */
	public Customer(String name, String VandalismType, double minDamage, double maxDamage) {
		this.name=name;
		this.VandalismType=VandalismType;
		if(!((VandalismType.compareTo("None") == 0) || (VandalismType.compareTo("Fixed") ==0) ||
			(VandalismType.compareTo("Arbitrary") == 0))){    throw new RuntimeException("invalid input"); }
		this.minDamage=minDamage;
		this.maxDamage=maxDamage;
	}
	@Override
	/**
	 * a string of the customer details
	 */
	public String toString() {
		return "Customer [name=" + name + ", VandalismType=" + VandalismType
				+ ", minDamage=" + minDamage + ", maxDamage=" + maxDamage + "]";
	}
	/**
	 * a getter of the customer name
	 * @return customer name
	 */
	public String getName() {
		return name;
	}
	/**
	 * a getter of the customer vandalism type
	 * @return the customer vandalism type
	 */
	public String getVandalismType() {
		return VandalismType;
	}
	
	/**
	 * a getter for the customer's damage
	 * @return costumer's damage
	 */
	public double getDamage() {
		double damage=0.5;
		if (VandalismType=="Fixed")damage=(minDamage+maxDamage)/2;
			else if (VandalismType=="ARBITRARY")damage=Math.random()*(maxDamage-minDamage)+minDamage;
		return damage;
	}

}
