package zelda.scenary;

import com.golden.gamedev.object.Sprite;

import zelda.Zelda;

public class Rock extends AbstractTile {
    
    public enum Kind {
        GREEN_PLAIN,
        GREEN_PLAIN_BORDER,
        GREEN_INDENTED,
        GREEN_SOUTH_EAST_CORNER,
        GREEN_SOUTH_WEST_CORNER,
        GREEN_NORTH_EAST_CORNER,
        GREEN_NORTH_WEST_CORNER,
        BUSH,
        GREEN_STANDALONE,
        ORANGE_STANDALONE,
        FIRE,
        RED_WALL,
        DJ_WALL
    }
    
    private Kind kind;
    
    public Rock(Zelda game, Kind kind) {
        super(game, 2, 2, 42, 42);
            this.kind = kind;
            switch (this.kind) {
            case GREEN_NORTH_WEST_CORNER:
            this.add("res/sprites/scenary/GREEN_NORTH_WEST_ROCK_1.GIF", -1);
            this.add("res/sprites/scenary/GREEN_NORTH_WEST_ROCK_2.GIF", 1);
            this.add("res/sprites/scenary/GREEN_NORTH_WEST_ROCK_3.GIF", 1);
            this.add("res/sprites/scenary/GREEN_NORTH_WEST_ROCK_4", 1);
            break;
        case GREEN_INDENTED:
            this.add("res/sprites/scenary/GREEN_INDENTED_ROCK_1.GIF", 1);
            this.add("res/sprites/scenary/GREEN_INDENTED_ROCK_2.GIF", 1);
            this.add("res/sprites/scenary/GREEN_INDENTED_ROCK_3.GIF", 1);
            this.add("res/sprites/scenary/GREEN_INDENTED_ROCK_4.GIF", 1);
            break;
        case GREEN_NORTH_EAST_CORNER:
            this.add("res/sprites/scenary/GREEN_NORTH_EAST_ROCK_1.GIF", -1);
            this.add("res/sprites/scenary/GREEN_NORTH_EAST_ROCK_2.GIF", -1);
            this.add("res/sprites/scenary/GREEN_NORTH_EAST_ROCK_3.GIF", 1);
            this.add("res/sprites/scenary/GREEN_NORTH_EAST_ROCK_4.GIF", 1);
            break;
        case GREEN_SOUTH_EAST_CORNER:
            this.add("res/sprites/scenary/GREEN_SOUTH_EAST_ROCK_1.GIF", 1);
            this.add("res/sprites/scenary/GREEN_SOUTH_EAST_ROCK_2.GIF", -1);
            this.add("res/sprites/scenary/GREEN_SOUTH_EAST_ROCK_3.GIF", -1);
            this.add("res/sprites/scenary/GREEN_SOUTH_EAST_ROCK_4.GIF", -1);
            break;
        case GREEN_PLAIN_BORDER:
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_1.GIF", 1);
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_2.GIF", 1);
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_3.GIF", -1);
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_4.GIF", -1);
            break;
        case GREEN_PLAIN:
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_1.GIF", 1);
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_2.GIF", 1);
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_3.GIF", 1);
            this.add("res/sprites/scenary/GREEN_PLAIN_ROCK_4.GIF", 1);
            break;
        case GREEN_SOUTH_WEST_CORNER:
            this.add("res/sprites/scenary/GREEN_SOUTH_WEST_ROCK_1.GIF", -1);
            this.add("res/sprites/scenary/GREEN_SOUTH_WEST_ROCK_2.GIF", 1);
            this.add("res/sprites/scenary/GREEN_SOUTH_WEST_ROCK_3.GIF", 1);
            this.add("res/sprites/scenary/GREEN_SOUTH_WEST_ROCK_4.GIF", -1);
            break;
        case BUSH: 
        	this.add("res/sprites/scenary/BGGSH.GIF", 1);
        	break;
        case GREEN_STANDALONE: 
        	this.add("res/sprites/scenary/GREEN_ROCK_7.GIF", 1);
        	break;
        case ORANGE_STANDALONE: 
        	this.add("res/sprites/scenary/BGBR.GIF", 1);
        	break;
        case RED_WALL:
        	this.add("res/sprites/scenary/BGBSR.GIF", 1);
        	break;
        case FIRE:
        	this.add("res/sprites/scenary/fire_1.png", 1);
        	break;
        case DJ_WALL:
        	this.add("res/sprites/Dongeon/4/DG4F1.png", 1);
        	break;
        }

    }

}
