package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	UnicodeFont uiFont;
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
		
		uiFont = new UnicodeFont("fonts/MINECRAFTIA-REGULAR.TTF", 22, false, false);
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
		uiFont.drawString(200, 200, "Welcome to the Play state", Color.red);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		

	}
	
	public int getID(){
		return 1;
	}
}
