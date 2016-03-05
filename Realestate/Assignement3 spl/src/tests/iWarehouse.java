package tests;

import repairs.RepairMaterial;
import repairs.RepairMaterialInformationCollection;
import repairs.RepairTool;
import repairs.RepairToolInformationCollection;

public interface iWarehouse {
	
	/**
     * Will remove the tools object from the container and decrease quantity.
     * 
     * @param obj
     *            any non null T object
     * @param int 
     * 			 any int equal or greater to zero.
     * @pre: tools object exist in warehouse
     * @post: tools object does not exist in warehouse or quantity = @pre:quantity - amountOrdered.
     */
	boolean  orderTools(String orderedTools, int amountOrdered);
	
	/**
     * Will add the tools object from the container and increase quantity.
     * 
     * @param obj
     *            any non null T object
     * @param int 
     * 			 any int equal or greater to zero.
     * @pre: none
     * @post: tools object exist in warehouse and quantity = @pre:quantity + amountOrdered.
     */
	
	boolean releaseTools(String releasedTools, int amountOrdered);
	
	/**
     * Will remove the materials object from the container and decrease quantity.
     * 
     * @param obj
     *            any non null T object
     * @param int 
     * 			 any int equal or greater to zero.
     * @pre: materials object exist in warehouse
     * @post: materials object does not exist in warehouse or quantity = @pre:quantity - amountOrdered.
     */
	
	boolean  orderMaterial(String orderedMaterials, int amountOrdered);
	
	/**
     * Will add the materials object from the container and increase quantity.
     * 
     * @param obj
     *            any non null T object
     * @param int 
     * 			 any int equal or greater to zero.
     * @pre: none
     * @post: materials object exist in warehouse and quantity = @pre:quantity + amountOrdered.
     */
	
	boolean releaseMaterial(String releasedMaterials, int amountOrdered);
}