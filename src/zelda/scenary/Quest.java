package zelda.scenary;

import java.awt.Graphics2D;

import zelda.Zelda;

import com.golden.gamedev.object.PlayField;

public class Quest extends PlayField {
    
    private Zelda game;
    
    private Board[][] boards;
    
    private QuestMenu menu;
    
    public Quest(Zelda game) {
        super();
        this.game = game;
        this.boards = new Board[1][1];
        this.initRessources();
    }

    private void initRessources() {
        this.menu = new QuestMenu(this.game);
        
        
        // Board (0, 0)
        Board b00 = new Board(this.game, 0, 0);
        
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_SOUTH_EAST_CORNER));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        
        
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_SOUTH_EAST_CORNER));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
            
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_SOUTH_EAST_CORNER));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
            
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_SOUTH_EAST_CORNER));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_SOUTH_WEST_CORNER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN_BORDER));
        
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_NORTH_EAST_CORNER));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));

        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Floor(this.game, Floor.Color.SAND));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        

        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
        
        this.add(b00);
        
    }
    
    
    public Board getCurrentBoard() {
        return this.boards[0][0];
    }
    
    public void add(Board board) {
        //this.addGroup(board.getBackground());
        //this.addGroup(board.getForground());
        this.boards[board.getX()][board.getY()] = board;
    }
        
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        this.boards[0][0].update(elapsedTime);
        this.menu.update(elapsedTime);
    }
    
    public void render(Graphics2D g) {
        super.render(g);
        this.boards[0][0].render(g);        
        this.menu.render(g);
    }
}
