import java.util.Scanner;
import java.util.Arrays;

public class Game {
	
	private static int size;
	
	public static char menu(Scanner kb) {
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("A) Add a new player");
		System.out.println("S) Search for a player");
		System.out.println("R) Remove a player");
		System.out.println("L) List all players");
		System.out.println("E) Exit");
		System.out.print("Your choice: ");
		return kb.nextLine().charAt(0);
	} //menu
	
	public static Player[] insert(Player[] pa, String name, int score) {
		// Create a room for the new player
		if (size == 0) 
			pa = new Player[1];
		else if (pa.length == size) 
			pa = Arrays.copyOf(pa, 2*pa.length);
		
		// find the right pos to insert the new player
		int pos = 0;
		while (pos< size && pa[pos].getScore() > score)
			pos++;
		System.out.println("pos="+pos+" size="+size);
		// Now pos contain the index where the new player is to be inserted
		if (pos == size) // new player has to be the last
			pa[size++] = new Player(name, score);
		else  { // insert into the middle
			// shift
			for (int i=size; i>pos; i--)
				pa[i] = pa[i-1];
			pa[pos] = new Player(name, score);
			size++;
		} // else
		return pa;
	} // insert
	
	public static int search(Player[] pa, int size, String key) {
		int i=0;
		while (i < size)
			if (pa[i].getName().equals(key))
				return i;
			else
				i++;
		return -1;
	} // search
	
	public static void list (Player[] pa, int size) {
		System.out.print("[");
		if (size == 0) {
			System.out.print("]");
			return;
		}
		System.out.print(pa[0]);
		for (int i=1; i <size; i++)
			System.out.print(", "+pa[i]);
		System.out.print("]");
		int usage = Math.round(100*size / pa.length);
		System.out.println(" Array is "+usage+"% full.");
	} // list
	
	public  static void remove(Player[] pa, int pos) {
		if (pos < 0 || pos >= size) {
			System.out.println("To remove a player, the position must be between 0 and "+(size-1));
			return;
		}
		for (int i=pos; i<size-1; i++)
			pa[i] = pa[i+1];
		pa[--size]= null;
	} // remove

    public static void main(String[] args) {
    	
    	Scanner kb = new Scanner(System.in);
    	
    	Player[] game = new Player[0];
    	size = 0;
    	
    	String name;
    	int score;
    	int pos;
    	char selection;
    	boolean done = false;
    	do {
    		selection = menu(kb);
    		
    		if (selection == 'A') {
    			System.out.print("Enter the name and the score of the new player: ");
    			name = kb.next();
    			score = kb.nextInt();
    			kb.nextLine();
    			game = insert(game, name, score);
    		}
    		else if (selection == 'S') {
    			System.out.print("Enter the name of the player: ");
    			name = kb.nextLine();
    			pos = search(game, size, name);
    			if (pos == -1)
    				System.out.println(name+" does not exist in the game!");
    			else
    				System.out.println(name +" appears in position "+pos);
    		} // search
    		else if (selection == 'R') {
    			System.out.print("Enter the position of the player to be removed: ");
    			pos = kb.nextInt();
    			kb.nextLine();
    			remove(game, pos);
    		}
    		else if (selection == 'L') {
    			list(game, size);
    		} // list
    		else if (selection == 'E') {
    			done = true;
    		}	
    	} while (!done);
    }
}
