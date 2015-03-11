package org.loderunner.screens;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends BasicGameState{
	
	Image creditsBackground;
	
	public Credits(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
		
		creditsBackground = new Image("res/Credits.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		creditsBackground.draw(0, 0, gc.getWidth(), gc.getHeight(), Color.white);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		//Keyboard/Mouse input
		Input input = gc.getInput();
				
		if (input.isKeyPressed(Input.KEY_BACK)){ sbg.enterState(0); } //change state to menu
	}
	
	public int getID(){
		return 3;
	}
}
