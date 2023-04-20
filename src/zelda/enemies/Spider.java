package zelda.enemies;

import com.golden.gamedev.object.Timer;

import zelda.Zelda;

public class Spider extends AbstractEnemy {

	// Paramètres spécifiques de l'arraignée
		protected int life = 20;
		protected double SPEED = 0.6;  
		protected static int FIGHT_DELAY = 300;

	
	public Spider(Zelda game, double posX, double posY) {
		super(game, "Spider", posX, posY);
		super.game = game;
		this.loadImagesFolder("res/sprites/Enemies", "spider_", ".png");
		super.SPEED = this.SPEED;
		super.life = this.life;
		super.FIGHT_DELAY = this.FIGHT_DELAY;
	}
	
	protected void setOrientationSprite(char xORy) {
		System.out.println("...");
	}

}
