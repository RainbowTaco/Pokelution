package io.github.rainbowtaco;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.rainbowtaco.pokemon.Pokemon;
import io.github.rainbowtaco.world.Map;

@SuppressWarnings("serial")
public class Main extends JPanel implements Runnable {
	
	public static final String APPNAME = "Pokelution", VERSION = "pre-alpha 0";
	public static final int WIDTH = 800, HEIGHT = 600;
	private BufferedImage image;
	private Graphics2D g;
	public static final long seed = System.currentTimeMillis();
	public static final Random rand = new Random(seed);
	public Pokemon p;
	private Thread thread;
	private int tick = 0;
	private Map map;
	
	public static void main(String[] args) {
		JFrame w = new JFrame(APPNAME + " " + VERSION);
		w.add(new Main());
		w.pack();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setLocationRelativeTo(null);
		w.setResizable(true);
		w.setVisible(true);
	}
	
	public Main() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		p = new Pokemon(400, 300, "/textures/pokemon/chespin.png", rand);
		map = new Map(rand);
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		init();
		while(true) {
			update();
			draw();
			doDuDo();
		}
	}
	
	private void update() {
		map.update();
		p.update();
		tick++;
	}
	
	private void draw() {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		map.draw(image);
		p.draw(g);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.setColor(Color.black);
		g.drawString(p.extraAI.toString(), 10, 20);
		g.drawString("Tick: " + tick, 10, 40);
		g.drawString("Seconds: " + (System.currentTimeMillis() - seed) / 1000, 10, 60);
	}
	
	public void doDuDo() {
		Graphics g = this.getGraphics();
		g.drawImage(image, 0, 0, super.getWidth(), super.getHeight(), null);
		g.dispose();
	}
	
}

