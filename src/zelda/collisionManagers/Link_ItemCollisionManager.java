package zelda.collisionManagers;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

import zelda.Link;
import zelda.objects.CollectableOnce;

public class Link_ItemCollisionManager extends AdvanceCollisionGroup {

	private Link link;
	private CollectableOnce item;
	
	public Link_ItemCollisionManager(Link link, CollectableOnce item) {
		this.pixelPerfectCollision = false;
		this.link = link;
		this.item = item;
	}
	
	@Override
	public void collided(Sprite linkSprite, Sprite itemSprite) {
		this.item.collect();
	}

}
