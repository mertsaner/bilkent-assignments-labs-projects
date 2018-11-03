/**
Author: Yasin Balcancý
 */
import java.util.Scanner;

public class ImageProccesing {
	
	public static int truncate(int arg) {
		if (arg > 255)
			arg = 255;
		else if (arg < 0) 
			arg = 0;
		return arg;
	}
	public static Image correctBrightness(Image im, int brightness) {
		int[][] red = im.getRedChannel();
		int[][] green = im.getGreenChannel();
		int[][] blue = im.getBlueChannel();
		for (int i = 0; i  < im.getWidth(); i++)
			for(int j = 0; j < im.getHeight(); j++){
				red[i][j] = truncate(red[i][j] + brightness);
				green[i][j] = truncate(green[i][j] + brightness);
				blue[i][j] = truncate(blue[i][j] + brightness);
			} // process of units - for
		Image modifiedImage = new Image(red, green, blue);
		return modifiedImage;
	} // sets the brightness
	public static Image correctGamma(Image im, int gamma){
		int[][] red = im.getRedChannel();
		int[][] green = im.getGreenChannel();
		int[][] blue = im.getBlueChannel();
		for (int i = 0; i  < im.getWidth(); i++)
			for(int j = 0; j < im.getHeight(); j++){
				red[i][j] = (int)(255*(Math.pow((double)red[i][j] / 255, 1.0/gamma)));
				green[i][j] = (int)(255*(Math.pow((double)green[i][j] / 255, 1.0/gamma)));
				blue[i][j] = (int)(255*(Math.pow((double)blue[i][j] / 255, 1.0/gamma)));
			} // process of units - for
		Image modifiedImage = new Image(red, green, blue);
		return modifiedImage;
	} // gamma settings

    public static void main(String[] args) {
       	Scanner in = new Scanner(System.in);
    	System.out.print("Enter the name of file: ");
    	String fileName = in.next(); // user types file name
    	Image unchangedImage = new Image(fileName);
		System.out.print("Enter brightness: ");
		int brightness = in.nextInt(); // user types brightness
		System.out.print("Enter gamma: ");
		int gamma = in.nextInt(); // user types gamma
		unchangedImage.display();
		correctBrightness(unchangedImage, brightness).display();
		correctGamma(unchangedImage, gamma).display();
    }
    
    
}