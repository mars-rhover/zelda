package zelda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import zelda.enemies.AbstractEnemy;
import zelda.enemies.Monster;
import zelda.scenary.Quest;
import zelda.scenary.Rock;


import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.SpriteGroup;

public class Zelda extends Game {
    
    private Link link;
        
    private AbstractEnemy[] Enemies;
   
    private Quest quest;
    
    private boolean menu;
    
    public Zelda() {
        
    }
    
    public void initResources() {
        this.quest = new Quest(this);
        this.link = new Link(this);
        this.link.setBoard(this.quest.getCurrentBoard());    
        this.menu = false;
        
        // Pour l'instant : on mets un enemy
        this.Enemies = new AbstractEnemy[1];
        this.Enemies[0] = new Monster(this);
        this.Enemies[0].setBoard(this.quest.getCurrentBoard());    
        
        this.quest.createCollisionManagers();
    }
        
    public void update(long elapsedTime) {
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
            this.quest.changeBoard(0, 0);
            this.link.setBoard(this.quest.getCurrentBoard());
        } 
        
        this.quest.update(elapsedTime);
        this.link.update(elapsedTime);
        
        // Pour l'instant on update que notre enemy mais faudra update que ceux de la board actuelle
        if(this.Enemies[0].isOnBoard(this.quest.getCurrentBoard())) {
        	this.Enemies[0].update(elapsedTime);
        } else {
        	this.Enemies[0].setActive(false);
        }
        
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.quest.render(g);
        this.link.render(g);
        
        // Pour l'instant on render que notre enemy
        this.Enemies[0].render(g);
    }
    
    // Retourne le sprite group de link : pour les collisions managers
    public SpriteGroup getLinkSG() {
		return this.link.getSpriteGroup();
    }
    
    public Link getLink() {
    	return this.link;
    }
    
    public AbstractEnemy getEnemy(int index) {
    	if(index>this.Enemies.length)
    		throw new IllegalArgumentException("Index de l'enemi incorrect");
    	
    	return this.Enemies[index];
    }
    
    public AbstractEnemy[] getEnemies() {
    	return this.Enemies;
    }
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Zelda(), new Dimension(672,588), false);
        game.start();
    }
    
}
