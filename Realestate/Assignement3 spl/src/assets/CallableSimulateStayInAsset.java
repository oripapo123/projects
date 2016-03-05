package assets;

import rentals.Customer;

public class CallableSimulateStayInAsset {
	
	/**
	 * constructor for CallableSimulateStayInAsset
	 */
	public CallableSimulateStayInAsset() {
	}
	
	/**
	 * simulates a stay of a customer in an asset
	 * @param assetToBeVacated : asset in which the customer will be vacating
	 * @param c : customer who will be vacating the asset
	 * @return : damage done to the asset by the customer.
	 */
	//simulation process
	public double simulate(Asset assetToBeVacated, Customer c){
		//assigning damage to asset contents per customer
		double damageDone = c.getDamage();
		for(AssetContent AC : assetToBeVacated.getContents()){
			AC.damageHealth(damageDone);
		}
		//returning damage done by a customer - TODO:: accumulate damage from all AC's to Asset itself, I did not understand
		//exactly how to do that.
		return damageDone;
	}

}
