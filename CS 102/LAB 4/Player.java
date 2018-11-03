/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	Represents the player that will play the game.
*/
import java.util.ArrayList;

public class Player {
	static final double BOMB_RADIUS = 2.0;
	static final double DAMAGE = 50.0;
	int score = 0;

    public Player() {
    }

	/**
		scans an area and damages the vehicles inside it.
		@param x: x coordinate
		@param y: y coordinate
		@param enemies: enemy arraylist
	*/
    public void attack(int x, int y, ArrayList<EnemyVehicle> enemies){
    	for (int i = 0; i < enemies.size(); i++)
    		if(Math.sqrt(Math.pow(x-enemies.get(i).getX(),2)+Math.pow(y-enemies.get(i).getX(),2)) < BOMB_RADIUS)
    			enemies.get(i).takeDamage(DAMAGE);
    }

	/**
		increases the score of the player
	*/
    public void addScore(){
    	score++;
    }

	/**
		@return the score of the player
	*/
    public int getScore(){
    	return score;
    }


}