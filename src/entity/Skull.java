package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Skull extends Entity {

	GamePanel gamePanel;
	public int targetX, targetY;
	public double angle;

	public Skull(GamePanel gamePanel, Skeleton skeleton) {
		this.gamePanel = gamePanel;
		setDefaultValues(skeleton);
		getProjectileImage();
	}

	private void setDefaultValues(Skeleton skeleton) {
		speed = 5;
		x = skeleton.x;
		y = skeleton.y;
		targetX = skeleton.player.x;
		targetY = skeleton.player.y;
		angle = Math.atan2(targetY-y, targetX-x);
		
		if (x > targetX) {
			targetX -= gamePanel.screenWidth;
			flipped = false;
		} else {
			targetX += gamePanel.screenWidth;
			flipped = true;
		}
		
		if (y > targetY) {
			targetY -= gamePanel.screenHeight;
		} else {
			targetY += gamePanel.screenHeight;
		}
		
		lifeSpan = 0;
		deathTime = 300;
	}

	private void getProjectileImage() {
		try {
			skull = ImageIO.read(getClass().getResourceAsStream("/skeleton/skull.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		lifeSpan++;

		y += speed * Math.sin(angle);
		x += speed * Math.cos(angle);
	}

	public void draw(Graphics2D g2) {
		// Draw sprite
		if (flipped) {
			g2.drawImage(skull, x + gamePanel.tileSize, y, -gamePanel.tileSize, gamePanel.tileSize, null);
		} else {
			g2.drawImage(skull, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
		}
	}

}
