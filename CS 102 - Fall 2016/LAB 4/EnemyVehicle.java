/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	This class represents a vehicle.(Whether a helicopter or a tank).
*/
import java.util.Random;

public abstract class EnemyVehicle implements Movable, Destructible {
	boolean isDestroyed = false;
	double health;
	int x, y;
	Random generator = new Random();
	final int BASE_SPEED = 3;

    /**
		@param minX: min value of x.
		@param maxX: max value of x.
		@param y: y coordinate of vehicle.
	*/
	public EnemyVehicle(int minX, int maxX, int y) {
    	x = generator.nextInt(maxX - minX + 1) + minX;
    	this.y = y;
    }

	/**
		decreases the y coordinate
	*/
    public void move(){
    	y -= BASE_SPEED;
    }

	/**
		@return the location of vehicle
	*/
    public Point getLocation(){
    	return new Point(x,y);
    }

	/**
		@return whether the vehicle is destroyed or not
	*/
	public boolean isDestroyed(){
		return isDestroyed;
	}

	/**
		@param damage value given to the vehicle
	*/
	public void takeDamage(double damage){
		health = health - damage;
		if (health <= 0)
			isDestroyed = true;
	}

	/**
		@return x
	*/
	public int getX(){
		return x;
	}

	/**
		@return y
	*/
	public int getY(){
		return y;
	}

	/**
		@return whether the vehicle has crossed the border or not
	*/
	public boolean isCrossed(){
		if (y <= 0) return true;
		else return false;
	}

	/**
		@return the distance to the border
	*/
	public int getDistanceToBorder(){
		return y;
	}

	/**
		@return the type of the vehicle
	*/
	public abstract String getType();


}