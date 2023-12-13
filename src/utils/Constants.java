// A dedicated class to constants to allow easy changes.

package utils;

import main.Game;

public class Constants {

	public static class UI {
		public static class Buttons {
			public static final int BUTTON_DEFAULT_WIDTH = 64, BUTTON_DEFAULT_HEIGHT = 32;
			public static final int BUTTON_SCALED_WIDTH = (int) (BUTTON_DEFAULT_WIDTH * Game.SCALE);
			public static final int BUTTON_SCALED_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT * Game.SCALE);
		}

		public static class Hearts {
			public static final int HEART_DEFAULT_WIDTH = 13, HEART_DEFAULT_HEIGHT = 12;
			public static final int HEART_SCALED_WIDTH = (int) (HEART_DEFAULT_WIDTH * Game.SCALE);
			public static final int HEART_SCALED_HEIGHT = (int) (HEART_DEFAULT_HEIGHT * Game.SCALE);
			public static final int HEART_FILLED = 0, HEART_EMPTY = 1;
		}
	}

	public static class Directions {
		public static final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 0, RUNNING = 1, HIT = 2;

		public static int getSpriteAmount(int state) {
			switch (state) {
			case IDLE:
			case RUNNING:
			case HIT:
				return 4;
			default:
				return 1;
			}
		}
	}

	public static class EnemyConstants {
		public static final int SKELETON = 0, IDLE = 0, RUNNING = 1;
		public static final int SKELETON_DEFAULT_SIZE = 16;
		public static final int SKELETON_SCALED_SIZE = (int) (SKELETON_DEFAULT_SIZE * Game.SCALE);

		public static int getSpriteAmount(int enemyType, int state) {
			switch (enemyType) {
			case SKELETON:
				switch (state) {
				case IDLE:
				case RUNNING:
					return 4;
				default:
					return 1;
				}
			default:
				return 0;
			}

		}
	}

}
