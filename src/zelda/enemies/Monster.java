package zelda.enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import zelda.Zelda;
import zelda.scenary.Board;

public class Monster extends AbstractEnemy {
	
	// Paramètres spécifiques du monstre
	protected int life = 43;
	protected double SPEED = 0.2;  
	protected int FIGHT_DELAY = 300;

	public Monster(Zelda game, double posX, double posY) {
		super(game, "Monster", posX,posY);
		super.game = game;
		this.loadImagesFolder("res/sprites/Enemies", "monster_", ".png");
		super.SPEED = this.SPEED;
		super.life = this.life;
		super.FIGHT_DELAY = this.FIGHT_DELAY;
	}
	
	public void setOrientationSprite(char xORy) {
		
		// on a bougé sur x
		if(xORy == 'x') {
			double xMove = this.getX()-this.getOldX(); // pour avoir l'orientation du sprite
			
			if(xMove > 0) // Vers la droite 
				this.setAnimationFrame(3,3);

			 else if (xMove < 0)  // Vers la gauche
				this.setAnimationFrame(1,1);
			
		}
		
		// on a bougé sur y
		else if(xORy == 'y') {
			double yMove = this.getY()-this.getOldY();
			
			if(yMove < 0) // Vers le haut
				this.setAnimationFrame(2,2);
					
			 else if(yMove > 0) // Vers le bas
				this.setAnimationFrame(0,0);
		
		}
		
		// si une autre lettre ou vide on change pas le frame
		
				
		
		
		
		
	
		
	}
	
}
