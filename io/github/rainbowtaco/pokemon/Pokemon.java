package io.github.rainbowtaco.pokemon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.github.rainbowtaco.util.MathUtil;

public class Pokemon {
	
	public float x, y, dx = 0, dy = 0; 
	public int width, height;
	public BufferedImage sprite;
	public StringBuilder extraAI = new StringBuilder();
	
	//public static Pokemon chespin;
	
	
	public Pokemon(int startX, int startY, String spritePath) {
		x = startX;
		y = startY;
		try {
			sprite = ImageIO.read(Pokemon.class.getResource(spritePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = sprite.getWidth();
		height = sprite.getHeight();
		for(int i = 0; i < 64; i++) {
			extraAI.append((char) (0 + i));//(char) Main.rand.nextInt(256));
		}
	}
	
	public Pokemon(int startX, int startY, String spritePath, int width, int height) {
		x = startX;
		y = startY;
		try {
			sprite = ImageIO.read(Pokemon.class.getResource(spritePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = width;
		this.height = height;
		for(int i = 0; i < 16; i++) {
			extraAI.append((char) 0);//Main.rand.nextInt(256));
		}
	}
	
	public void applyAI() {
		for(int i = 0; i < extraAI.length(); i++) {
			switch(extraAI.charAt(i)) {
			case 0:
				dx += extraAI.charAt(i + 1) / 255f;
				i++;
				break;
			case 1:
				dx -= extraAI.charAt(i + 1) / 255f;
				i++;
				break;
			case 2:
				dy += extraAI.charAt(i + 1) / 255f;
				i++;
				break;
			case 3:
				dy -= extraAI.charAt(i + 1) / 255f;
				i++;
				break;
			default:
				break;
			}
		}
	}
	
	public void update() {
		applyAI();
		dx = MathUtil.averageTimes(40, dx, 0);
		dy = MathUtil.averageTimes(40, dy, 0);
		x += dx;
		y += dy;
		if(x < 0)
			x = 0;
		if(x > 799 - width)
			x = 799 - width;
		if(y < 0)
			y = 0;
		if(y > 599 - height)
			y = 599 - height;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(sprite, (int) x, (int) y, width, height, null);
	}

}
