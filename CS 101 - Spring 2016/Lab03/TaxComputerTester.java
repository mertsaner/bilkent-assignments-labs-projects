import java.util.Scanner;

public class TaxComputerTester {

    public static void main(String[] args) {
    	String status;
    	double income;
    	double taxValue;
    	Scanner in = new Scanner(System.in);
    	
    	System.out.print("Enter your status: ");
    	status = in.next(); //user will type the status
    
    	System.out.print("Enter your income: ");
    	income = in.nextDouble(); //user will type the income
    	
    	TaxComputer person1 = new TaxComputer(status, income);
    	System.out.print("The tax is: " );
    	System.out.println(person1.getTaxValue()); //displays the tax value we calculated
    	
    }
}
