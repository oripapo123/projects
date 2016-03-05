package repairs;

public class RepairMaterialInformation {
private String material;
private int quantity;

/**
 * constructor for repair material information
 * @param material : material name
 * @param quantity : material quantity
 */
public RepairMaterialInformation(String material, int quantity){
	this.material=material;
	this.quantity=quantity;
}

/**
 * copy constructor for repair material information
 * @param other repair material information object to be copied from
 */
public RepairMaterialInformation(RepairMaterialInformation other){
	this.material = other.material;
	this.quantity = other.quantity;
}

/**
 * getter for the repair material information material name
 * @return material name
 */
public String getMaterial() {
	return material;
}

/**
 * getter for the repair material information material quantity
 * @return material quantity
 */
public int getQuantity() {
	return quantity;
}

}
