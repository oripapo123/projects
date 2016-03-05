package repairs;



public class RepairTool {
	private String name;
	private int quantity;
	/**
	 * constructor for repair tool
	 * @param name : name of the repair tool
	 * @param quantity : quantity of the repair tool
	 */
	public RepairTool(String name, int quantity) {
		this.name=name;
		this.quantity=quantity;
	}
	/**
	 * a getter for the tool name
	 * @return tool name
	 */
	public String getName() {
		return name;
	}
	/**
	 * a getter for the tool quantity
	 * @return tool quantity
	 */
	public synchronized int  getQuantity() {
		return quantity;
	}
	/**
	 * adds tools to the repair material quantity
	 * @param quantity : number of tools to be added
	 */
	public synchronized void addTools(int quantity) {
		this.quantity = this.quantity+quantity;
	}
	/**
	 * takes tools from the repair tool quantity
	 * @param quantity : number of tools to be taken
	 */
	public synchronized void takeTools(int quantity) {
		this.quantity = this.quantity-quantity;
	}
	
	@Override
	/**
	 * returns a string of the repair tool details.
	 */
	public String toString(){
		String toReturn = "<" + name + "> <" + quantity + ">";
		return toReturn;
	}

}
