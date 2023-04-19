package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.enemies.AbstractEnemy;

public class Link_EnemyCollisionManager extends AdvanceCollisionGroup {
	
		private AbstractEnemy enemy;
		private Link link;
	
	 	public Link_EnemyCollisionManager(Link l, AbstractEnemy e) {
	        this.pixelPerfectCollision = false;
	        this.enemy = e;
	        this.link = l;
	    }
	    
	    public void collided(Sprite s1, Sprite s2) {
			System.out.println("Collision");

	    	this.enemy.decreaseLife();
	    	this.link.screamInPain();
	    	
	    	this.revertPosition1();
	        //this.revertPosition2();
	    }
	   
	    
}
