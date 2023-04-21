package zelda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import zelda.enemies.*;
import zelda.npc.NonPlayableCharacter;
import zelda.scenary.*;
import zelda.sounds.SoundManager;
import zelda.objects.*;
import zelda.objects.*;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.SpriteGroup;

public class Zelda extends Game {

	// Dimension du playground, menu et fenetre de jeu
	public final static int MENU_SIZEY = 120;
	public final static int SCREEN_SIZEX = 672;
	public final static int SCREEN_SIZEY = 588;
	public final static int PLAYGROUND_SIZEX = SCREEN_SIZEX;
	public final static int PLAYGROUND_SIZEY = SCREEN_SIZEY - MENU_SIZEY;

	private Link link;

	private AbstractEnemy[] enemies; // Enemies
	private int EnemyCount = 0;
	
	private Ruby ruby;
	
	private Vie[] arrayVie; 
	
	private CollectableOnce[] collectableItems; // Items collectables une fois
	
	private NonPlayableCharacter[] npcs; // PNJs
	
	private int arrayPositionXVie[]= {515,530,545,560,575,590};
	private int positionYVie=60;
	
	
	
	private Quest quest;

	private boolean menu;

	public Zelda() {

	}

	public void initResources() {
		this.quest = new Quest(this);
		this.link = new Link(this);
		this.link.setBoard(this.quest.getCurrentBoard());
		this.menu = false;
		this.ruby=new Ruby(this,270,80);
		
		this.arrayVie=new Vie[this.link.getLinkLifePoints()];
		this.collectableItems = new CollectableOnce[10];
		this.enemies = new AbstractEnemy[this.quest.getBoards().length*this.quest.getBoards()[0].length * 3]; 		// On a max 3 enemy par board max (en moyenne)
		this.npcs = new NonPlayableCharacter[10];
		
		this.createInitialEnemies();
		this.createInitialCollectableItems();
		this.createNPCs();
		this.quest.createCollisionManagers();
		this.creationVies();

		
	}

	



	public void update(long elapsedTime) {
		// transition de board quand link bouge
		int x = quest.getCurrentBoard().getX();
		int y = quest.getCurrentBoard().getY();
		
		
		
//		System.out.println(this.link.getOrientationLink());
//		System.out.println(this.link.getScreenX());

		//System.out.println(this.link.getScreenY());
		
		transitionBoard(x, y);
		
		if (this.keyPressed(KeyEvent.VK_ALT)) {
			this.link.fight();
		} else if (this.keyDown(KeyEvent.VK_LEFT)) {
			this.link.walk(Orientation.WEST);
		} else if (this.keyDown(KeyEvent.VK_RIGHT)) {
			this.link.walk(Orientation.EAST);
		} else if (this.keyDown(KeyEvent.VK_UP)) {
			this.link.walk(Orientation.NORTH);
		} else if (this.keyDown(KeyEvent.VK_DOWN)) {
			this.link.walk(Orientation.SOUTH);
		} else if (keyPressed(KeyEvent.VK_ESCAPE)) {
			finish();
		} else {
			this.link.setSpeed(0, 0);
		}
		
		// Pour test
		if (this.keyPressed(KeyEvent.VK_SPACE)) {
			this.quest.changeBoard(1, 3);
			link.setBoard(this.quest.getBoard(1, 3));
		}

		this.quest.update(elapsedTime);
		this.link.update(elapsedTime);

		// Updates de tous les enemies vivants et actifs (de la board)
		for (int i = 0; i < enemies.length; i++) {
			if (this.enemies[i] != null)
				if (this.enemies[i].isActive() && this.enemies[i].isAlive())
					this.enemies[i].update(elapsedTime);
		}
		
		// Updates de tous les items collectables non nuls et non collectés de la board
		for (int i = 0; i < collectableItems.length; i++) {
			if (this.collectableItems[i] != null)
				if (!this.collectableItems[i].isCollected && this.collectableItems[i].isOnBoard(this.quest.getCurrentBoard()) )
					this.collectableItems[i].update(elapsedTime);			
		}
		
		// Update de tous les NPCs
		for (int i = 0; i < npcs.length; i++) {
			if (this.npcs[i] != null)
				if (this.npcs[i].canInteract() && this.npcs[i].isOnBoard(this.quest.getCurrentBoard()))
					this.npcs[i].update(elapsedTime);		
		}
		
		this.ruby.update(elapsedTime);
		this.updateVies(elapsedTime);

	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		this.quest.render(g);
		this.link.render(g);
		
		this.ruby.render(g);
		this.renderVie(g);

		// Pour renders des enemies vivants et actifs (de la board)
		for (int i = 0; i < enemies.length; i++) {
			if (this.enemies[i] != null)
				if (this.enemies[i].isActive() && this.enemies[i].isAlive())
					this.enemies[i].render(g);
		}
		
		// Render de tous les items collectables non nuls et non collectés de la board
		for (int i = 0; i < collectableItems.length; i++) {
			if (this.collectableItems[i] != null)
				if (!this.collectableItems[i].isCollected && this.collectableItems[i].isOnBoard(this.quest.getCurrentBoard()) )
					this.collectableItems[i].render(g);			
		}
		
		
		// Render de tous les NPCs
		for (int i = 0; i < npcs.length; i++) {
			if (this.npcs[i] != null)
				if (this.npcs[i].canInteract() && this.npcs[i].isOnBoard(this.quest.getCurrentBoard()))
					this.npcs[i].render(g);			
		}

	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zelda(), new Dimension(SCREEN_SIZEX, SCREEN_SIZEY), false);
		game.start();
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////

	
	
