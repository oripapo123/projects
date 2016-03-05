package rentals;
import assets.Asset;


public class RentalRequest {
	private int id;
	private String type;
	private int assetSize;
	private int durationOfStay;
	private Asset asset;
	private String requestStatus;
	
	/**
	 * constructor for rental request object
	 * @param id : rental request's ID
	 * @param type : rental request's type
	 * @param assetSize : size of the asset to be looked for
	 * @param durationOfStay : duration of stay for the customer that filled the request
	 */
	public RentalRequest(int id, String type, int assetSize, int durationOfStay) {
		this.id=id;
		this.type=type;
		this.assetSize=assetSize;
		this.durationOfStay=durationOfStay;
		requestStatus="INCOMPLETE";
	}
	/**
	 * getter for the asset of choice
	 * @return asset of choice
	 */
	public Asset getAsset() {
		return asset;
	}
	/**
	 * setter for the asset of choice
	 * @param asset asset of choice to be set
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	/**
	 * getter for the request's status
	 * @return request's status
	 */
	public synchronized String getRequestStatus() {
		return requestStatus;
	}
	/**
	 * setter of request's status
	 * @param requestStatus : status to be set
	 */
	public synchronized void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	/**
	 * getter for request's id
	 * @return request's ID
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter for request's type
	 * @return request's type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * getter for request's asset of choice size
	 * @return request's asset of choice size
	 */
	public int getAssetSize() {
		return assetSize;
	}
	/**
	 * getter for request's duration of stay
	 * @return duration of stay
	 */
	public int getDurationOfStay() {
		return durationOfStay;
	}
	
	@Override
	/**
	 * returns a string of the request's details.
	 */
	public String toString(){
		String toReturn = "requestID: " + id +"\ntype is: " + type + "\nsize is: " 
						   + assetSize + "\nduration of stay is: " + 
				durationOfStay + "\nrequest status is: " + requestStatus + "\n";
		return toReturn;
	}

}
