package entities;

import static utils.Constants.EnemyConstants.SKELETON_DEFAULT_SIZE;
import static utils.Constants.EnemyConstants.SKELETON_SCALED_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import gamestates.Playing;
import main.Game;
import utils.LoadSave;

public class EnemyManager {

	private Playing playing;
	private Player player;
	private BufferedImage[][] animations;
	private ArrayList<Skeleton> skeletons = new ArrayList<>();
	private Random random = new Random();
	private int spawnX, spawnY, spawnCount = 0, spawnMax = 5, spawnIncreaseRate = 30;
	private double spawnRate = 10.0, deathRate = 30;
	private int score, scoreIncrease = 1;
	private long currentTime, lastSpawn, lastIncrease;
	private float drawOffsetX = 4 * Game.SCALE;
	private float drawOffsetY = 3 * Game.SCALE;

	public EnemyManager(Playing playing) {
		this.playing = playing;
		this.player = playing.getPlayer();
		this.currentTime = System.currentTimeMillis();
		this.lastSpawn = currentTime;
		this.lastIncrease = currentTime;
		importSprites();
	}

	public void update() {
		currentTime = System.currentTimeMillis();

		if (currentTime - lastIncrease >= spawnIncreaseRate * 1000)
			spawnRateIncrease();

		if (spawnCount < spawnMax && currentTime - lastSpawn >= spawnRate * 1000)
			spawnSkeleton();

		for (int i = skeletons.size() - 1; i >= 0; i--) {
			Skeleton skeleton = skeletons.get(i);
			if (currentTime - skeleton.getSpawnTime() >= deathRate * 1000)
				killSkeleton(skeleton);
		}

		for (Skeleton skeleton : skeletons) {
			skeleton.update();
			if (skeleton.getHitbox().intersects(player.getHitbox())) {
				player.setHit(true);
				player.resetDirectionBools();
				skeletons.clear();
				score -= 1 * scoreIncrease;
				break;
			}
		}
	}

	public void draw(Graphics g) {
		drawSkeletons(g);
	}

	private void drawSkeletons(Graphics g) {
		for (Skeleton skeleton : skeletons) {
			g.drawImage(animations[skeleton.getState()][skeleton.getAnimIndex()],
					(int) (skeleton.getHitbox().x - drawOffsetX), (int) (skeleton.getHitbox().y - drawOffsetY),
					SKELETON_SCALED_SIZE, SKELETON_SCALED_SIZE, null);
			skeleton.drawHitbox(g);
		}
	}

	private void importSprites() {
		animations = new BufferedImage[2][4];
		BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.SKELETON_ATLAS);

		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[i].length; j++) {
				animations[i][j] = image.getSubimage(j * SKELETON_DEFAULT_SIZE, i * SKELETON_DEFAULT_SIZE,
						SKELETON_DEFAULT_SIZE, SKELETON_DEFAULT_SIZE);
			}
		}
	}

	public void spawnRateIncrease() {
		spawnRate *= 0.9;
		deathRate *= 1.1;
		spawnMax++;
		scoreIncrease++;
		lastIncrease = currentTime;
	}

	public void spawnSkeleton() {
		do {
			spawnX = random.nextInt((int) -(Game.GAME_WIDTH * 1.5), (int) (Game.GAME_WIDTH * 1.5));
		} while (spawnX >= -SKELETON_SCALED_SIZE && spawnX <= Game.GAME_WIDTH);
		do {
			spawnY = random.nextInt((int) -(Game.GAME_HEIGHT * 1.5), (int) (Game.GAME_HEIGHT * 1.5));
		} while (spawnY >= -SKELETON_SCALED_SIZE && spawnY <= Game.GAME_HEIGHT);
		skeletons.add(new Skeleton(spawnX, spawnY, playing.getPlayer()));
		spawnCount++;
		lastSpawn = currentTime;
	}

	public void killSkeleton(Skeleton skeleton) {
		spawnCount--;
		score += 1 * scoreIncrease;
		skeletons.remove(skeleton);
	}

}
