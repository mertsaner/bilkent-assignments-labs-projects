import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Image {

	private BufferedImage img;
	private int height;
	private int width;

	public Image (String filePath) {
		img = null;
		try {	
			img = ImageIO.read(new File(filePath));
			height = img.getHeight();
			width  = img.getWidth();
		}
		catch (IOException e) {}
	} // Image
	
	public Image(int[][] r, int[][] g, int [][] b) {
		
		width= r.length;
		height=r[0].length;
		
		img = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color c = new Color (r[i][j],g[i][j],b[i][j]);
				img.setRGB(i, j, c.getRGB());
			} // for
		} // for
	} // Image
	
	public int[][] getRedChannel() {
		int[][] redPixels = new int[width][height];

		for( int i = 0; i < width; i++ ){
			for( int j = 0; j < height; j++ ) {
				Color c = new Color(img.getRGB(i,j));
				redPixels[i][j] = c.getRed();
			}//for
		}//for
		return redPixels;
	} //getRedChannel

	public int[][] getGreenChannel() {
		int[][] greenPixels = new int[width][height];

		for( int i = 0; i < width; i++ ) {
			for( int j = 0; j < height; j++ ) {
				Color c = new Color(img.getRGB(i,j));
				greenPixels[i][j] = c.getGreen();
			}//for
		}//for
		return greenPixels;
	} //getGreenChannel

	public int[][] getBlueChannel() {
		int[][] bluePixels = new int[width][height];

		for( int i = 0; i < width; i++ ){
			for( int j = 0; j < height; j++ ) {
				Color c = new Color(img.getRGB(i,j));
				bluePixels[i][j] = c.getBlue();
			}//for
		}//for
		return bluePixels;
	} // getBlueChannel

	public void display() {
		JFrame frame = new JFrame("WINDOW");
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.add(new JLabel(new ImageIcon(this.img)));
	} // display

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
} // class Image

