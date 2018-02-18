package io.github.rainbowtaco.util;

import java.awt.image.BufferedImage;

public class ImageUtil {
	
	public static int getAverageColor(BufferedImage img) {
		long added = 0;
		for(int x = 0; x < img.getWidth(); x++)
			for(int y = 0; y < img.getHeight(); y++)
				added += img.getRGB(x, y);
		
		return (int) (added / (img.getHeight() * img.getWidth()));
	}
	
}
