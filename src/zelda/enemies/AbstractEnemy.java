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
import com.golden.gamedev.object.Timer;
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
	
	protected double SPEED = 0.2;  
	    
	protected static int ANIMATION_DELAY = 100;  
	    
	protected static int FIGHT_DELAY = 300;
	
	protected Timer FIGHT_TIMER;
	    
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
			for(int i = 0; i < countImg; i++) {
				filename = prefix+(i+1)+suffix;
				sprites[i] = this.game.getImage(folder+"/"+filename);
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
       
        // si dir = x on a bougé sur X, si dir=y on bougé sur Y
        char dir = this.moveTowardLink();	
        this.setOrientationSprite(dir);

        // ne pas bouger de frame pour l'instant
        //this.setAnimationFrame(0, 0);
    }
	
	// Suivant les sprites, change l'orientation du personnage
	protected abstract void setOrientationSprite(char xORy);

	// Déplace vers link en fonction de l'attribut de classe SPEED
	// renvoie x si a bougé sur X, ou y si a bougé sur Y
	public char moveTowardLink() {
		double link_PosX = this.game.getLink().getScreenX();
		double link_PosY = this.game.getLink().getScreenY();
		
		double distX = Math.abs(link_PosX-this.getScreenX());
		double distY = Math.abs(link_PosY-this.getScreenY());
				
		// On bouge sur l'axe le plus éloigné d'abord
		if(distX > distY) {
			
			if(link_PosX < this.getScreenX()) {
				this.moveX(-this.SPEED);
				System.out.println("Bougé sur X");
				return 'x';
			} else if (link_PosX > this.getScreenX()){
				this.moveX(this.SPEED);
				System.out.println("Bougé sur X");
				return 'x';
			}
			
		} 
		
		
		if(link_PosY < this.getScreenY()) {
			this.moveY(-this.SPEED);
			System.out.println("Bougé sur Y");
			return 'y';
		} else if(link_PosY > this.getScreenY()) {
			this.moveY(this.SPEED);
			System.out.println("Bougé sur Y");
			return 'y';
		}
		
		return 'y';
		
		
		
		
		
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
