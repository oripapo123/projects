package rentals;
import assets.Location;


public class ClerkDetails {

private String name;
private Location location;

	/**
	 * constructor for clerkDetails
	 * @param name : name of the clerk
	 * @param location : cartesian coordinates of clerks location
	 */
	public ClerkDetails(String name, Location location) {
		this.name=name;
		this.location=location;
	}
	/**
	 * getter for clerk's name
	 * @return : clerk's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * getter for clerk's location
	 * @return : a copy of clerk's location
	 */
	public Location getLocation() {
		
		return new Location(location);
	}
	@Override
	/**
	 * a string of the clerk details.
	 */
	public String toString() {
		return "ClerkDetails [name=" + name + ", location=" + location + "]";
	}
}
