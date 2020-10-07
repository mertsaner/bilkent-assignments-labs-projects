import java.io.*;
import java.util.*;

public class BigIntegerTester{

	public static BigIntegerList readBigIntegerFile(String fileName) throws IOException{
		Scanner fileScan = new Scanner(new File(fileName));
		ArrayList<String> sar = new ArrayList<String>();
		while (fileScan.hasNextLine())
			sar.add(fileScan.nextLine());
		return new BigIntegerList(sar);
	}

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the file name: ");
        String fileName = in.next();
        BigIntegerList bil = readBigIntegerFile(fileName);
        System.out.println("Min: " + bil.min(0, bil.getSize()-1));
    }
}
