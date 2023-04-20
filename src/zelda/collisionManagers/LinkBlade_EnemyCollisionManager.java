package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.enemies.AbstractEnemy;
import zelda.sounds.SoundManager;

public class LinkBlade_EnemyCollisionManager extends AdvanceCollisionGroup {
	private AbstractEnemy enemy;
    private Link link;
    
    private static final long MIN_TIME_BETWEEN_SOUNDS_MS = 1000;
    private long lastPlayedTime;


 	public LinkBlade_EnemyCollisionManager(Link l, AbstractEnemy e) {
        this.pixelPerfectCollision = false;
        this.enemy = e;
        this.link = l;
    }
    
    public void collided(Sprite s1, Sprite s2) {
		System.out.println("LinkBlade _ Enemy Collision");
		
		long currentTime = System.currentTimeMillis();
        if (currentTime - lastPlayedTime > MIN_TIME_BETWEEN_SOUNDS_MS) {
//        	SoundManager.playSound("res/sounds/LOZ_Sword.wav");
            lastPlayedTime = currentTime;
        }


    	this.enemy.decreaseLife();
    
    	//this.revertPosition1();
        //this.revertPosition2();
    }
}
