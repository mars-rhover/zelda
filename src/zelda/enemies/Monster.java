package zelda.enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import zelda.scenary.Board;

public class Monster extends AbstractEnemy {

	public Monster(Game game) {
		super("Monster", 259,340);
		super.game = game;
		this.loadImagesFolder("res/sprites/Enemies", "monster_", ".png");
	}
	
	

}
