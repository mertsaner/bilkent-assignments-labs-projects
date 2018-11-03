/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	Represents a helicopter.
*/

public class Helicopter extends EnemyVehicle {
	int x, y;
	int speed = 3;

    /**
		@param minX: min value of x.
		@param maxX: max value of x.
		@param y: y coordinate of vehicle.
	*/
    public Helicopter(int minX, int maxX, int y) {
    	super(minX,maxX,y);
    	x = generator.nextInt(maxX - minX + 1) + minX;
    	this.y = y;
	}

	/**
		increases the y coordinate of vahicle
	*/
    public void move(){
    	y -= speed;
    	speed++;
    }

	/**
		@return the type of the vehicle
	*/
    public String getType(){
    	return "Helicopter";
    }

	/**
		@return a modified String
	*/
    public String toString(){
		return "Helicopter at the position (" + x + ", " + y + "). Health = " + health;
	}


}