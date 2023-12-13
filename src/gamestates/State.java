// Shared variables and methods between states.

package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {

	protected Game game;

	public State(Game game) {
		this.game = game;
	}

	public boolean isInside(MouseEvent e, MenuButton button) {
		return button.getBounds().contains(e.getX(), e.getY());
	}

	public Game getGame() {
		return game;
	}

	public void drawText(Graphics g, String text, int x, int y, Color color, Font font) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}

}
