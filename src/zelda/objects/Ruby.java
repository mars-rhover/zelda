package zelda.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import zelda.Zelda;
import zelda.scenary.Board;


public class Ruby extends Sprite {
	
	protected boolean isCollected=false;
	
	protected static int countRubies=0;
	
	protected BufferedImage ruby;
	
	protected Board board;
	
	protected Zelda game;
	
	protected static int ANIMATION_DELAY = 100;  
	
	

//	public Ruby() {
//		// TODO Auto-generated constructor stub
//	}
	
	public Ruby(Zelda game, double posX, double posY) {
		this.game = game;
		//System.out.print(rubies_SpriteGroup.getSize());
		this.ruby=this.game.getImage("res/sprites/Objects/OBP.gif");
		this.setImage(ruby);
		this.setLocation(posX, posY); 
	}
	
	
	public int getCountRubies() {
		return this.countRubies;
	}
	public void incrementRubies() {
		countRubies++;
	}
	
	 public Sprite getSprite() {
	    return this;
	 }
	 
	 public void setBoard(Board board) {
			this.board = board;
	    }
	 
	 public void render(Graphics2D g) {
	        super.render(g);
	  }
	 public void update(long elapsedTime) {
	        super.update(elapsedTime);

	    }
	 
	 
	
	 
	 

}
