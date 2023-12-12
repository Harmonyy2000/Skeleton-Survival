package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;

	public GamePanel(Game game) {
		this.game = game;
		mouseInputs = new MouseInputs(this);
		setPanelSize();
		requestFocusInWindow();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		setPreferredSize(size);
		System.out.println(Game.GAME_WIDTH + "x" + Game.GAME_HEIGHT);
	}

	public void updateGame() {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	public Game getGame() {
		return game;
	}

}
