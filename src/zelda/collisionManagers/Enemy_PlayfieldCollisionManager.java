package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.enemies.AbstractEnemy;

public class Enemy_PlayfieldCollisionManager extends AdvanceCollisionGroup {

	private AbstractEnemy enemy;
	
    public Enemy_PlayfieldCollisionManager(AbstractEnemy enemy) {
        this.pixelPerfectCollision = false;
        this.enemy = enemy;
    }
    
    public void collided(Sprite s1, Sprite s2) {
        this.revertPosition1();
    }
}
