
package zelda;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import zelda.collisionManagers.Link_PlayfieldCollisionManager;
import zelda.objects.Blade;
import zelda.objects.Shield;
import zelda.scenary.Board;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

public class Link extends AnimatedSprite {
    
    private static final double SPEED = 0.2;  
    
    private static final int ANIMATION_DELAY = 100;  
    
    private static final int INVUNERABLE_DELAY = 1000;
    
    private Timer invulnerableTimer;
    
    private static final int FIGHT_TIMER = 100;
    
    public static final Shield.Kind DEFAULT_SHIELD = Shield.Kind.SMALL;
    
    public static final Orientation DEFAULT_ORIENTATION = Orientation.NORTH;
    
    private static SpriteGroup links_SGroup;
    
    private static SpriteGroup links_Attack_SGroup;
    
    private static SpriteGroup links_Vulnerable_SGroup;
    
    private Game game;
    
    private Blade.Kind blade;
    
    private Shield.Kind shield;
    
    private Orientation orientation;
    
    private int life;
    
    private Timer figth;
    
    public boolean wasTouched;
    
    public boolean canBeTouched;
    
    public Link(Game game) {
        this.game = game;
        
        //pour l'affichage de plus de vie, il faut ajouter des positions additionnels dans l'array de arrayPositionXVie dans Zelda
        this.life=6;
        
        
        this.shield = Link.DEFAULT_SHIELD;
        this.orientation = Link.DEFAULT_ORIENTATION;
        this.getAnimationTimer().setDelay(Link.ANIMATION_DELAY);
        this.invulnerableTimer = new Timer(INVUNERABLE_DELAY);
        this.figth = new Timer(Link.FIGHT_TIMER);
        this.figth.setActive(false);
        links_SGroup = new SpriteGroup("LINK SPRITE GROUP");
        links_Vulnerable_SGroup = new SpriteGroup("Link Vulnerable");
        wasTouched = false;
        canBeTouched = true;
        this.initResources();
    }
    
    private void initResources() {
        BufferedImage[] sprites = new BufferedImage[35];

        // Walk north
        sprites[0] = game.getImage("res/sprites/Link/GLWN1.gif");
        sprites[1] = game.getImage("res/sprites/Link/GLWN2.gif");
        // Walk south with small shield
        sprites[2] = game.getImage("res/sprites/Link/GLSSWS1.gif");
        sprites[3] = game.getImage("res/sprites/Link/GLSSWS2.gif");
        // Walk south with magical shield
        sprites[4] = game.getImage("res/sprites/Link/GLMSWS1.gif");
        sprites[5] = game.getImage("res/sprites/Link/GLMSWS2.gif");
        // Walk east with small shield
        sprites[6] = game.getImage("res/sprites/Link/GLSSWE1.gif");
        sprites[7] = game.getImage("res/sprites/Link/GLSSWE2.gif");
        // Walk east with magical shield
        sprites[8] = game.getImage("res/sprites/Link/GLMSWE1.gif");
        sprites[9] = game.getImage("res/sprites/Link/GLMSWE2.gif");
        // Walk west with small shield
        sprites[10] = game.getImage("res/sprites/Link/GLSSWW1.gif");
        sprites[11] = game.getImage("res/sprites/Link/GLSSWW2.gif");
        // Walk west with magical shield
        sprites[12] = game.getImage("res/sprites/Link/GLMSWW1.gif");
        sprites[13] = game.getImage("res/sprites/Link/GLMSWW2.gif");
        // Fight north with wood blade
        sprites[14] = game.getImage("res/sprites/Link/GLFWBN1.gif");
        sprites[15] = game.getImage("res/sprites/Link/GLFN1.gif");
        sprites[16] = game.getImage("res/sprites/Link/GLFWBN.png");
        // Fight south with wood blade and small shield
        sprites[17] = sprites[4];
        sprites[18] = game.getImage("res/sprites/Link/GLFS.gif");
        sprites[19] = game.getImage("res/sprites/Link/GLFWBS.png");
        // Fight south with wood blade and magical shield
        sprites[20] = sprites[4];
        sprites[21] = game.getImage("res/sprites/Link/GLFS.gif");
        sprites[22] = game.getImage("res/sprites/Link/GLFWBS.gif");
        // Fight east with wood blade and small shield
        sprites[23] = sprites[6];
        sprites[24] = game.getImage("res/sprites/Link/GLFE.gif");
        sprites[25] = game.getImage("res/sprites/Link/GLFWBE.png");
        // Fight east with wood blade and magical shield
        sprites[26] = sprites[8];
        sprites[27] = game.getImage("res/sprites/Link/GLFE.gif");
        sprites[28] = game.getImage("res/sprites/Link/GLFWBE.gif");
        // Fight west with wood blade and small shield
        sprites[29] = game.getImage("res/sprites/Link/GLFSSWBW.gif");
        sprites[30] = game.getImage("res/sprites/Link/GLFW.gif");
        sprites[31] = game.getImage("res/sprites/Link/GLFWBW.png");
        // Fight west with wood blade and magical shield
        sprites[32] = game.getImage("res/sprites/Link/GLFMSWBW.gif");
        sprites[33] = game.getImage("res/sprites/Link/GLFW.gif");
        sprites[34] = game.getImage("res/sprites/Link/GLFWBW.gif");
        
        // Sprites d'attaques seules - ordre : Sud, West, Nord, East
        links_Attack_SGroup = new SpriteGroup("LinkBlade Attack");
        links_Attack_SGroup.add(new Sprite(sprites[19]));
        links_Attack_SGroup.add(new Sprite(sprites[31]));
        links_Attack_SGroup.add(new Sprite(sprites[16]));
        links_Attack_SGroup.add(new Sprite(sprites[25]));
        
        // Sprites de link vulnérable seules        
        for(int i = 0; i < 13; i++) {
        	  links_Vulnerable_SGroup.add(new Sprite(sprites[i]));
        }
        
       
       // this.setImages(vulnerableLink);

        this.setImages(sprites);
        this.setLocation(256, 380);
        this.setAnimationFrame(0, 0);
    }
    
