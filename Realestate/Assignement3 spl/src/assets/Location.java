package assets;

public class Location {
	private double x;
	private double y;
	
	/**
	 *empty constructor for location. x,y will be reset to (0,0)
	 */
	public Location() {
		x=0;
		y=0;
	}
	
	/**
	 * constructor for location
	 * @param x : x coordinate of location
	 * @param y : y coordinate of location
	 */
	public Location(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * constructor for location
	 * @param other : other location which the constructor will copy x,y coordinates from
	 */
	public Location(Location other){
		if (other!=null){
		x=other.x;
		y=other.y;
		}
	}
	
	/**
	 * getter for x coordinate
	 * @return x coordinate
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * getter for y coordinate
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * calculates the distance from an other object
	 * @param other : object to be calculated distance from
	 * @return distance between the two objects
	 */
	public double calculateDistance(Location other) {
		double xtemp;
		double ytemp;
		xtemp=x-other.getX();
		ytemp=y-other.getY();
		return Math.sqrt(xtemp*xtemp+ytemp*ytemp);
	}

	@Override
	/**
	 * returns a string of the location by mathematical standards.
	 */
	public String toString() {
		return "Location is (" + x + "," + y + ")";
	}
	
	/* not required, but can be unfrozen
	public void translate(Location p) {
		x=x+p.x;
		y=y+p.y;
		
	}
	
	public void rotateRelativeToAxesOrigin(double theta) {
		//rotate counter clockwise by theta
		double tmpX=x*Math.cos(theta)-y*Math.sin(theta);
		double tmpY=y*Math.cos(theta)+x*Math.sin(theta);
		x=tmpX;
		y=tmpY;
	}
	
	public void rotateRelativeToPixel(Location p1, double theta) {
		//rotate counter clockwise by theta
		double tmpX=p1.x+(x-p1.x)*Math.cos(theta)-(y-p1.y)*Math.sin(theta);
		double tmpY=p1.y+(y-p1.y)*Math.cos(theta)+(x-p1.x)*Math.sin(theta);
		x=tmpX;
		y=tmpY;
	}*/
}
