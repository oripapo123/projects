package repairs;
import assets.Asset;


public class DamageReport {
private Asset asset;
private double damagePercentage;
	/**
	 * constructor for damage report
	 * @param asset asset to write report about
	 */
	public DamageReport(Asset asset) {
		this.asset=asset;
		this.damagePercentage=0;
	}
	/**
	 * adds the damage to the damage report
	 * @param damage
	 */
	public void addDamage(double damage){
		damagePercentage=damagePercentage+damage;
	}
	/**
	 * a getter for the asset to write a report about
	 * @return asset
	 */
	public Asset getAsset() {
		return asset;
	}
	/**
	 * a getter for the damage percentage
	 * @return : damage percentage
	 */
	public double getDamagePercentage() {
		return damagePercentage;
	}

}
