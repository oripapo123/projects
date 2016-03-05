package repairs;



public class RepairMaterial {
	private String name;
	private int quantity;
	/**
	 * constructor for repair material
	 * @param name : name of the repair material
	 * @param quantity : quantity of the repair material
	 */
	public RepairMaterial(String name, int quantity) {
		this.name=name;
		this.quantity=quantity;
	}
	@Override
	/**
	 * returns a string of the repair material details
	 */
	public String toString(){
		String toReturn = "<" + name + "> <" + quantity + ">";
		return toReturn;
	}
	
	/**
	 * getter for the repair material name
	 * @return repair material name
	 */
	public String getName() {
		return name;
	}
	/**
	 * getter for the repair material quantity
	 * @return repair material quantity
	 */
	public synchronized int getQuantity() {
		return quantity;
	}
	
	/**
	 * adds materials to the repair material quantity
	 * @param quantity : number of materials to be added
	 */
	public synchronized void addMaterials(int quantity) {
		this.quantity = this.quantity+quantity;
	}
	/**
	 * takes materials from the repair material quantity
	 * @param quantity : number of materials to be taken
	 */
	public synchronized void takeMaterials(int quantity) {
		this.quantity = this.quantity-quantity;
	}
	

}
