// Author: Yasin Balcancý
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static void insert(ArrayList<Player> game, Player newPlayer) {
		int pos = 0;
		boolean added = false;
		do{
			if (game.size() == 0){
				game.add(newPlayer);
				added = true;
			}
			else if (game.get(pos).getScore() >= newPlayer.getScore()){
				game.add(pos+1, newPlayer);
				added = true;
			}
			else{
				game.add(pos,newPlayer);
				added = true;
			}
		pos--;
		} //do
		while (!added && pos < game.size());
	}// insert method
	
	public static int search(String key, ArrayList<Player> game){
		boolean found = false;
		int pos = 0; //position
		while (pos < game.size() && !found){
			if (game.get(pos).getName().equals(key))
				found = true;
			else
				pos++;// every time that searches, pos increases 1.
			}
		if (found = false) // if not found, returns -1
			pos = -1;
		return pos;
	} // search method
	
	public static void remove(ArrayList<Player> game, int pos) {
		game.remove(pos);
	}// remove method
	
	public static void list(ArrayList<Player> game){
		System.out.print("[");
		if (game.size() != 0){
			for (int i = 0; i < game.size() - 1; i++)
				System.out.print(game.get(i).getName() + "(" + game.get(i).getScore() + "), ");
			System.out.print(game.get(game.size()-1).getName() + "(" + game.get(game.size()-1).getScore() + ")");
		}
		System.out.println("]");
	} // list method
	  
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
        ArrayList<Player> game = new ArrayList<Player>();
        boolean end = false;
        do{
        	System.out.println("What do you want to do?");
       	 	System.out.println("A) Add a new player");
       	 	System.out.println("S) Search for a player");
       	 	System.out.println("R) Remove a player");
       	 	System.out.println("L) List all players");
        	System.out.println("E) Exit");
        	System.out.print("Your choice: ");
        	String input = in.next();
        	if (input.equals("A")){
        		System.out.print("Enter the name and the score of the player: ");
        		String name = in.next();
        		int score = in.nextInt();
        		insert (game, new Player(name, score));
        	} // adding
        	
        	else if (input.equals("S")){
        		System.out.print("Enter the name of the player: ");
        		String key = in.next();
        		System.out.println(game.get(0).getName());
        		System.out.println(game.get(0).getScore());
        		System.out.println(key+" appears in position "+search(key, game));
        	} // searching
        	
        	else if (input.equals("E")) {
        		end = true;
        	} // exit
        	
        	else if (input.equals("R")) {
        		System.out.print("Enter the position of the player to be removed: ");
        		int pos = in.nextInt();
        		if (pos < 0 || pos >= game.size())
        			System.out.println("To remove a player, the position must be between 0 and " + (game.size()-1));
        		else if (game.size() == 0)
        			System.out.println("To remove a player, you must add first");
        		else
        			remove(game, pos);
        	}// removing
        	
        	else if (input.equals("L")) {
        		list(game);
        	}// listing
        	
        } // do
    	while (!end);
    }
}
  