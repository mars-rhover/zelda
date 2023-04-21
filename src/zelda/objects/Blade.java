package zelda.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import zelda.Zelda;
import zelda.scenary.Board;
import zelda.sounds.SoundManager;

public class Blade extends CollectableOnce {
	
    public enum Kind {
        NONE,
        WOOD,
        SILVER,
        MAGICAL
    }
    
    public Blade(Zelda game, double posX, double posY) {
		super(game, posX, posY);
		this.spriteImg=this.game.getImage("res/sprites/Objects/woodblade.png");
		this.setImage(spriteImg);
	}
    
    public void collect() {
    	super.collect();
    	SoundManager.playSound("res/sounds/LOZ_Fanfare.wav");
    }
    
}
