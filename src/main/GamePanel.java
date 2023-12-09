package main;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;
import entity.*;
import tile.TileManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

	// Screen Settings
	final int scale = 4;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	// Tile Sprite Settings
	final int originalTileSize = 16;
	public final int tileSize = originalTileSize * scale;
	
	// Player Sprite Settings
	final int originalPlayerSizeX = 16;
	final int originalPlayerSizeY = 28;
	public final int playerSizeX = originalPlayerSizeX * scale;
	public final int playerSizeY = originalPlayerSizeY * scale;
	
	// More Screen Settings
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	final int fps = 60;
	
	// Skeleton Settings
	int spawnRate = 150;
	int spawnRateCounter = 0;
	
	// Score Settings
	int score;

	TileManager tileManager = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	LinkedList<Skeleton> skeletons = new LinkedList<>();
	LinkedList<Skull> skulls = new LinkedList<>();

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true); // Draws items off-screen before displaying, improves performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long fpsTimer = 0;
		@SuppressWarnings("unused")
		int fpsCount = 0;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			fpsTimer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				fpsCount++;
			}

			// FPS Counter
			if (fpsTimer >= 1000000000) {
				fpsCount = 0;
				fpsTimer = 0;
			}
		}
	}

	public void update() {
		// Spawn skeletons
		if (spawnRateCounter >= spawnRate) {
			skeletons.add(new Skeleton(this, player));
			spawnRateCounter = 0;
		} else {
			spawnRateCounter++;
		}
		
		// Run individual updates
		player.update();
		for (int x = 0; x < skeletons.size(); x++) {
			skeletons.get(x).update();
			if (skeletons.get(x).lifeSpan % 60 == 0) {
				skulls.add(new Skull(this, skeletons.get(x)));
			}
		}
		for (int x = 0; x < skulls.size(); x++) {
			skulls.get(x).update();
		}
		
		// Remove skeletons
		for (int x = skeletons.size() - 1; x >= 0; x--) {
			if (skeletons.get(x).lifeSpan >= skeletons.get(x).deathTime) {
				skeletons.remove(x);
				score++;
			}
		}
		
		// Remove skulls
		for (int x = skulls.size() - 1; x >= 0; x--) {
			if (skulls.get(x).lifeSpan >= skulls.get(x).deathTime) {
				skulls.remove(x);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		tileManager.draw(g2);
		player.draw(g2);
		for (int x = 0; x < skeletons.size(); x++) {
			skeletons.get(x).draw(g2);
		}

		for (int x = 0; x < skulls.size(); x++) {
			skulls.get(x).draw(g2);
		}
		
		
		g2.dispose();
	}

}
