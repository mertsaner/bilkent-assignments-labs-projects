/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
*/
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Difficulty {
	int difficulty;
	static final int STARTING_DISTANCE = 20;
	Player player;
	ArrayList<EnemyVehicle> enemies;

    /*
    	Difficulties --> 1:Easy, 2:Medium, 3:Hard
    	@param difficulty level
    */
    public Game(int difficulty) {
    	if (difficulty > 3) difficulty = 3;
    	if (difficulty < 1) difficulty = 1;
    	this.difficulty = difficulty;
    	player = new Player();
    	enemies = new ArrayList<EnemyVehicle>();
		for (int i = 0; i < difficulty * 2; i++)
			enemies.add(getNewRandomVehicle());
    }

	/**
		sets the difficulty to a new value
		@param difficulty value that will be setted
	*/
    public void setDifficulty(int difficulty){
    	this.difficulty = difficulty;
    }

	/**
		@return the difficulty
	*/
    public int getDifficulty(){
    	return difficulty;
    }

	/**
		@return a random vehicle
	*/
    private EnemyVehicle getNewRandomVehicle(){
    	double chance = Math.floor(Math.random()*2);
    	if (chance == 0)
    		return new Helicopter(0, difficulty*5, STARTING_DISTANCE);
    	else return new Tank(0, difficulty*5, STARTING_DISTANCE);
    }

	/**
		Asks the user to type coordinates. Scans around the point and
		damages the vehicles that are in the area. Then checks if any
		vehicle is destroyed or not. If destroyed, replaces it with a
		new one and increases the score of the player. Then moves the
		vehicles. After moving, checks whether any vehicle has crossed
		the border or not. If one crossed, increases the value of total
		crossing time. Stops when the crossing time has reached the limit.
	*/
	public void play(){
		Scanner in = new Scanner(System.in);
		int noOfPassingEnemies = 0;
		final int MAX_NO_OF_PASSING_ENEMIES = 10;
		while(noOfPassingEnemies < MAX_NO_OF_PASSING_ENEMIES){
			System.out.print("Enter the x coordinate to attack: ");
			int x = in.nextInt();
			System.out.print("Enter the y coordinate to attack: ");
			int y = in.nextInt();
			player.attack(x, y, enemies);
			for (int i = 0; i < enemies.size(); i++){
				if(enemies.get(i).isDestroyed()){
					enemies.add(i, getNewRandomVehicle());
					enemies.remove(i-1);
					player.addScore();
				}
				enemies.get(i).move();
			}
			for (int i = 0; i < enemies.size(); i++)
				if(enemies.get(i).isCrossed()){
					enemies.add(i, getNewRandomVehicle());
					enemies.remove(i-1);
					noOfPassingEnemies++;
				}
			System.out.println("Current Score of the Player: " + player.getScore());
			System.out.println("Number of vehicles crossed the border: " + noOfPassingEnemies);
			System.out.println();
		}
		System.out.println("Game Over!");
		System.out.println("Final Score of the Player: " + player.getScore());
	}

	/**
		@return the vehicles
	*/
	public ArrayList<EnemyVehicle> getEnemies(){
		return enemies;
	}

}