package zelda.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.SpriteGroup;

import zelda.Orientation;
import zelda.objects.Shield;
import zelda.scenary.Board;

public abstract class AbstractEnemy extends AnimatedSprite {
	// Dans la super classe 

	protected String name = "Enemy";
	
	protected int life = 2;
	
	protected static int weapon;
	
	protected static double SPEED = 0.2;  
	    
	protected static int ANIMATION_DELAY = 100;  
	    
	protected static int FIGHT_TIMER = 300;
	
    protected CollisionManager manager;
    
    protected SpriteGroup enemies_SGroup;

    protected Game game;
	
	// Créé un enemy et le place à 0,0
    public AbstractEnemy() {
		this("",0,0);
	}
    
	public AbstractEnemy(String spriteName) {
		this(spriteName,0,0);
	}
	
	// Créé un enemy et le place à posX, posY et sur la board Board
	public AbstractEnemy(String spriteName, double posX, double posY) {
		enemies_SGroup = new SpriteGroup("ENEMY SPRITE GROUP");
		this.manager = new EnemyCollisionManager();
        this.setLocation(posX, posY);
	}


	// Add a sprite to this monster game
	public void loadImagesFolder(String folder, String prefix, String suffix) {
		int countImg = 0;
		String filename = "";
		try {
			for(int i = 1; i < 35; i++) {
				filename = prefix+i+suffix;
				Paths.get(folder+"\\"+filename).toRealPath(); // essaye de trouver le fichier
				countImg++;
			}
		} catch (IOException e) {
			System.out.println("Il y a "+countImg+" images");
		} 
		
	    BufferedImage[] sprites = new BufferedImage[countImg];
	   // this.game.getImage("res/sprites/Enemies/monster_1.png");
		for(int i = 1; i <= countImg; i++) {
			filename = prefix+i+suffix;
			System.out.println(folder+"/"+filename);
			sprites[i-1] = this.game.getImage(folder+"/"+filename);
		}
		this.setImages(sprites);
        this.setAnimationFrame(0, 0);
	}
	
	public void setBoard(Board board) {
		enemies_SGroup.add(this);
        this.manager.setCollisionGroup(enemies_SGroup, board.getForeground());
    }
	
	
	// Medhodes pour voir les sprites et les updates
	public void update(long elapsedTime) {
        super.update(elapsedTime);
        this.setAnimationFrame(0, 0);
    }
	
	  public void render(Graphics2D g) {
	        super.render(g);
	  }


}
