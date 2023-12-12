package gamestates;

import static utils.Constants.UI.Buttons.BUTTON_SCALED_HEIGHT;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class Menu extends State implements StateMethods {

	private MenuButton[] buttons = new MenuButton[2];

	public Menu(Game game) {
		super(game);
		loadButtons();
	}

	@Override
	public void update() {
		for (MenuButton button : buttons)
			button.update();
	}

	@Override
	public void draw(Graphics g) {
		for (MenuButton button : buttons)
			button.draw(g);
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2,
				(Game.GAME_HEIGHT / 2) - (BUTTON_SCALED_HEIGHT / 2) - BUTTON_SCALED_HEIGHT, 0, GameState.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2,
				(Game.GAME_HEIGHT / 2) - (BUTTON_SCALED_HEIGHT / 2) + BUTTON_SCALED_HEIGHT, 2, GameState.QUIT);
	}

	private void resetButtons() {
		for (MenuButton button : buttons) {
			button.resetBools();
		}
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
		for (MenuButton button : buttons) {
			button.setMouseOver(false);
		}

		for (MenuButton button : buttons) {
			if (isInside(e, button)) {
				button.setMouseOver(true);
				break;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButton button : buttons) {
			if (isInside(e, button)) {
				button.setMousePressed(true);
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton button : buttons) {
			if (isInside(e, button)) {
				if (button.isMousePressed()) {
					button.applyGameState();
				}
				break;
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
