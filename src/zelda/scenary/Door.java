package zelda.scenary;

import com.golden.gamedev.object.Sprite;

import zelda.Zelda;
import zelda.scenary.Rock.Kind;

public class Door extends AbstractTile {

	public enum Kind {
        OUTDOOR_DOOR,
        STAIRS,
        FLOOR_MOVE
    }
    
    private Kind kind;
    
    public Door(Zelda game, Kind kind) {
        super(game, 2, 2, 42, 42);
            this.kind = kind;
            switch (this.kind) {
        case OUTDOOR_DOOR:
            this.add("res/sprites/scenary/OUTSIDE_DOOR.GIF", -1);
            
            break;
        case STAIRS:
            this.add("res/sprites/scenary/BGGDS.GIF", -1);
            break;
        case FLOOR_MOVE:
        	this.add(new Sprite(this.game.getImage("res/sprites/scenary/BGGF.gif")), -1);
            break;
        }

    }

}
