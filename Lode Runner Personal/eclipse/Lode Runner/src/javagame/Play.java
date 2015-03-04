package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	UnicodeFont uiFont;
	private int score;
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
		
		uiFont = new UnicodeFont("fonts/MINECRAFTIA-REGULAR.TTF", 22, true, false);
		score = 0000000;
	}
	
	@SuppressWarnings("unchecked")
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setBackground(Color.black);
		
		//Loading things for fonts
		uiFont.addAsciiGlyphs();
		//uiFont.addGlyphs(400, 600);
		uiFont.getEffects().add(new ColorEffect());
		uiFont.loadGlyphs();
		
		//Draw string in specified font
		uiFont.drawString(100, 500, "SCORE", new Color(72,104,247));
		uiFont.drawString(250, 500, Integer.toString(score), new Color(176,32,32));
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		

	}
	
	public int getID(){
		return 1;
	}
}
