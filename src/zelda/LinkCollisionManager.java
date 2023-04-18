package zelda;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

public class LinkCollisionManager extends AdvanceCollisionGroup {
    public LinkCollisionManager() {
        this.pixelPerfectCollision = false;
    }
    
    public void collided(Sprite s1, Sprite s2) {
        
 
    
        this.revertPosition1();
    }
}
