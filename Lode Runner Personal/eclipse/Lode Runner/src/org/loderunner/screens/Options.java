package org.loderunner.screens;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Options extends BasicGameState{
	
	Image optionsBackground;
	
	public Options(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
		
		optionsBackground = new Image("res/Options.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		optionsBackground.draw(0, 0, gc.getWidth(), gc.getHeight(), Color.white);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		//Keyboard/Mouse input
		Input input = gc.getInput();
				
		if (input.isKeyDown(Input.KEY_BACK)){ sbg.enterState(0); } //change state to menu
	}
	
	public int getID(){
		return 2;
	}
}
