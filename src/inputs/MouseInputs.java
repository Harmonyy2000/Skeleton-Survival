// Handles mouse inputs and routes the event to the current state.
// TODO: Unit Testing
// TODO: Strategy Patterns / Map of Handlers

package inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gamestates.GameOver;
import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;
import main.GamePanel;

public class MouseInputs extends MouseAdapter {

	private GameOver gameOver;
	private Menu menu;
	private Playing playing;

	public MouseInputs(GamePanel gamePanel) {
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
	public void mousePressed(MouseEvent e) {
		switch (GameState.state) {
		case GAMEOVER:
			gameOver.mousePressed(e);
			break;
		case MENU:
			menu.mousePressed(e);
			break;
		case PLAYING:
			playing.mousePressed(e);
			break;
		case QUIT:
			// No uses at the moment
			break;
		default:
			System.err.println("mousePressed - Unhandled game state: " + GameState.state);
			break;

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameState.state) {
		case GAMEOVER:
			gameOver.mouseReleased(e);
			break;
		case MENU:
			menu.mouseReleased(e);
			break;
		case PLAYING:
			playing.mouseReleased(e);
			break;
		case QUIT:
			// No uses at the moment
			break;
		default:
			System.err.println("mouseReleased - Unhandled game state: " + GameState.state);
			break;

		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameState.state) {
		case GAMEOVER:
			gameOver.mouseMoved(e);
			break;
		case MENU:
			menu.mouseMoved(e);
			break;
		case PLAYING:
			playing.mouseMoved(e);
			break;
		case QUIT:
			// No uses at the moment
			break;
		default:
			System.err.println("mouseMoved - Unhandled game state: " + GameState.state);
			break;

		}
	}

}
