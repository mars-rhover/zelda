package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;

public class Link_PlayfieldCollisionManager extends AdvanceCollisionGroup {
	
	private Link link;
	
    public Link_PlayfieldCollisionManager(Link link) {
        this.pixelPerfectCollision = true;
        this.link = link;
    }
    
    public void collided(Sprite s1, Sprite s2) {
        
       //System.out.println("Link a collide avec un mur");
        this.revertPosition1();
    }
}
