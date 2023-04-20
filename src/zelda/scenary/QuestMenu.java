package zelda.scenary;

import java.awt.Graphics2D;

import zelda.Zelda;

import zelda.Link;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class QuestMenu {
    
    private SpriteGroup sprites;
    private SpriteGroup spritesVie;
    private Zelda game;
    
    public QuestMenu(Zelda game) {
        this.game = game;
        this.spritesVie=new SpriteGroup("");
        this.sprites = new SpriteGroup("");
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/B_CASE.GIF"), 330, 30));
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/A_CASE.GIF"), 400, 30));

        this.sprites.add(new Sprite(this.game.getImage("res/sprites/LIFE.GIF"), 500, 30));
       
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/BOMB.GIF"), 230, 79));
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/X.GIF"), 270, 80));
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/KEY.GIF"), 230, 50));
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/X.GIF"), 270, 50));
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/PIECE.GIF"), 230, 20));
        this.sprites.add(new Sprite(this.game.getImage("res/sprites/X.GIF"), 270, 20));
        
        
       
       
        Sprite s1=new Sprite(this.game.getImage("res/sprites/Objects/OHC.gif"), 590, 60);
        Sprite s2=new Sprite(this.game.getImage("res/sprites/Objects/OHC.gif"), 573, 60);
        Sprite s3=new Sprite(this.game.getImage("res/sprites/Objects/OHC.gif"), 556, 60);
        Sprite s4=new Sprite(this.game.getImage("res/sprites/Objects/OHC.gif"), 539, 60);
        Sprite s5=new Sprite(this.game.getImage("res/sprites/Objects/OHC.gif"), 522, 60);
        this.spritesVie.add(s5);
        this.spritesVie.add(s4);
        this.spritesVie.add(s3);
        this.spritesVie.add(s2);
        this.spritesVie.add(s1);
        
    }
    
    public void move(long elapsedTime, boolean flag) {
        Sprite[] s = this.sprites.getSprites();
        for (int i = 0; i < this.sprites.getSize(); i++) {
            if (flag) 
                s[i].moveTo(elapsedTime, s[i].getX(), s[i].getY() + 300, 1);
            else 
                s[i].moveTo(elapsedTime, s[i].getX(), s[i].getY() - 300, 1);
        }
        
    }
    
    //ajout pour afficher les vie de link dans le menu
    public void afficherVie() {
    	Sprite[] vies = this.spritesVie.getSprites();
    	int LinklifePoints = this.game.getLink().getLinkLifePoints();
    	int oldLinklifePoints = LinklifePoints;
    	
    	
//    	if (oldLinklifePoints!=LinklifePoints) {
//    		System.out.print(this.spritesVie.getSprites());
//    	}
//    	
    	
    	for (int i=0;i<LinklifePoints;i++) {
    		//vies[i].moveTo( LinklifePoints, oldLinklifePoints, i)
    		vies[i].setLocation(vies[i].getX(), vies[i].getY());
    		}
    	
    	
    

	}
   
    
    
    public void update(long elapsedTime) {
    	this.afficherVie();
    	//creer spriteGroup de vies, les ajouter dans update et render
    	
        this.sprites.update(elapsedTime);
        this.spritesVie.update(elapsedTime);
       
    }
    
    public void render(Graphics2D g) {
    	
        this.sprites.render(g);
        this.spritesVie.render(g);
        
    }
}
