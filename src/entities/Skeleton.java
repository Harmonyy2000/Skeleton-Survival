package entities;

import static utils.Constants.EnemyConstants.SKELETON;
import static utils.Constants.EnemyConstants.SKELETON_SCALED_SIZE;

public class Skeleton extends Enemy {

	public Skeleton(float x, float y, Player player) {
		super(x, y, SKELETON_SCALED_SIZE, SKELETON_SCALED_SIZE, SKELETON, player);
	}

}
