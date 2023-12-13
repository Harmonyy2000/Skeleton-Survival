// When the game ends, this state displays the score and a quit button.
// TODO: Score
// TODO: Error Handling - User inputs

package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class GameOver extends State implements StateMethods {

	private MenuButton quitButton;
	private Playing playing;
	private int score;

	public GameOver(Game game, Playing playing) {
		super(game);
		this.playing = playing;
		loadButtons();
	}

	@Override
	public void update() {
		quitButton.update();
		this.score = playing.getScore();
	}

	@Override
	public void draw(Graphics g) {
		quitButton.draw(g);
		// Displaying the score
		Font scoreFont = new Font("Arial", Font.BOLD, 50);
		Color scoreColor = Color.BLACK; // Choose a color that's visible on your background
		drawText(g, "Score: " + score, (Game.GAME_WIDTH / 2) - 110, (Game.GAME_HEIGHT / 2) - 100, scoreColor,
				scoreFont); // Adjust position (x, y) as needed
	}

	private void loadButtons() {
		quitButton = new MenuButton(Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2, 2, GameState.QUIT);
	}

	private void resetButtons() {
		quitButton.resetBools();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// No uses at the moment
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// No uses at the moment
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		quitButton.setMouseOver(false);

		if (isInside(e, quitButton)) {
			quitButton.setMouseOver(true);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isInside(e, quitButton)) {
			quitButton.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isInside(e, quitButton)) {
			if (quitButton.isMousePressed()) {
				quitButton.applyGameState();
			}
		}
		resetButtons();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// No uses at the moment
	}

}
