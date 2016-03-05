package repairs;
import java.util.Vector;

import tests.iWarehouse;


public class Warehouse implements iWarehouse {
	private Vector<RepairMaterial> materials;
	private Vector<RepairTool> tools;
	
	/**
	 * constructor for the warehouse object
	 */
	public Warehouse(){
		materials=new Vector<RepairMaterial>();
		tools=new Vector<RepairTool>();
	}
	/**
	 * adds a tool to the warehouse
	 * @param toolToAdd : the tool to be added to the warehouse
	 */
	public void addTool(RepairTool toolToAdd){
		tools.add(toolToAdd);
	}
	/**
	 * adds a material to the warehouse
	 * @param materialToAdd : the material to be added to the warehouse
	 */
	public void addMaterial(RepairMaterial materialToAdd){
		materials.add(materialToAdd);
	}
	
	/**
	 * pulls the tools needed from the warehouse, if the tool was successfully pulled returns true, otherwise false.
	 */
	public boolean  orderTools(String orderedTools, int amountOrdered){
		for (RepairTool repairTool : tools) {
				if(repairTool.getName()==orderedTools){
					repairTool.takeTools(amountOrdered);
					return true;
				}
		}

		return false;
	}
	
	/**
	 * pushes back the tools used to the warehouse, if the tool was successfully pushed returns true, otherwise false.
	 */
	public boolean releaseTools(String releasedTools, int amountOrdered){
	for (RepairTool repairTool : tools) {
			if(repairTool.getName()==releasedTools){
				repairTool.addTools(amountOrdered);
				return true;
			}
	}

	return false;
	}
	
	/**
	 * pulls the materials needed from the warehouse, if the material was successfully pulled returns true, otherwise false.
	 */
	public boolean  orderMaterial(String orderedMaterials, int amountOrdered){
	for (RepairMaterial repairMat : materials) {
			if(repairMat.getName()==orderedMaterials){
				repairMat.takeMaterials(amountOrdered);
				return true;
			}
	}
		return false;
	}
	
	/**
	 * pushes back the materials used to the warehouse, if the material was successfully pushed returns true, otherwise false.
	 */
	public boolean releaseMaterial(String releasedMaterials, int amountOrdered){
	for (RepairMaterial repairMat : materials) {
			if(repairMat.getName()==releasedMaterials){
				repairMat.addMaterials(amountOrdered);
				return true;
			}
	}	
		return false;
	}
	
	@Override
	/**
	 * returns a string of the warehouse details.
	 */
	public String toString(){
		String toReturn = "Warehouse contents Are: " + "\n\n";
		toReturn = toReturn + "Tools: " + "\n";
		for(RepairTool t : tools){
			toReturn = toReturn + t.toString() + "\n";
		}
		toReturn = toReturn + "\n" + "Materials: " + "\n";
		for(RepairMaterial t : materials){
			toReturn = toReturn + t.toString() + "\n";
		}
		return toReturn;
	}
	
}
