package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.sounds.SoundManager;

public class Link_PlayfieldCollisionManager extends AdvanceCollisionGroup {
	
	private Link link;
	
    private static final long MIN_TIME_BETWEEN_SOUNDS_MS = 1000;
    private long lastPlayedTime;
	
    public Link_PlayfieldCollisionManager(Link link) {
        this.pixelPerfectCollision = true;
        this.link = link;
    }
    
    public void collided(Sprite s1, Sprite s2) {
        
    	long currentTime = System.currentTimeMillis();
        if (currentTime - lastPlayedTime > MIN_TIME_BETWEEN_SOUNDS_MS) {
        	//SoundManager.playSound("res/sounds/ScreamInPain.wav");
            lastPlayedTime = currentTime;
        }
    	
       //System.out.println("Link a collide avec un mur");
        this.revertPosition1();
    }
}
