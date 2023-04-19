package zelda.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.CollisionGroup;

import zelda.Orientation;
import zelda.Zelda;
import zelda.objects.Shield;
import zelda.scenary.Board;

public abstract class AbstractEnemy extends AnimatedSprite {
	// Dans la super classe 

	protected String name = "Enemy";
	
	protected int life = 45;
	
	protected static int weapon;
	
	protected static double SPEED = 0.2;  
	    
	protected static int ANIMATION_DELAY = 100;  
	    
	protected static int FIGHT_TIMER = 300;
	    
    protected Board board;
    
    protected SpriteGroup enemies_SGroup;

    protected Zelda game;
	
	
	// Créé un enemy et le place à posX, posY et sur la board Board
	public AbstractEnemy(Zelda game, String spriteName, double posX, double posY) {
		this.game = game;
		enemies_SGroup = new SpriteGroup("ENEMY SPRITE GROUP");
        this.setLocation(posX, posY);
	}
	
	  public SpriteGroup getSpriteGroup() {
	    	return this.enemies_SGroup;
	    }


	// Add a sprite to this monster game
	public void loadImagesFolder(String folder, String prefix, String suffix) {
		int countImg = 0;
		String filename = "";
		try {
			for(int i = 1; i < 35; i++) {
				filename = prefix+i+suffix;
				String pathName = folder+File.separatorChar+filename;
				pathName = pathName.replace('/', File.separatorChar);
				Paths.get(pathName).toRealPath(); // essaye de trouver le fichier
				countImg++;
				
			}
		} catch(IOException e) {
			// Plus d'images
		}
		
		if(countImg > 0) {
			BufferedImage[] sprites = new BufferedImage[countImg];
			for(int i = 1; i < countImg; i++) {
				filename = prefix+i+suffix;
				sprites[i-1] = this.game.getImage(folder+"/"+filename);
			}
			this.setImages(sprites);
	        this.setAnimationFrame(0, 0);
		}
	    
	}
	
	
	public void animate(int startFrame, int stopFrame) {
		// set animation speed 100 milliseconds for each frame
	    this.getAnimationTimer().setDelay(ANIMATION_DELAY);

	    // set animation frame starting from the first image to the third image
	    this.setAnimationFrame(startFrame, startFrame);

	    // animate the sprite, and perform continous animation
	    this.setAnimate(true);
	    this.setLoopAnim(true);
	}
	
	// Place le sprite dans une board
	public void setBoard(Board board) {
		this.board = board;
		enemies_SGroup.add(this);
    }
	
	// Retourne la board sur laquelle est l'enemi
	public Board getBoard() {
		System.out.println("Enemy sur la board "+this.board.getX()+" "+this.board.getY());
		return this.board;
	}
	
	// Retourne true si l'enemy est sur la board 
	public boolean isOnBoard(Board board) {
		return this.board == board;
	}
	
	
	public boolean isAlive() {
		return this.life > 0;
	}
	
	// Medhodes pour voir les sprites et les updates
	public void update(long elapsedTime) {
        super.update(elapsedTime);

        // ne pas bouger de frame pour l'instant
        //this.setAnimationFrame(0, 0);
    }
	
	public void decreaseLife() {
		this.life = this.life - 1;
		if(this.life == 0) {
			this.setActive(false);// desactive le collision manager
			System.out.println("Mooort");
		}
	}
	
	  public void render(Graphics2D g) {
	        super.render(g);
	  }


}
