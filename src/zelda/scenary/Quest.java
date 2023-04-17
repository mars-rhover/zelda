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

import com.golden.gamedev.object.PlayField;

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
        curBoardIndexX = 1;
        curBoardIndexY = 1;
        
        this.initRessources();
    }
    
    // Changer de board : indiquer l'index x et y de la board
    public void changeBoard(int x, int y) {
    	curBoardIndexX = x;
        curBoardIndexY = y;
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
		        	System.out.println(boardName+" : ");
		        	int indexX = Integer.parseInt(boardName.substring(1, 2));
		        	int indexY = Integer.parseInt(boardName.substring(2, 3));
		        	System.out.println(boardName+" : "+indexX+" "+indexY);
		        	
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
		                }
		                
		                this.add(b00);
		            	
		        }
		    }
		    

		} catch (IOException x) {
		    System.err.println(x);
		}

        
    }
    
    
    public Board getCurrentBoard() {
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
     
    
    // Re
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
		        	System.out.println("Fichier : "+filePath);
		        	
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