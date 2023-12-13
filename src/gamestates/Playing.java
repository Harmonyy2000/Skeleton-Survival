// When the game is playing, this state displays the level, player, hearts, and enemies.
// TODO: Score
// TODO: Error Handling - User inputs

package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.Hearts;

public class Playing extends State implements StateMethods {

	private LevelManager levelManager;
	private Player player;
	private EnemyManager enemyManager;
	private Hearts hearts;
	private int score;

	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {
		levelManager = new LevelManager();
		player = new Player(200, 200, Game.PLAYER_SCALED_WIDTH, Game.PLAYER_SCALED_HEIGHT);
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		enemyManager = new EnemyManager(this);
		hearts = new Hearts(5, 5, player);
	}

	@Override
	public void update() {
		player.update();
		enemyManager.update();
		hearts.update();
		if (player.getHealth() <= 0) {
			GameState.state = GameState.GAMEOVER;
		}
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		enemyManager.draw(g);
		hearts.draw(g);
		// Displaying the score
		Font scoreFont = new Font("Arial", Font.BOLD, 20);
		Color scoreColor = Color.WHITE; // Choose a color that's visible on your background
		drawText(g, "Score: " + score, 10, 60, scoreColor, scoreFont); // Adjust position (x, y) as needed
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
		// No uses at the moment
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
		// No uses at the moment
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// No uses at the moment
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// No uses at the moment
	}

	public void windowFocusLost() {
		player.resetDirectionBools();
	}

	public Player getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
