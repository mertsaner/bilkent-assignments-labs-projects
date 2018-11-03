/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	Represents a tank.
*/

public class Tank extends EnemyVehicle {
	int minX, maxX, y;
    public Tank(int minX, int maxX, int y) {
    	super(minX,maxX,y);
    	x = generator.nextInt(maxX - minX + 1) + minX;
    	this.y = y;
    }

	/**
		@return the type of the vehicle
	*/
    public String getType(){
    	return "Tank";
    }

	/**
		@param damage value given to the vehicle
	*/
    public void takeDamage(double damage){
		health = health - damage/2;
	}

	/**
		@return a modified String
	*/
	public String toString(){
		return "Tank at the position (" + x + ", " + y + "). Health = " + health;
	}


}