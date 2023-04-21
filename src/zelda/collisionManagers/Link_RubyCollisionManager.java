package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.enemies.AbstractEnemy;
import zelda.objects.Ruby;
import zelda.sounds.SoundManager;


public class Link_RubyCollisionManager extends AdvanceCollisionGroup {
	
	private Link link;
	private Ruby ruby;
	
	private static final long MIN_TIME_BETWEEN_SOUNDS_MS = 1000;
    private long lastPlayedTime;
	

	public Link_RubyCollisionManager(Link l, Ruby r) {
		 this.pixelPerfectCollision = false;
	       this.ruby = r;
	       this.link = l;
	}

	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		System.out.println("Link _ Ruby Collision");
		
		long currentTime = System.currentTimeMillis();
        if (currentTime - lastPlayedTime > MIN_TIME_BETWEEN_SOUNDS_MS) {
        	SoundManager.playSound("res/sounds/LOZ_Get_Rupee.wav",100);
            lastPlayedTime = currentTime;
        }
        
        this.ruby.incrementRubies();
        this.ruby.setActive(false);
        this.revertPosition1();
       
		
	}

}
