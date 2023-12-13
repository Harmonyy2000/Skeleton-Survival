// Imports the level sprites and draws them.
// TODO: Unit Testing
// TODO: Importing Sprites - Add more flexibility
// TODO: Error Handling - resource loading, out-of-bounds

package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {

	private BufferedImage[] levelSprites;
	private Level level;

	public LevelManager() {
		importSprites();
		level = new Level(LoadSave.getLevelData());
	}

	private void importSprites() {
		BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprites = new BufferedImage[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int index = i * 3 + j;
				levelSprites[index] = image.getSubimage(j * Game.TILE_DEFAULT_SIZE, i * Game.TILE_DEFAULT_SIZE, Game.TILE_DEFAULT_SIZE, Game.TILE_DEFAULT_SIZE);
			}
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
			for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
				int index = level.getSpriteIndex(j, i);
				g.drawImage(levelSprites[index], j * Game.TILE_SIZE, i * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE,
						null);
			}
		}
	}

	public Level getCurrentLevel() {
		return level;
	}

}
