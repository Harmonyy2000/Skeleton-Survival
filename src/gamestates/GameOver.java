package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class GameOver extends State implements StateMethods {
	
	private MenuButton quitButton;

	public GameOver(Game game) {
		super(game);
		loadButtons();
	}

	@Override
	public void update() {
		quitButton.update();
	}

	@Override
	public void draw(Graphics g) {
		quitButton.draw(g);
	}

	private void loadButtons() {
		quitButton = new MenuButton(Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2, 2, GameState.QUIT);
	}

	private void resetButtons() {
		quitButton.resetBools();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
