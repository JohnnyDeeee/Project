package org.loderunner.screens;

import org.loderunner.table.Account;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	UnicodeFont uiFont;
	private Color uiBlue = new Color(72,104,247);
	private Color uiRed = new Color(176,32,32);
	
	private int playerScore;
	
	public Play(int state){
		
	}
	
	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);

		//Loading things for font
		uiFont = new UnicodeFont("fonts/MINECRAFTIA-REGULAR.TTF", 22, true, false);
		uiFont.addAsciiGlyphs();
		uiFont.getEffects().add(new ColorEffect());
		uiFont.loadGlyphs();
		
		playerScore = 0;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setBackground(Color.black);
		
		//Draw string in specified font
		uiFont.drawString(100, 500, "SCORE", uiBlue);
		uiFont.drawString(250, 500, Integer.toString(playerScore), uiRed);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_UP)){ playerScore += 1; } //DEBUG
		if (input.isKeyDown(Input.KEY_DOWN)){ playerScore -= 1; } //DEBUG
		//if (input.isKeyPressed(Input.KEY_HOME)){ saveScore(); } //DEBUG
		
		/* NEED PLAYER INFO
		if (playerScore != this.ac.getScore()){
			ac.setScore(playerScore);
		}
		*/
	}
	
	public int getID(){
		return 1;
	}
	
	/* NEED PLAYER INFO
	private void saveScore(){
		ac.replaceSelected(ac.getUsername(), ac.getPassword(), this.playerScore);
	}
	*/
}
