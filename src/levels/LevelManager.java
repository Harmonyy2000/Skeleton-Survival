package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {

	@SuppressWarnings("unused")
	private Game game;
	private BufferedImage[] levelSprite;
	private Level level;

	public LevelManager(Game game) {
		this.game = game;
		importSprites();
		level = new Level(LoadSave.getLevelData());
	}

	private void importSprites() {
		BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int index = i * 3 + j;
				levelSprite[index] = image.getSubimage(j * 16, i * 16, 16, 16);
			}
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
			for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
				int index = level.getSpriteIndex(j, i);
				g.drawImage(levelSprite[index], j * Game.TILE_SIZE, i * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE,
						null);
			}
		}
	}

	public void update() {

	}

	public Level getCurrentLevel() {
		return level;
	}

}
