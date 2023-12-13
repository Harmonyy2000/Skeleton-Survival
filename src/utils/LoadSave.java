// Provides a way to get sprites and generate a new random level.
// TODO: Error Handling - resource loading
// TODO: Unit Testing
// TODO: Singleton Pattern

package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String SKELETON_ATLAS = "skeleton_sprites.png";
	public static final String LEVEL_ATLAS = "level_sprites.png";
	public static final String MENU_BUTTON_ATLAS = "menu_button_sprites.png";
	public static final String HEART_ATLAS = "heart_sprites.png";

	public static BufferedImage getSpriteAtlas(String fileName) {
		BufferedImage image = null;

		try (InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);) {
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public static int[][] getLevelData() {
		Random random = new Random();
		int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

		for (int i = 0; i < levelData.length; i++) {
			for (int j = 0; j < levelData[i].length; j++) {
				int sprite = random.nextInt(21);
				if (sprite > 7) {
					sprite = 0;
				}
				levelData[i][j] = sprite;
			}
		}

		return levelData;
	}

}
