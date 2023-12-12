package utils;

import main.Game;

public class HelperMethods {

	public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
		if (!isSolid(x, y, levelData) && !isSolid(x + width, y + height, levelData) && !isSolid(x + width, y, levelData)
				&& !isSolid(x, y + height, levelData)) {
			return true;
		}
		return false;
	}

	private static boolean isSolid(float x, float y, int[][] levelData) {
		if (x < 0 || x >= Game.GAME_WIDTH || y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}

		float indexX = x / Game.TILE_SIZE;
		float indexY = y / Game.TILE_SIZE;
		int value = levelData[(int) indexY][(int) indexX];

		if (value < 0 || value > 7) {
			return true;
		}

		return false;
	}

}
