package entities;

import static utils.Constants.EnemyConstants.RUNNING;
import static utils.Constants.EnemyConstants.getSpriteAmount;

import main.Game;

public abstract class Enemy extends Entity {

	private Player player;
	private int animTick, animIndex, animSpeed = 30;
	private int state, enemyType;
	private float speed = 0.3f * Game.SCALE;
	private long spawnTime;

	public Enemy(float x, float y, int width, int height, int enemyType, Player player) {
		super(x, y, width, height);
		this.player = player;
		this.enemyType = enemyType;
		this.spawnTime = System.currentTimeMillis();
		this.state = RUNNING;
		initHitbox(x, y, (int) (8 * Game.SCALE), (int) (12 * Game.SCALE));
	}

	public void update() {
		updatePos();
		updateAnimation();
	}

	private void updateAnimation() {
		animTick++;
		if (animTick >= animSpeed) {
			animTick = 0;
			animIndex++;
			if (animIndex >= getSpriteAmount(enemyType, state)) {
				animIndex = 0;
			}
		}
	}

	public void updatePos() {
		float targetX = player.getHitbox().x;
		float targetY = player.getHitbox().y;
		double angle = Math.atan2(targetY - hitbox.y, targetX - hitbox.x);

		hitbox.x += speed * Math.cos(angle);
		hitbox.y += speed * Math.sin(angle);
	}

	public int getAnimIndex() {
		return animIndex;
	}

	public int getState() {
		return state;
	}

	public long getSpawnTime() {
		return spawnTime;
	}

}
