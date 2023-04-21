package zelda.enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.sprite.VolatileSprite;

public class Weapon extends VolatileSprite {
	
	protected double Speed = 1.2;
	
	protected BufferedImage[] imgs;

	// Image and positions
	public Weapon(BufferedImage[] imgs, double posX, double posY) {
		super(imgs, posX, posY);
		this.setImages(imgs);
		this.setActive(false);
	}
	
	public void setPosition(double posX, double posY) {
		super.moveX(posX+20);
		super.moveY(posY);
	}

	

}
