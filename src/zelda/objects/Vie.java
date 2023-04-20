package zelda.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import zelda.Zelda;
import zelda.scenary.Board;

public class Vie extends Sprite {
	
	protected boolean isCollected=false;
	
	protected BufferedImage vie;
	
	protected double posXVie=500;
	protected double posYVie=200;
	
	protected Board board;
	
	protected Zelda game;
	
	protected static int ANIMATION_DELAY = 100;  

//	public Vie() {
//		// TODO Auto-generated constructor stub
//	}
//	

//	public Vie(Zelda game) {
//		this.game = game;
//		this.vie=this.game.getImage("res/sprites/Objects/OHC.gif");
//		this.setImage(vie);
//		this.setLocation(700,200);
//	}
	public Vie(Zelda game,double x, double y) {
		this.game = game;
		this.vie=this.game.getImage("res/sprites/Objects/OHC.gif");
		this.setImage(vie);
		this.setLocation(x,y);
	}
	
	
	
	public void setBoard(Board board) {
		this.board = board;
    }
	
//	public void collectLifePoints() {
//		if (isCollected==true) {
//		
//		}
//	}
	
	
	 public Sprite getSprite() {
		    return this;
		 }
	 public void render(Graphics2D g) {
	        super.render(g);
	  }
	 public void update(long elapsedTime) {
	        super.update(elapsedTime);

	  }
	 
	 

}
