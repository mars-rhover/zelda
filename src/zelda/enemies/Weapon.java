package zelda.enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.sprite.VolatileSprite;

public class Weapon extends VolatileSprite {
	
	

	// Image and positions
	public Weapon(BufferedImage[] imgs, double posX, double posY) {
		super(imgs, posX, posY);
		this.setImages(imgs);
	}

}
