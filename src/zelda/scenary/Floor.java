
package zelda.scenary;

import com.golden.gamedev.object.Sprite;

import zelda.Zelda;

public class Floor extends AbstractTile {
    
    public enum Color {
        GREEN,
        DARCK,
        SAND,
        YELLOW
    }
    private Color color;
    
    public Floor(Zelda game, Color color) {
        super(game, 1, 1, 42, 42);
        this.color = color;
        switch(color) {
        case SAND:
            this.add(new Sprite(this.game.getImage("res/sprites/scenary/BGGF.gif")), -1);
            break;
        case GREEN:
            break;
        case DARCK:
            this.add(new Sprite(this.game.getImage("res/sprites/scenary/BGGSD.png")), -1);
            break;
        case YELLOW: 
            this.add(new Sprite(this.game.getImage("res/sprites/Dongeon/4/DG4F6.png")), -1);
            break;
        
        }
    }
}
