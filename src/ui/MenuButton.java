package ui;

import static utils.Constants.UI.Buttons.BUTTON_DEFAULT_HEIGHT;
import static utils.Constants.UI.Buttons.BUTTON_DEFAULT_WIDTH;
import static utils.Constants.UI.Buttons.BUTTON_SCALED_HEIGHT;
import static utils.Constants.UI.Buttons.BUTTON_SCALED_WIDTH;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.GameState;
import utils.LoadSave;

public class MenuButton {

	private int posX, posY, rowIndex, index;
	private GameState state;
	private BufferedImage[] images;
	private int offsetXCenter = BUTTON_SCALED_WIDTH / 2;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;

	public MenuButton(int posX, int posY, int rowIndex, GameState state) {
		this.posX = posX;
		this.posY = posY;
		this.rowIndex = rowIndex;
		this.state = state;
		initBounds();
		importSprites();
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;
	}

	public void draw(Graphics g) {
		g.drawImage(images[index], posX - offsetXCenter, posY, BUTTON_SCALED_WIDTH, BUTTON_SCALED_HEIGHT, null);
	}

	private void initBounds() {
		bounds = new Rectangle(posX - offsetXCenter, posY, BUTTON_SCALED_WIDTH, BUTTON_SCALED_HEIGHT);
	}

	private void importSprites() {
		images = new BufferedImage[3];
		BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTON_ATLAS);
		for (int i = 0; i < images.length; i++) {
			images[i] = image.getSubimage(i * BUTTON_DEFAULT_WIDTH, rowIndex * BUTTON_DEFAULT_HEIGHT,
					BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT);
		}
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void applyGameState() {
		GameState.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

}
