package zelda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import zelda.enemies.*;
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

	private AbstractEnemy[] Enemies;
	private int EnemyCount = 0;
	private Ruby ruby;
	private Vie[] arrayVie; 
	
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
		
		
		
		
		
		
		
		// On a 3 enemy par board max (en moyenne)
		this.Enemies = new AbstractEnemy[this.quest.getBoards().length*this.quest.getBoards()[0].length * 3];
		this.createInitialEnemies();
		this.quest.createCollisionManagers();
		this.creationVies();
		
		
		
	}

	
	
	
	public void transitionBoard(int x, int y) {

		// System.out.print(quest.getCurrentBoard().getX());
		// System.out.println(quest.getCurrentBoard().getY());

		// pour ne pas depasser l'integralite de la carte

		if ((x == 0 || x == 1) && (y == 0 || y == 1 || y == 2)) {

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
	
	
	
	
		
	
	
	
	

	public void update(long elapsedTime) {
		// transition de board quand link bouge
		System.out.println(this.link.getScreenX());
		System.out.println(this.link.getScreenY());
		
		int x = quest.getCurrentBoard().getX();
		int y = quest.getCurrentBoard().getY();
		
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

		this.quest.update(elapsedTime);
		this.link.update(elapsedTime);

		// Updates de tous les enemies vivants et actifs (de la board)
		for (int i = 0; i < Enemies.length; i++) {
			if (this.Enemies[i] != null)
				if (this.Enemies[i].isActive() && this.Enemies[i].isAlive())
					this.Enemies[i].update(elapsedTime);
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
		for (int i = 0; i < Enemies.length; i++) {
			if (this.Enemies[i] != null)
				if (this.Enemies[i].isActive() && this.Enemies[i].isAlive())
					this.Enemies[i].render(g);
		}

	}

	// Retourne le sprite group de link : pour les collisions managers
	public SpriteGroup getLinkSG() {
		return this.link.getSpriteGroup();
	}

	public Link getLink() {
		return this.link;
	}

	
	
	
	

	// GESTION DES ENEMIES 
	private void createInitialEnemies() {
		int bX = 0, bY = -1; // compteurs d'index de board
		for(int i = 0; i< (Enemies.length/3);i++) {
			AbstractEnemy newEnemy;
			Board targetBoard = this.quest.getBoard(0, 0);
			
			// Créé un monstre 1 fois sur 2, sinon une araignée
			if (i % 2 == 0) {
				newEnemy = new Monster(this, PLAYGROUND_SIZEX/2+100, (PLAYGROUND_SIZEY/2)+MENU_SIZEY);
			} else {
				newEnemy = new Spider(this, PLAYGROUND_SIZEX/2+100, (PLAYGROUND_SIZEY/2)+MENU_SIZEY);
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
		
			this.addEnemy(newEnemy, targetBoard);
			
		}
	}
	
	public void addEnemy(AbstractEnemy e, Board b) {
		this.Enemies[EnemyCount] = e;
		this.Enemies[EnemyCount].setBoard(b);
		EnemyCount+=1;
	}
	
	public AbstractEnemy getEnemy(int index) {
		if (index > this.Enemies.length)
			throw new IllegalArgumentException("Index de l'enemi incorrect");

		return this.Enemies[index];
	}

	public AbstractEnemy[] getEnemies() {
		return this.Enemies;
	}
	
	

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Zelda(), new Dimension(SCREEN_SIZEX, SCREEN_SIZEY), false);
		game.start();
	}

}
