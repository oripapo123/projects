package rentals;

import java.util.Vector;

public class CustomerGroupDetails {

	private Vector<RentalRequest> requests;
	private Vector<Customer> customers;
	private String groupManager;
	private int currentRequestIndex;
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	/**
	 * adds a rental request to the group
	 * @param request : request to be added
	 */
	public void addRentalRequest(RentalRequest request){
		requests.add(request);
	}
	/**
	 * constructor for customer group details
	 * @param groupmanager : group manager name
	 */
	public CustomerGroupDetails(String groupmanager) {
		this.groupManager=groupmanager;
		requests=new Vector<RentalRequest>();
		customers=new Vector<Customer>();
		currentRequestIndex=0;
	}
	/**
	 * fetches a new request from the group
	 * @return a rental request
	 */
	public synchronized RentalRequest getRequests() {
		if (currentRequestIndex<requests.size()){
			RentalRequest vreturn=requests.get(currentRequestIndex);
			return vreturn;
		}
		else {
			return null;
		}
	}
	/**
	 * completes the request
	 */
	public synchronized void RequestCompleted(){
		currentRequestIndex++;
	}
	/**
	 * a getter for the group customers collection
	 * @return a vector of the group's customers
	 */
	public Vector<Customer> getCustomers() {
		Vector<Customer> vreturn=(Vector<Customer>)customers.clone();
		return vreturn;
	}
	/**
	 * a getter for the group's manager name
	 * @return group's manager name
	 */
	public String getGroupManager() {
		return groupManager;
	}
	
	@Override
	/**
	 * returns a string of the groups details.
	 */
	public String toString(){
		String toReturn = "Customer group of " + groupManager + ":\n\nCustomers:\n";
		for(Customer t : customers){
			toReturn = toReturn + t.toString() + "\n";
		}
		toReturn = toReturn + "\nRequests:\n\n";
		for(RentalRequest t : requests){
			toReturn = toReturn + t.toString() + "\n";
		}
		return toReturn;
	}
	
	/**
	 * a getter for number of requests left on that gruop
	 * @return number of requests left
	 */
	public int getRequestsLeft(){
		return requests.size() - currentRequestIndex;
	}
	
}
