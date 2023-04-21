package zelda.enemies;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import zelda.Zelda;
import zelda.scenary.Board;

public class Moblin extends AbstractEnemy {
	
	// Paramètres spécifiques du monstre
	protected int life = 2;
	protected double SPEED = 0.2;  
	protected int FIGHT_DELAY = 600;

	public Moblin(Zelda game, double posX, double posY) {
		super(game, "Monster", posX,posY);
		super.game = game;
		this.loadImagesFolder("res/sprites/Enemies", "monster_", ".png");
		
		BufferedImage[] weaponImgs = new BufferedImage[4]; // Sud, Ouest, Nord, Est
		weaponImgs[0] = this.game.getImage("res/sprites/Objects/fleche_1.png");
		weaponImgs[1] = this.game.getImage("res/sprites/Objects/fleche_2.png");
		weaponImgs[2] = this.game.getImage("res/sprites/Objects/fleche_3.png");
		weaponImgs[3] = this.game.getImage("res/sprites/Objects/fleche_4.png");
		
		super.weapon = new Weapon(weaponImgs, posX, posY);
		super.SPEED = this.SPEED;
		super.life = this.life;
		super.attackDist = 20;
		super.reculDist = 10;
		super.FIGHT_DELAY = this.FIGHT_DELAY;
	}
	
	public void setOrientationSprite(char xORy) {
		
		// on a bougé sur x
		if(xORy == 'x') {
			double xMove = this.getX()-this.getOldX(); // pour avoir l'orientation du sprite
			
			if(xMove > 0) // Vers la droite 
				this.setAnimationFrame(6,7);

			 else if (xMove < 0)  // Vers la gauche
				this.setAnimationFrame(2,3);
			
		}
		
		// on a bougé sur y
		else if(xORy == 'y') {
			double yMove = this.getY()-this.getOldY();
			
			if(yMove < 0) // Vers le haut
				this.setAnimationFrame(4,5);
					
			 else if(yMove > 0) // Vers le bas
				this.setAnimationFrame(0,1);
		
			
		}
		
		this.setAnimate(true);
		
		// si une autre lettre ou vide on change pas le frame
			
	}
	
	protected void attack(int atk) {	 
		super.attack(atk);
		if(atk == 1) {
			weapon.setAnimationFrame(3,3);
			weapon.setSpeed(weapon.Speed, 0);
		} else if (atk == -1) {
			weapon.setAnimationFrame(1,1);
			weapon.setSpeed(-weapon.Speed, 0);
		} else if (atk == 2) {
			weapon.setAnimationFrame(2,2);
			weapon.setSpeed(0, weapon.Speed);
		} else if (atk == -2) {
			weapon.setAnimationFrame(0,0);
			weapon.setSpeed(0, -weapon.Speed);
		}
	}
	
}