	public void transitionBoard(int x, int y) {

		// System.out.print(quest.getCurrentBoard().getX());
		// System.out.println(quest.getCurrentBoard().getY());

		// pour ne pas depasser l'integralite de la carte

		// Pour acceder au dungeon
		if (x==0 && y==1) {
			if ((link.getScreenX()>292 && link.getScreenX()<297) &&
					(this.link.getScreenY()>165 && this.link.getScreenY()<176)) {
				this.quest.changeBoard(0, 3);
				link.setBoard(this.quest.getCurrentBoard());
				link.setLocation(200,300);
				
			}
			
		} // Pour sortir du dungeon
		if (x==0 && y==3) {
			if ((link.getScreenY()>SCREEN_SIZEY-3)) {	
				this.quest.changeBoard(0, 1);
				link.setBoard(this.quest.getCurrentBoard());
				link.setLocation(300,180);
				
			}
			
		}//access au dungeon avec GANON
		if ((x==0 && y==2) && (link.getScreenX()>60 && link.getScreenX()<77) &&
				(this.link.getScreenY()>360 && this.link.getScreenY()<379)) {
		
				this.quest.changeBoard(1, 3);
				link.setBoard(this.quest.getCurrentBoard());
				link.setLocation(200,300);
				
			
			
		}
		
		else {

			if (link.getScreenY() < SCREEN_SIZEY && link.getScreenX() > SCREEN_SIZEX) {
				// changer sur x a droite
				this.quest.changeBoard(x, y + 1);
				link.setBoard(this.quest.getCurrentBoard());
				link.setLocation(link.getWidth(), link.getY());
			}

			else if (link.getScreenY() < SCREEN_SIZEY && link.getScreenX() < 0) {
				// changer sur x a gauche
				this.quest.changeBoard(x, y - 1);
				link.setBoard(this.quest.getCurrentBoard());
				link.setLocation(SCREEN_SIZEX - link.getWidth(), link.getY());
			}

			else if (link.getScreenY() > SCREEN_SIZEY && link.getScreenX() < SCREEN_SIZEX) {
				// link bouge sur une carte du bas
				this.quest.changeBoard(x + 1, y);
				link.setBoard(this.quest.getCurrentBoard());
				link.setLocation(link.getX(), MENU_SIZEY+link.getHeight());
			}

			else if (link.getScreenY() < MENU_SIZEY && link.getScreenX() < SCREEN_SIZEX) {
				// link bouge sur une carte du haut
				this.quest.changeBoard(x - 1, y);
				link.setBoard(this.quest.getCurrentBoard());
				link.setLocation(link.getX(), SCREEN_SIZEY-link.getHeight());
			}
			
			}
		
		}

	
	
	
	// Retourne le sprite group de link : pour les collisions managers
	public SpriteGroup getLinkSG() {
		return this.link.getSpriteGroup();
	}

