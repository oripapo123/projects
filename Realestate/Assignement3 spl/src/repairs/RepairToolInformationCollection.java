package repairs;

import java.util.Iterator;
import java.util.Vector;



public class RepairToolInformationCollection implements Iterable<RepairToolInformation>{
	private Vector<RepairToolInformation> toolsInformation;
	private String ACName;
	
	/**
	 * constructor for repair tool information collection
	 * @param name : name of the asset content of which the collection belongs to
	 */
	public RepairToolInformationCollection(String name) {
		toolsInformation=new Vector<RepairToolInformation>();
		ACName = name;
	}
	
	/**
	 * copy constructor for repair tool information collection
	 * @param other : repair tool information collection object to be copied from
	 */
	public RepairToolInformationCollection(RepairToolInformationCollection other) {
		toolsInformation = new Vector<RepairToolInformation>();
		for(RepairToolInformation t : other.toolsInformation){
			toolsInformation.add(new RepairToolInformation(t));
		}
		this.ACName = other.ACName;
	}
	
	/**
	 * adds a repair tool information to the collection
	 * @param toAdd : repair tool information to be added
	 */
	public void addTool(RepairToolInformation toAdd) {
		toolsInformation.add(toAdd);
	}
	
	/**
	 * getter to the asset content's name
	 * @return asset content's name
	 */
	public String getACName(){
		return ACName;
	}

	@Override
	/**
	 * returns an iterator the iterates through the collection
	 */
	public Iterator<RepairToolInformation> iterator() {
		return toolsInformation.iterator();
	}
}
