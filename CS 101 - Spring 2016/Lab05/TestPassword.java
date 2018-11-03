import java.util.Scanner;
public class TestPassword {

    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Password pw1;
		do 
		{
			System.out.print("Enter a new password: ");
			pw1 = new Password(in.next());//user types the password
			if (!pw1.checkLength()) //checks if the length is correct. if the length is correct, returns true.
				{System.out.println("A password must have at least 6 and at most 10 characters.");}
			else if(!pw1.checkDigit())//checks the existance of digits.
				{System.out.println("A password must have at least 1 digit.");}
			else if(!pw1.checkUpperCase())//checks the existance of upper cases.
				{System.out.println("A password must have at least 1 upper case.");}
			else 
				{System.out.println("OK. that's a strong password.");}
		}
		while(!pw1.checkLength() || !pw1.checkDigit() || !pw1.checkUpperCase());
		
}
}




