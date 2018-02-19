package io.github.rainbowtaco.util;

import java.awt.image.BufferedImage;

import io.github.rainbowtaco.world.Map;

public class ImageUtil {
	
	public static int getAverageColor(BufferedImage img) {
		long added = 0;
		for(int x = 0; x < img.getWidth(); x++)
			for(int y = 0; y < img.getHeight(); y++)
				added += img.getRGB(x, y);
		
		return (int) (added / (img.getHeight() * img.getWidth()));
	}
	
	public static void setMap(BufferedImage image) {
		for(int x = 0; x < 800; x++) {
			for(int y = 0; y < 600; y++) {
				Map.grid[x][y] = (image.getRGB(x, y) > 0x00ff00);
			}
		}
	}
	
}
