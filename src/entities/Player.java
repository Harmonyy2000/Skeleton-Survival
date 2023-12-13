package entities;

import static utils.Constants.PlayerConstants.HIT;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.getSpriteAmount;
import static utils.HelperMethods.canMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class Player extends Entity {

	private BufferedImage[][] animations;
	private int animTick, animIndex, animSpeed = 30;
	private int state = IDLE;
	private boolean up, left, down, right, moving = false, hit = false;
	private float drawOffsetX = 3 * Game.SCALE;
	private float drawOffsetY = 14 * Game.SCALE;
	private float speed = 0.5f * Game.SCALE;
	private float normalizedSpeed = (float) (speed / Math.sqrt(Math.pow(speed, 2) + Math.pow(speed, 2)));
	private int[][] levelData;
	private int health = 5;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		importSprites();
		initHitbox(x, y, (int) (12 * Game.SCALE), (int) (12 * Game.SCALE));
	}

	public void update() {
		updatePos();
		updateAnimation();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[state][animIndex], (int) (hitbox.x - drawOffsetX), (int) (hitbox.y - drawOffsetY), width,
				height, null);
	}

	private void importSprites() {
		animations = new BufferedImage[3][4];
		BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[i].length; j++) {
				animations[i][j] = image.getSubimage(j * Game.PLAYER_DEFAULT_WIDTH, i * Game.PLAYER_DEFAULT_HEIGHT,
						Game.PLAYER_DEFAULT_WIDTH, Game.PLAYER_DEFAULT_HEIGHT);
			}
		}
	}

	private void setAnimation() {
		int startAnim = state;

		if (moving)
			state = RUNNING;
		else
			state = IDLE;

		if (hit)
			state = HIT;

		if (startAnim != state)
			resetAnimation();
	}

	private void resetAnimation() {
		animTick = 0;
		animIndex = 0;
	}

	private void updateAnimation() {
		animTick++;
		if (animTick >= animSpeed) {
			animTick = 0;
			animIndex++;
			if (animIndex >= getSpriteAmount(state)) {
				animIndex = 0;
				hit = false;
			}
		}
	}

	public void updatePos() {
		moving = false;

		if (!up && !left && !down && !right)
			return;

		float speedX = 0, speedY = 0;

		if (up)
			speedY -= speed;
		if (left)
			speedX -= speed;
		if (down)
			speedY += speed;
		if (right)
			speedX += speed;

		if (Math.abs(speedX) > 0 && Math.abs(speedY) > 0) {
			speedX *= normalizedSpeed;
			speedY *= normalizedSpeed;
		}

		if (canMoveHere(hitbox.x + speedX, hitbox.y, hitbox.width, hitbox.height, levelData)) {
			hitbox.x += speedX;
			moving = true;
		}

		if (canMoveHere(hitbox.x, hitbox.y + speedY, hitbox.width, hitbox.height, levelData)) {
			hitbox.y += speedY;
			moving = true;
		}
	}

	public void resetDirectionBools() {
		up = false;
		left = false;
		down = false;
		right = false;
	}

	public void loadLevelData(int[][] levelData) {
		this.levelData = levelData;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
		health--;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public int getHealth() {
		return health;
	}

}
