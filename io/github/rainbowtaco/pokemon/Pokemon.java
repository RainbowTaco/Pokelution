package io.github.rainbowtaco.pokemon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import io.github.rainbowtaco.util.MathUtil;
import io.github.rainbowtaco.world.Map;
import sun.misc.JavaSecurityAccess;

public class Pokemon {
	
	public float x, y, dx = 0, dy = 0; 
	public int width, height;
	public BufferedImage sprite;
	public StringBuilder extraAI = new StringBuilder();
	public StringBuilder genes = new StringBuilder();
	public int energy;
	public int maxEnergy;
	public int health;
	public int maxHealth;
	public boolean eyes;
	public float water;
	public float land;
	
	//public static Pokemon chespin;
	public boolean lookingAt;
	
	public Pokemon(int startX, int startY, String spritePath, Random rand) {
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
			extraAI.append((char) rand.nextInt(256));
		}
	}
	
	public Pokemon(int startX, int startY, String spritePath, int width, int height, Random rand) {
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
			extraAI.append((char) rand.nextInt(256));
		}
	}
	
	public void applyAI() {
		for(int i = 0; i < extraAI.length(); i++) {
			switch(extraAI.charAt(i)) {
			case 0:
				dx += extraAI.charAt(++i) / 255f;
				i++;
				break;
			case 1:
				dx -= extraAI.charAt(++i) / 255f;
				i++;
				break;
			case 2:
				dy += extraAI.charAt(++i) / 255f;
				i++;
				break;
			case 3:
				dy -= extraAI.charAt(++i) / 255f;
				i++;
				break;
			case 4:
				dx *= extraAI.charAt(++i) / 255f;
				break;
			case 5:
				dx /= extraAI.charAt(++i) / 255f;
				break;
			case 6:
				dy *= extraAI.charAt(++i) / 255f;
				break;
			case 7:
				dy /= extraAI.charAt(++i) / 255f;
				break;
			case 8: 
				dy = dx;
				break;
			case 9:
				dx = dy;
				break;
			case 10:
				dy = extraAI.charAt(++i) / 255 * dx;
				break;
			case 11:
				dx = extraAI.charAt(++i) / 255 * dy;
				break;
			case 12:
				dy = extraAI.charAt(++i) / 255 / dx;
				break;
			case 13:
				dx = extraAI.charAt(++i) / 255 / dy;
				break;
			case 14:
				dx += dy;
				break;
			case 15:
				dx -= dy;
				break;
			case 16:
				dy += dx;
				break;
			case 17:
				dy -= dx;
				break;
			case 18:
				//the looking character
				if(eyes)
					lookingAt = Map.grid[(int) x][(int) y];
				break;
			case 19:
				lookingAt ? dx *= land : dx *= water;
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
