package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	
	// Game Panel
	GamePanel gamePanel;
	
	// Life
	public int health, lifeSpan, deathTime;
	
	// Movement
	public int x, y, speed;
	
	// Sprites
	public BufferedImage hit, idle0, idle1, idle2, idle3, run0, run1, run2, run3, skull;
	public boolean moving, flipped;
	
	// Animations
	public int spriteCounter = 0;
	public int spriteNum = 0;
	public int animSpeed;
	
}
