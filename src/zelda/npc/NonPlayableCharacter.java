package zelda.npc;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import zelda.Zelda;
import zelda.scenary.Board;

public class NonPlayableCharacter extends Sprite {
	
	private String sentence;
	
	private BufferedImage img;
	
	private Board board;
	
	private Zelda game;
	
	private boolean canInteract = true; // !canInteract si présent que dans certaines conditions
	
	
	public NonPlayableCharacter(Zelda game, Board b, double posX, double posY) {
		this.game = game;
		this.board = b;
		this.setLocation(posX, posY); 
	}
	
	public void setImg(String imgURL) {
		this.img = this.game.getImage(imgURL);
		this.setImage(img);
	}
	
	public boolean canInteract() {
		return this.canInteract;
	}
	
	public void setInteractable(Boolean b) {
		this.canInteract = b;
		this.setActive(b);
	}
	
	public Sprite getSprite() {
		return this;
	}
	
	public void setSentence(String s) {
		this.sentence = s;
	}
	
	public String getSentence() {
		return this.sentence;
	}

	public boolean isOnBoard(Board currentBoard) {
		return this.board == currentBoard;
	}


}
