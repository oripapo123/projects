package assets;

import java.util.Collections;
import java.util.Vector;

public class Assets {
	private Vector<Asset> assets;
	
	/**
	 * constructor for the Assets object
	 */
	public Assets() {
		assets=new Vector<Asset>();
	}
	
	/**
	 * adds an asset to the Assets collection
	 * @param asset : asset to be added
	 */
	public void addAsset(Asset asset){
		assets.add(asset);
	}
	
	/**
	 * fetches all damaged assets from the assets collection
	 * @return vector of damaged assets in the system
	 */
	public Iterable<Asset> getDamagedAssets(){
		Vector<Asset> damagedAssets=new Vector<Asset>();
		for (Asset asset : assets) {
			if (asset.getHealth()<65)damagedAssets.add(asset);
		}
		return damagedAssets;
	}
	/**
	 * @return number of damaged assets in the system
	 */
	public int numOfDamagedAssets(){
		Vector<Asset> damagedAssets=new Vector<Asset>();
		for (Asset asset : assets) {
			if (asset.getHealth()<65)damagedAssets.add(asset);
		}
		return damagedAssets.size();
	}
	/**
	 * finds an asset of type and size
	 * @param type : type of asset to look for
	 * @param size : size of asset to look for
	 * @return asset of identical type and equal or bigger size than asked, if none is found, null will be returned
	 */
	public Asset findAsset(String type, int size){
		for (Asset asset : assets) {
			if((asset.getSize()>=size) && (asset.getType().compareTo(type) == 0)){
				return asset;
			}
		}
		return null;
	}
	
	/**
	 * sorts the Assets collection in ascending order by asset's size.
	 */
	public void sort(){
		Collections.sort(assets);
		Collections.sort(assets, Collections.reverseOrder());
	}
	
	@Override
	/**
	 * returns a string of the assets
	 */
	public String toString(){
		String toReturn = "";
		for (Asset t : assets){
			toReturn = toReturn + t.toString() + "\n";
		}
		return toReturn;
	}
}
