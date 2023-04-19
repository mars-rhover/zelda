package zelda.enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import zelda.Zelda;
import zelda.scenary.Board;

public class Monster extends AbstractEnemy {

	public Monster(Zelda game) {
		super(game, "Monster", 209,340);
		super.game = game;
		this.loadImagesFolder("res/sprites/Enemies", "monster_", ".png");
	}
	
	

}
