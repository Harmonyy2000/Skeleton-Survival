package main;

import java.awt.Graphics;

import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable {

	// Objects
	private GamePanel gamePanel;
	@SuppressWarnings("unused")
	private GameWindow gameWindow;
	private Menu menu;
	private Playing playing;
	private Thread gameThread;

	// Game Settings
	private final int FPS = 120, UPS = 200;

	// Graphics Settings
	public static final float SCALE = 3.0f;
	public static final int PLAYER_DEFAULT_WIDTH = 16, PLAYER_DEFAULT_HEIGHT = 28;
	public static final int PLAYER_SCALED_WIDTH = (int) (PLAYER_DEFAULT_WIDTH * SCALE);
	public static final int PLAYER_SCALED_HEIGHT = (int) (PLAYER_DEFAULT_HEIGHT * SCALE);
	public static final int TILES_IN_WIDTH = 24, TILES_IN_HEIGHT = 18, TILE_DEFAULT_SIZE = 16;
	public static final int TILE_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
	public static final int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;

	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocusInWindow();
		startGameLoop();
	}

	private void initClasses() {
		menu = new Menu(this);
		playing = new Playing(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		switch (GameState.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case OPTIONS:
		case QUIT:
			System.exit(0);
		default:
			break;
		}
	}

	public void render(Graphics g) {
		switch (GameState.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
		long lastCheck = System.currentTimeMillis();
		long previousTime = System.nanoTime();

		int frames = 0, updates = 0;
		double deltaU = 0, deltaF = 0;
		double timePerUpdate = 1000000000.0 / UPS;
		double timePerFrame = 1000000000.0 / FPS;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public void windowFocusLost() {
		if (GameState.state == GameState.PLAYING)
			playing.getPlayer().resetDirectionBools();
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

}
