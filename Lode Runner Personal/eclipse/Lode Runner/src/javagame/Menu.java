package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	public String mouse = "Get yo mouse in this screen!";
	Image intro;
	
	public Menu(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);
		
		intro = new Image("res/intro.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setBackground(Color.lightGray);
		
		intro.draw(0, 0, gc.getWidth(), gc.getHeight(), Color.white);
		g.drawString(mouse, 300, 530); //somehow y=330 appears on y=30 ...
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		//Keyboard/Mouse input
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();

		
		//Mouse Tracking
		if (Mouse.isInsideWindow()){
			mouse = "X: " + xpos + " Y: " + ypos;
			if (input.isMousePressed(0)){ sbg.enterState(1); } //change state to play
		}
		else
		{
			mouse = "Get yo mouse in this screen!";
		}
		if (input.isKeyPressed(Input.KEY_O)){ sbg.enterState(2); } //change state to options
		if (input.isKeyPressed(Input.KEY_C)){ sbg.enterState(3); } //change state to credits
	}
	
	public int getID(){
		return 0;
	}
}
