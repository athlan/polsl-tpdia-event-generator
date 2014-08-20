//package pl.polsl;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class iterates over black-and-white image and finds the
 * most-top black pixel in each column and prints out its position on image
 * for each image column.
 * 
 * The number of columns is same as image width. The position is
 * bottom-related, that means the position od most black pixel is defined
 * as offset position from the bottom of image.
 * 
 * @author Athlan
 *
 */
public class DumpPlotDataFromBmp {
	
	public static void main(String[] argv) throws IOException {
		
		if(argv.length != 1) {
			throw new RuntimeException("Invalid numer of parameters. Excepted only one.");
		}
		
		String filePath = argv[0];
		BufferedImage img = ImageIO.read(new File(filePath));
		
		System.out.println(img.getWidth());
		System.out.println();

		int y_min;
		int y_min_value;
		int tmp_value;
		Color tmp_value_color;
		
		// iterate over image from left to right
		for (int x = 0; x < img.getWidth(); ++x) {
			
			// by default, the bottom pixel is a minimum
			y_min = img.getHeight();
			
			// white color (255) is minimum value, we expect black color (0) as max
			y_min_value = 255;
			
			// iterate image from top to bottom
			// the most-top black pixel is the minimum
			for(int y = 0; y < img.getHeight(); ++y) {
				tmp_value_color = new Color(img.getRGB(x, y));
				tmp_value = tmp_value_color.getRed();
				
				if(tmp_value < y_min_value) {
					y_min_value = tmp_value;
					y_min = y;
				}
			}
			
			// just because image (0,0) point is oriented in top-left, but not
			// bottom-left, we have to subtract height and y_min
			System.out.println(img.getHeight() - y_min);
		}
	}
}
