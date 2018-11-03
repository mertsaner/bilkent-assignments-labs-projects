import java.util.Scanner;
/*
 In this program, you will type some numbers which form the lengths of red and blue sticks
 and the line. After you type, program will calculate and display the number of blue and red sticks
 and the length of the each gaps.
**/
public class BlueRedTiles
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the length of a red stick: ");
		double lengthOfRed = in.nextDouble(); //Our input will form the length of a red stick.
		
		System.out.print("Please enter the length of a blue stick: ");
		double lengthOfBlue = in.nextDouble();//Our input will form the length of a blue stick.
		
		System.out.print("Please enter the length of the line: ");
		double lengthOfLine = in.nextDouble();//Our input will form the length of the line.
		
		double numberOfBlues = Math.floor((lengthOfLine - lengthOfRed)/(lengthOfBlue + lengthOfRed));//Because our stick numbers are integers, we used floors of floats.
		double numberOfReds = numberOfBlues + 1 ;// Because our first and last sticks are red, number of reds will be 1 more than blues.
		double gap = (lengthOfLine - (numberOfReds*lengthOfRed + numberOfBlues*lengthOfBlue))/2;//
		
		System.out.print("Number of red sticks: ");
		System.out.println(numberOfReds);
		
		System.out.print("Number of blue sticks: ");
		System.out.println(numberOfBlues);
		
		System.out.print("The gap at each end: ");
		System.out.println(gap);
		
	}
}