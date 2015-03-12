package org.loderunner.screens;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;

public class Score extends BasicGameState{
	
	UnicodeFont uiFont;
	private Color uiBlue = new Color(72,104,247);
	private Color uiRed = new Color(176,32,32);
	
	Image scoreBackground;
	
	public Score(int state){
		
	}
	
	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
		
		//Loading things for font
		uiFont = new UnicodeFont("fonts/MINECRAFTIA-REGULAR.TTF", 22, true, false);
		uiFont.addAsciiGlyphs();
		uiFont.getEffects().add(new ColorEffect());
		uiFont.loadGlyphs();
		
		scoreBackground = new Image("res/Highscores.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		scoreBackground.draw(0, 0, gc.getWidth(), gc.getHeight(), Color.white);
		
		uiFont.drawString(100, 100, "Score: ", uiBlue);
		//uiFont.drawString(250, 100, Integer.toString(playerScore), uiRed);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		//Keyboard/Mouse input
		Input input = gc.getInput();
				
		if (input.isKeyDown(Input.KEY_BACK)){ sbg.enterState(0); } //change state to menu
	}
	
	public int getID(){
		return 4;
	}
}
