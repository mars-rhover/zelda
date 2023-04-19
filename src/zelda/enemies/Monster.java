package zelda.enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import zelda.Zelda;
import zelda.scenary.Board;

public class Monster extends AbstractEnemy {

	public Monster(Zelda game, double posX, double posY) {
		super(game, "Monster", posX,posY);
		super.game = game;
		this.loadImagesFolder("res/sprites/Enemies", "monster_", ".png");
	}
	
	

}
