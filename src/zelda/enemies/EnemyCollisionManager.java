package zelda.enemies;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class EnemyCollisionManager extends AdvanceCollisionGroup {
	 public EnemyCollisionManager() {
	        this.pixelPerfectCollision = false;
	    }
	    
	    public void collided(Sprite s1, Sprite s2) {
	        
	 
	    
	        this.revertPosition1();
	    }
}
