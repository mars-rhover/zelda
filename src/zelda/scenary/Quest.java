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
    
    private QuestMenu menu;
    
    public Quest(Zelda game) {
        super();
        this.game = game;
        this.boards = new Board[2][1];
        this.initRessources();
    }

    
    
    private void initRessources() {
        this.menu = new QuestMenu(this.game);
        
        // Board (0, 0)
        // TODO : Lire un/des fichiers textes qui décrivent le board, et pour chaque tile ajouter 
        Path path = Paths.get("res/boards/");
    	
		// Récupérer le contenu du répertoire dans un flux 
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			// for each : regarde chaque fichier du stream
			
			
			
			int count=0;
		    for (Path filePath: stream) {
		        if(Files.isRegularFile(filePath)) {	
		        	// Pour chaque fichier : lire le contenu
		        	
		        	// Analyser le nom du fichier pour avoir sa position
		        	String boardName = filePath.getFileName().toString();
		        	System.out.println(boardName.substring(0, 3));
		        	
		        	String board = this.getBoard(filePath.toString());
		        	 Board b00 = new Board(this.game, count, 0);
		             
		             // Lire le string caractère par charactère
		                for(int i = 0; i < board.length(); i++) {
		                	char c = board.charAt(i);
		                	if(c == 'x') {
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
		                }
		                
		                this.add(b00);
		            	count+=1;
		        }
		    }
		    

		} catch (IOException x) {
		    System.err.println(x);
		}

        
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
    
    //// AJOUTE 
    
    private String getBoard(String boardPath) {
    	Path path = Paths.get(boardPath);
		boolean doFileExist = Files.exists(path);
		System.out.println("Le fichier à "+path.toString()+" existe : "+doFileExist);
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
				System.out.print(s.toUpperCase());	// Affichage
			} catch (IOException e) {
				System.err.println("Echec de la lecture du fichier "+path);
			} 
			
		}
		return s;
    }
        
    
    
}