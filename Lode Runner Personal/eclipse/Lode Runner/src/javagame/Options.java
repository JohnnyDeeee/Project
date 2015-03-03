package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Options extends BasicGameState{
	
	
	public Options(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("Welcome to the Options state", 100, 100);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		

	}
	
	public int getID(){
		return 2;
	}
}
