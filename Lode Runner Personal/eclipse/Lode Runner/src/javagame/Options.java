package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Options extends BasicGameState{
	
	Image options;
	
	public Options(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
		
		options = new Image("res/Options.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		options.draw(0, 0, gc.getWidth(), gc.getHeight(), Color.white);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		//Keyboard/Mouse input
		Input input = gc.getInput();
				
		if (input.isKeyPressed(Input.KEY_BACK)){ sbg.enterState(0); } //change state to credits
	}
	
	public int getID(){
		return 2;
	}
}