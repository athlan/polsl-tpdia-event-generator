//package pl.polsl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class DumpPlotDataFromBmp {
	
	public static void main(String[] argv) {
		
		if(argv.length != 1) {
			throw new RuntimeException("Invalid numer of parameters. Excepted only one.");
		}
		
		String filePath = argv[0];
		
		BufferedImage img = ImageIO.load(filePath);
		int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
		
		System.out.println("Hello! Done.");
	}
}
