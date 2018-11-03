// Author: Yasin Balcanc�
public class Player {
	private int score;
	private String name;

    public Player(String name, int score) {
    	this.name = name;
    	this.score = score;
    } // constructor of player class
    public String getName() {
    	return name;
    }//get name
    public int getScore() {
    	return score;
    }// get score
}