    public SpriteGroup getSpriteGroup() {
    	return links_SGroup;
    }
    
    public SpriteGroup getAttackSpriteGroup() {
    	return links_Attack_SGroup;
    }
    
    public SpriteGroup getVulnerableSpriteGroup() {
    	return links_Vulnerable_SGroup;
    }
    
    public void screamInPain() {
    	this.life -= 1;
    	this.wasTouched = true;
    	this.canBeTouched = false;
    	System.out.println("Ouuuuch");
    }
    
    public int getLinkLifePoints() {
    	return this.life;
    }
    
    public Orientation getOrientationLink() {  
    	return this.orientation;
    }
   

    public void setBoard(Board board) {
        links_SGroup.add(this);
        links_Attack_SGroup.add(this);
        links_Vulnerable_SGroup.add(this);
    }
    
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        
        if (this.figth.action(elapsedTime)) {
        	links_Attack_SGroup.setActive(true);
            this.figth.setActive(false);
            if (this.orientation.equals(Orientation.WEST)) {
                this.setX(this.getX() + 8);
                this.setAnimationFrame(31, 31);
            } else if (this.orientation.equals(Orientation.EAST)) {
                this.setX(this.getX() - 8);
                this.setAnimationFrame(25, 25);
            } 
            else if (this.orientation.equals(Orientation.SOUTH)) {
                this.setY(this.getY() - 8);
                this.setAnimationFrame(19, 19);
            }
            else if (this.orientation.equals(Orientation.NORTH)) {
                this.setY(this.getY() + 8);
                this.setAnimationFrame(16, 16);
            }
        } else {
        	links_Attack_SGroup.setActive(false);
        	canBeTouched = true;
        }
        
   
        if(!canBeTouched) {
        	links_Vulnerable_SGroup.setActive(false);
        } else {
        	links_Vulnerable_SGroup.setActive(true);
        }
        	
        if (wasTouched && invulnerableTimer.action(elapsedTime)) {
        	canBeTouched = true;
        	wasTouched = false;
        }
    
    }

    
    public void render(Graphics2D g) {
        super.render(g);
    }

    
    public void walk(Orientation direction) {
        if (!this.figth.isActive()) { 
            switch (direction) {
            case NORTH:
                this.setAnimationFrame(0, 1);
                this.setAnimate(true);
                this.setVerticalSpeed(-Link.SPEED);
                this.setHorizontalSpeed(0);
                this.orientation = Orientation.NORTH;
                break;
            case SOUTH:
                switch(this.shield) {
                case SMALL:
                    this.setAnimationFrame(2, 3);
                    break;
                case MAGICAL:
                    this.setAnimationFrame(4, 5);
                    break;
                default:
                    // do nothing
                }
                this.setAnimate(true);
                this.setVerticalSpeed(Link.SPEED);
                this.setHorizontalSpeed(0);
                this.orientation = Orientation.SOUTH;
                break;
            case EAST:
                switch(this.shield) {
                case SMALL:
                    this.setAnimationFrame(6, 7);
                    break;
                case MAGICAL:
                    this.setAnimationFrame(8, 9);
                    break;
                default:
                    // do nothing
                }
                this.setAnimate(true);
                this.setHorizontalSpeed(Link.SPEED);
                this.setVerticalSpeed(0);
                this.orientation = Orientation.EAST;
                break;
            case WEST:
                switch(this.shield) {
                case SMALL:
                    this.setAnimationFrame(10, 11);
                    break;
                case MAGICAL:
                    this.setAnimationFrame(12, 13);
                    break;
                default:
                    // do nothing
                }
                this.setAnimate(true);
                this.setHorizontalSpeed(-Link.SPEED);
                this.setVerticalSpeed(0);
                this.orientation = Orientation.WEST;
                break;
            default:
                // do nothing
            }
        }
    }
    
    public void fight() {
        if (!this.figth.isActive()) { 
        	canBeTouched = false;
            this.setSpeed(0, 0);
            this.figth.setActive(true);
            switch (this.orientation) {
            case NORTH:
                this.setY(this.getY() - 22);
                this.setAnimationFrame(16, 16);
                this.setAnimate(true);
                break;
            case SOUTH:
                switch(this.shield) {
                case SMALL:
                    this.setAnimationFrame(19, 19);
                    break;
                case MAGICAL:
                    this.setAnimationFrame(20, 22);
                    break;
                default:
                    // do nothing
                }
                this.setAnimate(true);
                break;
            case EAST:
                switch(this.shield) {
                case SMALL:
                    this.setAnimationFrame(25, 25);
                    break;
                case MAGICAL:
                    this.setAnimationFrame(26, 28);
                    break;
                default:
                    // do nothing
                }
                this.setAnimate(true);
                break;
            case WEST:
                this.setX(this.getX() - 22);
                switch(this.shield) {
                case SMALL:
                    this.setAnimationFrame(31, 31);
                    break;
                case MAGICAL:
                    this.setAnimationFrame(32, 34);
                    break;
                default:
                    // do nothing
                }
                this.setAnimate(true);
                this.orientation = Orientation.WEST;
                break;
            default:
                // do nothing
            }
        }
    }
     
}
    
