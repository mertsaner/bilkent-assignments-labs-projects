//Author Yasin Balcancý
import java.util.Scanner;

public class TransformerTest {

    public static void main(String[] args) {
    	double Rs;
    	double R0;
    	double Vs;
    	Scanner in = new Scanner(System.in);
    	do {//according to answer (Y/N), reruns the progress.
    	System.out.print("Please enter R0 value of Amplifier in ohms(R0): ");
    	R0 = in.nextDouble();
    	System.out.print("Please enter Vs value of Amplifier in volts(Vs): ");
    	Vs = in.nextDouble();
    	System.out.print("Please enter Rs value of Amplifier in ohms(Rs): ");
    	Rs = in.nextDouble();
    	
    	Transformer transformer1 = new Transformer(R0, Vs, Rs);
    	System.out.print("Optimal turn ratio is " + transformer1.optimalTurnRatio());
    	System.out.println();
    	System.out.print("Do you want to continue? (Y/N): ");
    	}
    	while (in.next().equals("Y"));//any answer except "Y" will be accepted as "no".
    }
}
