package zelda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import zelda.scenary.Quest;
import zelda.scenary.QuestMenu;
import zelda.scenary.Rock;

import zelda.scenary.Board;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class Zelda extends Game {
    
    private Link link;
   
    private Quest quest;
    
    private boolean menu;
    
    public Zelda() {
        
    }
    
    public void initResources() {
        this.quest = new Quest(this);
        this.link = new Link(this);
        this.link.setBoard(this.quest.getCurrentBoard());    
        this.menu = false;
    }
    
    public void transitionBoard(int x,int y) {
    	
    	//System.out.print(quest.getCurrentBoard().getX());
    	//System.out.println(quest.getCurrentBoard().getY());
    
    	//pour ne pas depasser l'integralite de la carte
    	
    	if ((x==0||x==1) && (y==0||y==1||y==2)) {
    	
    	if (link.getScreenY()<588 && link.getScreenX()>672 ) {	
    	//changer sur x a droite 
    		this.quest.changeBoard(x, y+1);
    		link.setBoard(this.quest.getCurrentBoard());
    		link.setLocation(300, 300);	
    	}
    	
    	if (link.getScreenY()<588 && link.getScreenX()<0 ) {
    		//changer sur x a gauche 
    		this.quest.changeBoard(x, y-1);
    		link.setBoard(this.quest.getCurrentBoard());
    		link.setLocation(300, 300);	
    	}
    	
    	//!!!------------>>>    todo >>  a changer la valeur de Y a cause du menu 
    	if (link.getScreenY()>588 && link.getScreenX()<672 ) {	
        	//changer sur y en haut  
    		this.quest.changeBoard(x+1, y);
    		link.setBoard(this.quest.getCurrentBoard());
    		link.setLocation(300, 300);	
        	}
        	
        	if (link.getScreenY()<0 && link.getScreenX()<672 ) {
        		//changer sur y a gauche 
        		this.quest.changeBoard(x-1, y);
        		link.setBoard(this.quest.getCurrentBoard());
        		link.setLocation(300, 300);	
        	}
        	}
        		
        
    }
    	
    	
    
    
 
        
    public void update(long elapsedTime) {
    	//transition de board quand link bouge 
    	int x = quest.getCurrentBoard().getX();
    	int y = quest.getCurrentBoard().getY();
    	transitionBoard(x,y);

    	
    	
    	
    	
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
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.quest.render(g);
        this.link.render(g);
    }
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Zelda(), new Dimension(672,588), false);
        game.start();
    }
    
}
