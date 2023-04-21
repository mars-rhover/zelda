package zelda.enemies;

import zelda.Zelda;

public class Ganon extends AbstractEnemy {

	// Paramètres spécifiques à Ganon
		protected int life = 30;
		protected double SPEED = 0.0;  // bouge pas
		protected static int FIGHT_DELAY = 200;
		protected static int ANIMATION_DELAY = 2000;  

		public Ganon(Zelda game, double posX, double posY) {
			super(game, "Ganondorf", posX, posY);
			super.game = game;
			this.loadImagesFolder("res/sprites/Enemies", "Ganon_", ".png");
			super.SPEED = this.SPEED;
			super.life = this.life;
			super.FREEZE_DELAY = 3000;
			super.ANIMATION_DELAY = this.ANIMATION_DELAY;
			super.FIGHT_DELAY = this.FIGHT_DELAY;
		}
		
		protected void setOrientationSprite(char xORy) {
			this.setAnimationFrame(2, 4);
			this.setAnimate(true);
		};
		
		protected void attack(int atk) {	 
			super.attack(atk);
		}
		
		public void reculer(int atk) {
			this.setAnimationFrame(1, 1);
		}
	
}
