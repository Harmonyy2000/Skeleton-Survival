package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Skeleton extends Entity {
	
	Player player;
	
	public Skeleton(GamePanel gamePanel, Player player) {
		this.gamePanel = gamePanel;
		this.player = player;
		setDefaultValues();
		getEnemyImage();
	}
	
	private void setDefaultValues() {
		x = (int) (Math.random() * gamePanel.screenWidth);
		y = (int) (Math.random() * gamePanel.screenHeight);
		speed = 1;
		animSpeed = 10;
		lifeSpan = 0;
		deathTime = (int) (Math.random() * 1000);
	}
	
	private void getEnemyImage() {
		try {
			idle0 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_idle0.png"));
			idle1 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_idle1.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_idle2.png"));
			idle3 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_idle3.png"));
			run0 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_run0.png"));
			run1 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_run1.png"));
			run2 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_run2.png"));
			run3 = ImageIO.read(getClass().getResourceAsStream("/skeleton/skeleton_run3.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		// Increase skeleton's lifespan
		lifeSpan++;
		
		// Movement towards player
		if (y > player.y) {
			y -= speed;
		}
		if (y < player.y) {
			y += speed;
		}
		if (x > player.x) {
			flipped = true;
			x -= speed;
		}
		if (x < player.x) {
			flipped = false;
			x += speed;
		}
		
		// Change sprite for animation
		spriteCounter++;
		if (spriteCounter > animSpeed) {
			spriteNum++;
			if (spriteNum >= 4) {
				spriteNum = 0;
			}
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		// Set which movement or idle image
		switch (spriteNum) {
			case 0:
				image = run0;
				break;
			case 1:
				image = run1;
				break;
			case 2:
				image = run2;
				break;
			case 3:
				image = run3;
				break;
		}
		
		if (flipped) {
			g2.drawImage(image, x + gamePanel.tileSize, y, -gamePanel.tileSize, gamePanel.tileSize, null);
		} else {
			g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
		}
	}
	
}
