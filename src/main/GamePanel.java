package main;

import java.awt.*;
import javax.swing.*;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {

	// Screen Settings
	final int originalTileSizeX = 16; // Represents a 16x16 tile
	final int originalTileSizeY = 28;
	final int scale = 3;
	public final int tileSizeX = originalTileSizeX * scale; // Scales up to a 48x48 tile
	public final int tileSizeY = originalTileSizeY * scale; // Scales up to a 48x48 tile
	final int maxScreenCol = 14;
	final int maxScreenRow = 8;
	final int screenWidth = tileSizeX * maxScreenCol; // 672 Pixels
	final int screenHeight = tileSizeY * maxScreenRow; // 672 Pixels

	int fps = 60;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);

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
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		player.draw(g2);
		
		g2.dispose();
	}

}
