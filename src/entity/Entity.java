package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y, speed;
	public BufferedImage hit, idle0, idle1, idle2, idle3, run0, run1, run2, run3;
	public boolean moving, flipped;
	
	public int spriteCounter = 0;
	public int spriteNum = 0;
	public int animSpeed;
	
}
