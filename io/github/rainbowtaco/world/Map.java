package io.github.rainbowtaco.world;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Map {
	
	/**
	 * the grid, 
	 * if it's true it's solid/green
	 */
	public static boolean[][] grid = new boolean[800][600];
	
	public Map(Random rand) {
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				grid[x][y] = rand.nextBoolean();
			}
		}
	}
	
	public void update() {
		
	}
	
	public void draw(BufferedImage b) {
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				b.setRGB(x, y, (grid[x][y]) ? 0x00ff00 : 0x0000ff);
			}
		}
	}

}
