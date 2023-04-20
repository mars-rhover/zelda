package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.enemies.AbstractEnemy;

public class testCollisionManager extends AdvanceCollisionGroup {
	
	public testCollisionManager() {
        this.pixelPerfectCollision = false;
    }
	
	@Override
	public void collided(Sprite s1, Sprite s2) {
		System.out.println(s1+" a collide avec "+s2);
	}

}
