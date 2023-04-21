package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.enemies.AbstractEnemy;
import zelda.sounds.SoundManager;

public class Link_EnemyCollisionManager extends AdvanceCollisionGroup {
	
		private AbstractEnemy enemy;
	    private Link link;
	    
	    private static final long MIN_TIME_BETWEEN_SOUNDS_MS = 1000;
	    private long lastPlayedTime;

	
	 	public Link_EnemyCollisionManager(Link l, AbstractEnemy e) {
	        this.pixelPerfectCollision = false;
	        this.enemy = e;
	        this.link = l;
	    }
	    
	    public void collided(Sprite s1, Sprite s2) {
//			System.out.println("Link _ Enemy Collision");
			
			long currentTime = System.currentTimeMillis();
	       
	        
	        // Viens d'attaquer
	        if(!enemy.freeze && enemy.justAttacked) {
	        	enemy.reculer(enemy.attackDir);
	        	enemy.justAttacked = false;
	        	enemy.freeze = true;	
	        }

	        if(!link.wasTouched && link.canBeTouched) {
	        	this.link.screamInPain();
	        	 if (currentTime - lastPlayedTime > MIN_TIME_BETWEEN_SOUNDS_MS) {
	 	        	SoundManager.playSound("res/sounds/ScreamInPain.wav",100);
	 	            lastPlayedTime = currentTime;
	 	        }
	        } else {
	        	// Invulnerable
	        }
	        	
	    	//this.revertPosition1();
	        //this.revertPosition2();
	    }
	   
	    
}
