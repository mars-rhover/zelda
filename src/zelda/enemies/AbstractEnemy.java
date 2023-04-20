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
import zelda.objects.*;



public abstract class AbstractEnemy extends AnimatedSprite {
	// Dans la super classe 

	protected String name = "Enemy";
	
	protected int life = 8;
	
	protected static int weapon;
	
	protected double SPEED = 0.2;  
	    
	protected static int ANIMATION_DELAY = 300;  
	    
	protected static int FIGHT_DELAY = 400;
	
	protected static int FREEZE_DELAY = 2000;
	
	protected static double attackDist = 20; // Distance d'attaque
	
	protected static double reculDist = 40; // Distance de recul après attaque
	
	protected Timer FIGHT_TIMER;
	    
    protected Board board;
    
    protected SpriteGroup enemies_SGroup;

    protected Zelda game;
    
    private double distX, distY;
    
    public boolean justAttacked = false;
    
    private Timer freezeTimer;
    
    public int attackDir = 0;
	
    public boolean freeze; // freeze true si l'enemi est en "pause" (en terme de mouvement)
	
	// Créé un enemy et le place à posX, posY et sur la board Board
	public AbstractEnemy(Zelda game, String spriteName, double posX, double posY) {
		this.game = game;
		enemies_SGroup = new SpriteGroup("ENEMY SPRITE GROUP");
        this.setLocation(posX, posY);
        this.distX = Zelda.PLAYGROUND_SIZEX;
        this.distY = Zelda.PLAYGROUND_SIZEY;
        this.freeze = false;
        this.getAnimationTimer().setDelay(ANIMATION_DELAY);
        this.FIGHT_TIMER = new Timer(FIGHT_DELAY);
        this.freezeTimer = new Timer(FREEZE_DELAY);
	}
	
	 public SpriteGroup getSpriteGroup() {
	    	return this.enemies_SGroup;
	 }

	 // retourne la distance X entre l'enemi et link
	 public double getDistX() {
		 return this.distX;
	 }
	  
	// retourne la distance Y entre l'enemi et link
	 public double getDistY() {
		 return this.distY;
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
         
    	if (freeze && freezeTimer.action(elapsedTime)) {
    		freeze = false;
    	}
       
        if(!freeze) {
            char dir = this.moveTowardLink();	// si dir = x on a bougé sur X, si dir=y on bougé sur Y
            this.setOrientationSprite(dir);
        
	        this.attackDir = this.shouldAttack();
	        if(this.attackDir != 0) {
	        	if (FIGHT_TIMER.action(elapsedTime)) {
	        		this.attack(this.attackDir);
	        		this.justAttacked = true;
	        	}       
	        } 
        }
          
    }
	
	
	// renvoie 1 si attaque en +X, -1 en -X
	// renvoie 2 si attaque en +Y, -1 en -Y
	private int shouldAttack() {

		double distLimit = (attackDist*2)+(attackDist/2); // Distance de stop pour attaque
		
		// Si on est a moins de 50px (distLimit), et que une des dimentions est à moins de 35px, on attaque
        if(Math.abs(distX) <= distLimit && Math.abs(distY) <= distLimit)  {

        	double minDist = Math.abs(distX) < Math.abs(distY) ?  distX : distY;
//        	System.out.println("distXY : "+distX+" "+distY);
//        	System.out.println("minDist : "+minDist);
        	
        	// On attaque que si on est à une certaine distance 
        	if(Math.abs(minDist) < (attackDist+(attackDist/2))) {
        		if(minDist == distY) { // On attaque en X
        			if(distX < 0)
        				return -1;
        			else
        				return 1;
        			
        		} else { // On attaque en Y
        			if(distY < 0)
        				return -2;
        			else
        				return 2;
        		}	
        	}
        }
        
        return 0;
        	
	}
	
	// Bouge le sprite un instantanément pour attaquer. Pour changer sprites réécrire cette méthode dans sous.classe.
	protected void attack(int atk) {
		if(atk == 1) {
			this.moveX(attackDist);
		} else if (atk == -1) {
			this.moveX(-attackDist);
		} else if (atk == 2) {
			this.moveY(attackDist);
		} else if (atk == -2) {
			this.moveY(-attackDist);
		}
	}
	
	// Fait reculer le sprite (après attaque). Pour changer sprites réécrire cette méthode dans sous.classe.
	public void reculer(int atk) {
		if(atk == 1) {
			this.moveX(-attackDist);
		} else if (atk == -1) {
			this.moveX(attackDist);
		} else if (atk == 2) {
			this.moveY(-attackDist);
		} else if (atk == -2) {
			this.moveY(attackDist);
		}
	}
	
	// Suivant les sprites, change l'orientation du personnage
	protected abstract void setOrientationSprite(char xORy);

	// Déplace vers link en fonction de l'attribut de classe SPEED
	// renvoie x si a bougé sur X, ou y si a bougé sur Y
	public char moveTowardLink() {
		double link_PosX = this.game.getLink().getScreenX();
		double link_PosY = this.game.getLink().getScreenY();
		
		distX = link_PosX-this.getScreenX();
		distY = link_PosY-this.getScreenY();
		double distLimit = (attackDist*2)+(attackDist/2);
				
		// On bouge sur l'axe le plus éloigné d'abord
		if(Math.abs(distX) > Math.abs(distY)) {
			
			// S'arrete avant de toucher link
			if(Math.abs(distX) > distLimit) { 
				if(link_PosX < this.getScreenX()) {
					this.moveX(-this.SPEED);
					return 'x';
				} else if (link_PosX > this.getScreenX()){
					this.moveX(this.SPEED);
					return 'x';
				}
			}
		
		} 
		
		// S'arrete avant de toucher link
		if(Math.abs(distY) > distLimit) {  
			if(link_PosY < this.getScreenY()) {
				this.moveY(-this.SPEED);
				return 'y';
			} else if(link_PosY > this.getScreenY()) {
				this.moveY(this.SPEED);
				return 'y';
			}
		}
		
		this.setSpeed(0, 0);
		
		return 's'; // retourner s ou autre = pas bouger
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