	public Link getLink() {
		return this.link;
	}

	
	public void creationVies() {
		for (int i=0;i<this.arrayVie.length;i++) {
			this.arrayVie[i]=new Vie(this,this.arrayPositionXVie[i],this.positionYVie);
		}
	}

	public void renderVie(Graphics2D g) {
		for (int i=0;i<this.link.getLinkLifePoints();i++) {
			this.arrayVie[i].render(g);
		}
	}
	
	public void updateVies(long elapsedTime) {
		//System.out.println(link.getLinkLifePoints());
	
		for (int i=0;i<this.link.getLinkLifePoints();i++) {
			this.arrayVie[i].update(elapsedTime);
		}
	}
	
	
	
	// Creations des objects collectables 1 fois
	private void createInitialCollectableItems() {
		// Créer l'épée dans la grotte du haut
		Blade woodBlade = new Blade(this,(PLAYGROUND_SIZEX/2)-21, (PLAYGROUND_SIZEY/2+MENU_SIZEY));
		woodBlade.setBoard(quest.getBoard(0, 3));
		collectableItems[0] = woodBlade;
		
	}
	
	public CollectableOnce[] getCollectableItems() {
		return this.collectableItems;
	}

	public CollectableOnce getCollectableItem(int i) {
		return this.collectableItems[i];
	}
	
	// Creation des NPCs
	private void createNPCs() {
		npcs[0] = new NonPlayableCharacter(this, quest.getBoard(0, 3), (PLAYGROUND_SIZEX/2)-21, (PLAYGROUND_SIZEY/2+MENU_SIZEY)-50);
		npcs[0].setImg("res/sprites/Characters/blade_dude.png");
		npcs[0].setSentence("It's dangerous to go alone ! Take this.");
	}
	
	


	// GESTION DES ENEMIES 
	private void createInitialEnemies() {
		int bX = 0, bY = -1; // compteurs d'index de board
		for(int i = 0; i< (enemies.length/3);i++) {
			AbstractEnemy newEnemy;
			Board targetBoard = this.quest.getBoard(0, 0);
			
			// Créé un monstre 1 fois sur 2, sinon une araignée
			if (i % 2 == 0) {
				newEnemy = new Moblin(this, PLAYGROUND_SIZEX/2+100, (PLAYGROUND_SIZEY/2)+MENU_SIZEY);
			} else {
				newEnemy = new Tektite(this, PLAYGROUND_SIZEX/2+100, (PLAYGROUND_SIZEY/2)+MENU_SIZEY);
			}
			
			// Trouver une board sur laquelle le mettre 
			if(bX < this.quest.getBoardCountX()) {
				if(bY < this.quest.getBoardCountY()-1) {
					bY += 1;
				} else {
					bY = 0; 
					bX += 1;
				}
			}
			
			targetBoard = this.quest.getBoard(bX, bY);
		
			if(bX==0 && bY==3) {
				// pas mettre d'enemi
			} else if(bX==1 && bY==3) {
				newEnemy = new Ganon(this, PLAYGROUND_SIZEX/2+100, (PLAYGROUND_SIZEY/2)+MENU_SIZEY);
				this.addEnemy(newEnemy, targetBoard);
			} else {
				this.addEnemy(newEnemy, targetBoard);
			}
			
		}
		
	
		
	}
	
	public void addEnemy(AbstractEnemy e, Board b) {
		this.enemies[EnemyCount] = e;
		this.enemies[EnemyCount].setBoard(b);
		EnemyCount+=1;
	}
	
	public AbstractEnemy getEnemy(int index) {
		if (index > this.enemies.length)
			throw new IllegalArgumentException("Index de l'enemi incorrect");

		return this.enemies[index];
	}

	public AbstractEnemy[] getEnemies() {
		return this.enemies;
	}
	
	

	

}
