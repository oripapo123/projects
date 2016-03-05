package repairs;

import java.util.Iterator;
import java.util.Vector;


public class RepairMaterialInformationCollection implements Iterable<RepairMaterialInformation> {
	private Vector<RepairMaterialInformation> materialInformation;
	private String ACName;
	
	/**
	 * constructor for repair material information collection
	 * @param name : name of the asset content of which the collection belongs to
	 */
	public RepairMaterialInformationCollection(String name) {
		materialInformation=new Vector<RepairMaterialInformation>();
		ACName = name;
	}
	
	/**
	 * copy constructor for repair material information collection
	 * @param other : repair material information collection object to be copied from
	 */
	public RepairMaterialInformationCollection(RepairMaterialInformationCollection other) {
		materialInformation = new Vector<RepairMaterialInformation>();
		for(RepairMaterialInformation t : other.materialInformation){
			materialInformation.add(new RepairMaterialInformation(t));
		}
		this.ACName = other.ACName;
	}
	
	/**
	 * getter for the asset contents name
	 * @return asset content name
	 */
	public String getACName(){
		return ACName;
	}
	
	/**
	 * adds repair material information to the collection
	 * @param toAdd : repair material information to be added
	 */
	public void addMaterial(RepairMaterialInformation toAdd) {
		materialInformation.add(toAdd);
	}

	/**
	 * returns an iterator that iterates through the collection.
	 */
	public Iterator<RepairMaterialInformation> iterator() {
		return materialInformation.iterator();
	}

}
