package zelda.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import zelda.Zelda;
import zelda.scenary.Board;

public class CollectableOnce extends Sprite {

	public boolean isCollected = false;
	
	protected BufferedImage spriteImg;
	
	protected Board board;
	
	protected Zelda game;
	
	   
    public CollectableOnce(Zelda game, double posX, double posY) {
		this.game = game;
		this.spriteImg=null;
		this.setImage(spriteImg);
		this.setLocation(posX, posY);
	}
	
	public SpriteGroup getSprites() {
		SpriteGroup s = new SpriteGroup("Item");
		s.add(this);
	    return s;
	 }
	 
    public void setBoard(Board board) {
		this.board = board;
    }
    
    // Retourne true si l'objet est sur la board 
 	public boolean isOnBoard(Board board) {
 		return this.board == board;
 	}
 	
 	// Déclenchée par collisionManager
	public void collect() {
		this.isCollected = true;
		this.setActive(false);
	}
	 
	 public void render(Graphics2D g) {
	      super.render(g);
	  }
	 
	 public void update(long elapsedTime) {
	      super.update(elapsedTime);
	  }
}
