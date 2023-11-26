package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {

	GamePanel gamePanel;
	KeyHandler keyH;
	
	public Player(GamePanel gamePanel, KeyHandler keyH) {
		this.gamePanel = gamePanel;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
	}
	
	public void getPlayerImage() {
		try {
			hit = ImageIO.read(getClass().getResourceAsStream("/player/player_hit.png"));
			idle0 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle0.png"));
			idle1 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle1.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle2.png"));
			idle3 = ImageIO.read(getClass().getResourceAsStream("/player/player_idle3.png"));
			run0 = ImageIO.read(getClass().getResourceAsStream("/player/player_run0.png"));
			run1 = ImageIO.read(getClass().getResourceAsStream("/player/player_run1.png"));
			run2 = ImageIO.read(getClass().getResourceAsStream("/player/player_run2.png"));
			run3 = ImageIO.read(getClass().getResourceAsStream("/player/player_run3.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyH.upPressed) {
			y -= speed;
		}
		if (keyH.downPressed) {
			y += speed;
		}
		if (keyH.leftPressed) {
			flipped = true;
			x -= speed;
		}
		if (keyH.rightPressed) {
			flipped = false;
			x += speed;
		}
		
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			moving = true;
			animSpeed = 4;
		} else {
			moving = false;
			animSpeed = 10;
		}
		
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
		
		if (moving) {
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
		} else {
			switch (spriteNum) {
			case 0:
				image = idle0;
				break;
			case 1:
				image = idle1;
				break;
			case 2:
				image = idle2;
				break;
			case 3:
				image = idle3;
				break;
			}
		}
		
		if (flipped) {
			g2.drawImage(image, x + gamePanel.tileSizeX, y, -gamePanel.tileSizeX, gamePanel.tileSizeY, null);
		} else {
			g2.drawImage(image, x, y, gamePanel.tileSizeX, gamePanel.tileSizeY, null);
		}
	}
	
}
