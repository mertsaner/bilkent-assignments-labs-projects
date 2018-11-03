/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	Tester class of the game.
*/
import java.util.Scanner;

public class GameTester {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the difficulty level (1, 2 or 3): ");
        int difficulty = in.nextInt();
        Game game = new Game(difficulty);
        game.play();
    }
}
