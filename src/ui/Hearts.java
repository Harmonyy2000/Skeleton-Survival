package ui;

import static utils.Constants.UI.Hearts.HEART_DEFAULT_HEIGHT;
import static utils.Constants.UI.Hearts.HEART_DEFAULT_WIDTH;
import static utils.Constants.UI.Hearts.HEART_SCALED_HEIGHT;
import static utils.Constants.UI.Hearts.HEART_SCALED_WIDTH;
import static utils.Constants.UI.Hearts.HEART_FILLED;
import static utils.Constants.UI.Hearts.HEART_EMPTY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Player;
import gamestates.GameState;
import utils.LoadSave;

public class Hearts {
	
	private Player player;
	private int posX, posY;
	private int[] heartStatus;
	private GameState state;
	private BufferedImage[] images;
	
	public Hearts(int posX, int posY, GameState state, Player player) {
		this.player = player;
		this.posX = posX;
		this.posY = posY;
		this.state = state;
		this.heartStatus = new int[player.getHealth()];
		System.out.println(heartStatus);
		importSprites();
	}
	
	public void update() {
		for (int i = 0; i < heartStatus.length; i++) {
			if (i <= player.getHealth() - 1) {
				heartStatus[i] = HEART_FILLED;
			} else {
				heartStatus[i] = HEART_EMPTY;
			}
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < heartStatus.length; i++) {
			g.drawImage(images[heartStatus[i]], posX + i * (HEART_SCALED_WIDTH + posX), posY, HEART_SCALED_WIDTH, HEART_SCALED_HEIGHT, null);
		}
	}

	private void importSprites() {
		images = new BufferedImage[2];
		BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.HEART_ATLAS);
		for (int i = 0; i < images.length; i++) {
			images[i] = image.getSubimage(i * HEART_DEFAULT_WIDTH, 0, HEART_DEFAULT_WIDTH, HEART_DEFAULT_HEIGHT);
		}
	}

}
