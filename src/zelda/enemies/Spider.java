package zelda.enemies;

import com.golden.gamedev.object.Timer;

import zelda.Zelda;

public class Spider extends AbstractEnemy {

	// Paramètres spécifiques de l'arraignée
		protected int life = 20;
		protected double SPEED = 0.6;  
		protected static int FIGHT_DELAY = 200;
		protected static int ANIMATION_DELAY = 600;  

	
	public Spider(Zelda game, double posX, double posY) {
		super(game, "Spider", posX, posY);
		super.game = game;
		this.loadImagesFolder("res/sprites/Enemies", "spider_", ".png");
		super.SPEED = this.SPEED;
		super.life = this.life;
		super.ANIMATION_DELAY = this.ANIMATION_DELAY;
		super.FIGHT_DELAY = this.FIGHT_DELAY;
	}
	
	protected void setOrientationSprite(char xORy) {
		this.setAnimationFrame(0, 1);
		this.setAnimate(true);
	};
	
	protected void attack(int atk) {	 
		super.attack(atk);
		
	}
	
	protected void reculer(int atk) {
		super.reculer(atk);
		this.setAnimationFrame(0, 0);
	}

}
