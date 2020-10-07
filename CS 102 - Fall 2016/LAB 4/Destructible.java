/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
*/
public interface Destructible {

    /**
		@return whether the vehicle is destroyed or not
	*/
	boolean isDestroyed();

    /**
		@param damages the vehicle
	*/
	void takeDamage(double damage);
}