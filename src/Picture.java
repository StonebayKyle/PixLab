import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	/** Method to keep only blue */
	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
	}

	/** Method to negate colors */
	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(255 - pixelObj.getRed());
				pixelObj.setGreen(255 - pixelObj.getGreen());
				pixelObj.setBlue(255 - pixelObj.getBlue());
			}
		}
	}

	/** Method to grayscale */
	public void grayscale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int averageColor = (pixelObj.getRed() + pixelObj.getGreen() +
										pixelObj.getBlue()) / 3;
				pixelObj.setRed(averageColor);
				pixelObj.setGreen(averageColor);
				pixelObj.setBlue(averageColor);
			}
		}
	}

	/** Method to fixUnderWater */
	public void fixUnderWater() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(pixelObj.getRed()+90);
			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorVerticalRightToLeft() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	public void mirrorHorizontal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length / 2; row++) {
			for (int col = 0; col < width; col++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[pixels.length - 1 - row][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}
	
	public void mirrorHorizontalBotToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length / 2; row++) {
			for (int col = 0; col < width; col++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[pixels.length - 1 - row][col];
				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}

	public void mirrorDiagonal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel bottomLeftPixel = null;
		Pixel topRightPixel = null;
		int height = pixels.length;
		int width = pixels[0].length;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				try {
					bottomLeftPixel = pixels[col][row];
					topRightPixel = pixels[row][col];
					topRightPixel.setColor(bottomLeftPixel.getColor());
					
				} catch (Exception e) {
					//TODO: handle exception
				}
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
				count++;
			}
		}
		System.out.println(count);
	}

	public void mirrorArms() {
		int mirrorPoint = 195;
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 164; row < mirrorPoint; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 104; col < 290; col++) {

				topPixel = pixels[row][col];
				bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}

	public void mirrorGull() {
		int mirrorPoint = 353;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 230; row < 325; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 235; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	public void copy(Picture fromPic, int fromStartRow, int fromStartCol, int fromEndRow, int fromEndCol, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();

		for (int fromRow = fromStartRow, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length && fromRow < fromEndRow; fromRow++, toRow++) {
			for (int fromCol = fromStartCol, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length && fromCol < fromEndCol; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}
	
	public void createMyCollage() {
		Picture butterfly1 = new Picture("butterfly1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		Picture swan = new Picture("swan.jpg");
		this.copy(butterfly1, 0, 0);
		zeroBlue();
		this.copy(flower2, 60, 60);
		this.copy(swan, 100, 100);

		mirrorDiagonal();
		mirrorVertical();
		mirrorHorizontal();
		mirrorDiagonal();
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel currentPixel = null;
		Pixel rightPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				currentPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				bottomPixel = pixels[row + 1][col];
				if (currentPixel.colorDistance(rightPixel.getColor()) > edgeDist ||
					currentPixel.colorDistance(bottomPixel.getColor()) > edgeDist)
					currentPixel.setColor(Color.BLACK);
				else
					currentPixel.setColor(Color.WHITE);
			}
		}
	}
	
	public void edgeDetection2(int edgeDist) {
		Pixel currentPixel = null;
		Pixel rightPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				currentPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				bottomPixel = pixels[row + 1][col];
				if (Math.abs(currentPixel.getAverage() - rightPixel.getAverage()) > edgeDist ||
					Math.abs(currentPixel.getAverage() - bottomPixel.getAverage()) > edgeDist)
					currentPixel.setColor(Color.BLACK);
				else
					currentPixel.setColor(Color.WHITE);
			}
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

} // this } is the end of class Picture, put all new methods before this
