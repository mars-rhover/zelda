package zelda.scenary;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import zelda.Zelda;
import zelda.collisionManagers.Link_EnemyCollisionManager;
import zelda.collisionManagers.Link_PlayfieldCollisionManager;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class Quest extends PlayField {
    
    private Zelda game;
    
    private Board[][] boards;
    
    // Indexes X-Y de la board actuelle
    private int curBoardIndexX;
    private int curBoardIndexY;
    
    private QuestMenu menu;
    
    public Quest(Zelda game) {
        super();
        this.game = game;
        
        String boardSchema = this.getBoardSchema("res/boards/");
        int maxX = Integer.parseInt(boardSchema.substring(0, 1));
        int maxY = Integer.parseInt(boardSchema.substring(2, 3));
        
        this.boards = new Board[maxX][maxY];
        
        // Board de départ
        curBoardIndexX = 0;
        curBoardIndexY = 0;
        
        this.initRessources();
    }
    
    //////////////////////////////////////////////////// COLLISION MANAGERS DANS QUEST
    public void createCollisionManagers() {
    	
    	SpriteGroup Link_SG = this.game.getLink().getSpriteGroup();
    	Board boardActuelle = this.boards[curBoardIndexX][curBoardIndexY];
    	
    	// Collision Link - playfield pour la board actuelle
    	this.addCollisionGroup(Link_SG, boardActuelle.getForeground(), new Link_PlayfieldCollisionManager(this.game.getLink()));

    	
    	// Collisions Link - Enemy qui sont sur la board actuelle
    	for(int i = 0; i < this.game.getEnemies().length; i++) {
    		if(this.game.getEnemy(i).getBoard() == boardActuelle) {
    			SpriteGroup Enemy_SG = this.game.getEnemy(i).getSpriteGroup();
        		this.addCollisionGroup(Link_SG, Enemy_SG, new Link_EnemyCollisionManager(this.game.getLink(),this.game.getEnemy(i)));
    		}
    	 }
   
	}
    
    
    /////////////////////////////////////////////////// Fin collision managers

	// Changer de board : indiquer l'index x et y de la board
    public void changeBoard(int x, int y) {
    	curBoardIndexX = x;
        curBoardIndexY = y;
        
        // Désactiver tous les collisions groups actuels
        for(int i = 0; i<this.getCollisionGroups().length; i++) {
        	this.getCollisionGroups()[i].setActive(false);
        }
       
        // Créé des collisionManagers pour la board actuelle
        createCollisionManagers();
        
    }

    private void initRessources() {
        this.menu = new QuestMenu(this.game);
    
        // Lire un/des fichiers textes qui décrivent le board, et pour chaque tile ajouter 
        Path path = Paths.get("res/boards/");
    	
		// Récupérer le contenu du répertoire dans un flux 
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			// for each : regarde chaque fichier du stream
			
		    for (Path filePath: stream) {
		        if(Files.isRegularFile(filePath)) {	
		        	// Pour chaque fichier : lire le contenu
		        	
		        	// Analyser le nom du fichier pour avoir sa position
		        	String boardName = filePath.getFileName().toString();
		        	int indexX = Integer.parseInt(boardName.substring(1, 2));
		        	int indexY = Integer.parseInt(boardName.substring(2, 3));
		        	
		        	String board = this.getBoard(filePath.toString());
		        	 Board b00 = new Board(this.game, indexX, indexY);
		             
		             // Lire le string caractère par charactère
		                for(int i = 0; i < board.length() ; i++) {
		                	char c = board.toUpperCase().charAt(i);
		                	if(c == 'X') {
		                		b00.add(new Rock(this.game, Rock.Kind.GREEN_PLAIN));
		                	} 
		                	else if (c == '.') {
		                		 b00.add(new Floor(this.game, Floor.Color.SAND));
		                	} 
		                	else if (c == '/') {
		                	    b00.add(new Rock(this.game, Rock.Kind.GREEN_SOUTH_EAST_CORNER));
		                	} 
		                	else if (c == '\\') {
		                		b00.add(new Rock(this.game, Rock.Kind.GREEN_SOUTH_WEST_CORNER));
		                	}
		                	else if (c == 'M') {
		                		b00.add(new Rock(this.game, Rock.Kind.GREEN_INDENTED));
		                	}
		                	else if (c == 'B') {
		                		b00.add(new Rock(this.game, Rock.Kind.BUSH));
		                	}
		                	else if (c == 'Q') {
		                		b00.add(new Rock(this.game, Rock.Kind.GREEN_STANDALONE));
		                	}
		                	else if (c == '?') {
		                		b00.add(new Door(this.game, Door.Kind.OUTDOOR_DOOR));
		                	}
		                	else if (c == 'O') {
		                		b00.add(new Rock(this.game, Rock.Kind.ORANGE_STANDALONE));
		                	}
		                	else if (c == 'e') {
		                		b00.add(new Door(this.game, Door.Kind.STAIRS));
		                	}
		                	else if (c == '?') {
		                		b00.add(new Door(this.game, Door.Kind.FLOOR_MOVE));
		                	}
		                }
		                
		                this.add(b00);
		            	
		        }
		    }
		    

		} catch (IOException x) {
		    System.err.println(x);
		}

        
    }
    
    
    public Board getCurrentBoard() {
    	///A AJOUTER TRY CATCH POUR 
    	
        return this.boards[curBoardIndexX][curBoardIndexY];
    }
    
    public void add(Board board) {
        //this.addGroup(board.getBackground());
        //this.addGroup(board.getForground());
        this.boards[board.getX()][board.getY()] = board;
    }
        
    public void update(long elapsedTime) {

        super.update(elapsedTime);
        this.boards[curBoardIndexX][curBoardIndexY].update(elapsedTime);
        this.menu.update(elapsedTime);
        this.checkCollisions();  
    }
    
    public void render(Graphics2D g) {
        super.render(g);
        this.boards[curBoardIndexX][curBoardIndexY].render(g);        
        this.menu.render(g);
    }
    
    //// AJOUTE 
    
    private String getBoard(String boardPath) {
    	Path path = Paths.get(boardPath);
		boolean doFileExist = Files.exists(path);
		String s = ""; // String dans lequel on copie les charactères
		
		if (doFileExist) {
			// ouverture du fichier dans un BufferedReader
			try (Reader in = Files.newBufferedReader(path, Charset.defaultCharset()) ) {
				int c; // code Utf8/ASCII du charactère (dépends du charset)
			
				// si le caractère lu est égale à -1 alors on a atteint la fin du flux (i.e. la fin du fichier)
				while ((c = in.read()) != -1) {
					// on affiche le charactère en transtypant son code UTF-8/ASCII
					char charact = (char) c;	// Conversion en char
					s += charact;	// Ajout à la chaine totale			
				}
				//System.out.print(s.toUpperCase());	// Affichage
			} catch (IOException e) {
				System.err.println("Echec de la lecture du fichier "+path);
			} 
			
		}
		return s;
    }
     
    
    // Compter le nombre de boards totales en X et Y dans la playfield
    private String getBoardSchema(String boardPath) {
    	String boardSchema = ""; // String qui décrit le schéma 5x6 = 5 sur 6
    
    	 // Lire un/des fichiers textes qui décrivent le board, et pour chaque tile ajouter 
        Path path = Paths.get("res/boards/");
    	
    	int maxIndexX=0;
    	int maxIndexY=0;
        
		// Récupérer le contenu du répertoire dans un flux 
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			// for each : regarde chaque fichier du stream
		
		    for (Path filePath: stream) {
		        if(Files.isRegularFile(filePath)) {	
		        	
		        	String boardName = filePath.getFileName().toString();
		        	// Pour chaque fichier : regarder son indexX et indexY
		        	int indexX = Integer.parseInt(boardName.substring(1, 2));
		        	int indexY = Integer.parseInt(boardName.substring(2, 3));
		        			        	
		        	// Si son indexX est supérieur au maxIndex, le remplacer
		        	maxIndexX =  indexX > maxIndexX? indexX : maxIndexX;
		        	maxIndexY =  indexY > maxIndexY? indexY : maxIndexY;
		        }
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
		boardSchema = (maxIndexX+1)+"x"+(maxIndexY+1);
    	return boardSchema;
    }
    
    
}