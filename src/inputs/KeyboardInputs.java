// Handles keyboard inputs and routes the event to the current state.
// TODO: Unit Testing
// TODO: Strategy Patterns / Map of Handlers

package inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gamestates.GameOver;
import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;
import main.GamePanel;

public class KeyboardInputs extends KeyAdapter {

	private GameOver gameOver;
	private Menu menu;
	private Playing playing;

	public KeyboardInputs(GamePanel gamePanel) {
		if (gamePanel == null || gamePanel.getGame() == null) {
			throw new IllegalArgumentException("GamePanel or its game reference cannot be null");
		}

		this.gameOver = gamePanel.getGame().getGameOver();
		if (this.gameOver == null) {
			throw new IllegalStateException("GameOver cannot be null");
		}

		this.menu = gamePanel.getGame().getMenu();
		if (this.menu == null) {
			throw new IllegalStateException("Menu cannot be null");
		}
		
		this.playing = gamePanel.getGame().getPlaying();
		if (this.playing == null) {
			throw new IllegalStateException("Playing cannot be null");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (GameState.state) {
		case GAMEOVER:
			gameOver.keyPressed(e);
			break;
		case MENU:
			menu.keyPressed(e);
			break;
		case PLAYING:
			playing.keyPressed(e);
			break;
		case QUIT:
			// No uses at the moment
			break;
		default:
			System.err.println("keyPressed - Unhandled game state: " + GameState.state);
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (GameState.state) {
		case GAMEOVER:
			gameOver.keyReleased(e);
			break;
		case MENU:
			menu.keyReleased(e);
			break;
		case PLAYING:
			playing.keyReleased(e);
			break;
		case QUIT:
			// No uses at the moment
			break;
		default:
			System.err.println("keyReleased - Unhandled game state: " + GameState.state);
			break;

		}
	}

}
