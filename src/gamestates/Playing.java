package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements StateMethods {

	private LevelManager levelManager;
	private Player player;
	private EnemyManager enemyManager;

	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, Game.PLAYER_SCALED_WIDTH, Game.PLAYER_SCALED_HEIGHT);
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		enemyManager = new EnemyManager(this);
	}

	@Override
	public void update() {
		levelManager.update();
		player.update();
		enemyManager.update();
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		enemyManager.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_ESCAPE:
			GameState.state = GameState.MENU;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void windowFocusLost() {
		player.resetDirectionBools();
	}

	public Player getPlayer() {
		return player;
	}

}
