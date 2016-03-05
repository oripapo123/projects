package repairs;

public class RepairToolInformation {
private String tool;
private int quantity;

/**
 * constructor for repair tool information
 * @param material : tool name
 * @param quantity : tool quantity
 */
public RepairToolInformation(String tool, int quantity){
	this.tool=tool;
	this.quantity=quantity;
}

/**
 * copy constructor for repair tool information
 * @param other repair tool information object to be copied from
 */
public RepairToolInformation(RepairToolInformation other){
	this.tool = other.tool;
	this.quantity = other.quantity;
}

/**
 * a getter for the tool name
 * @return tool name
 */
public String getTool() {
	return tool;
}
/**
 * a getter for the tool quantity
 * @return tool quantity
 */
public int getQuantity() {
	return quantity;
}

}
