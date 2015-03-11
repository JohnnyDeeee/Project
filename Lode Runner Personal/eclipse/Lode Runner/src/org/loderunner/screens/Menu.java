package org.loderunner.screens;

import org.loderunner.gui.PasswordTextField;
import org.loderunner.table.Account;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	private Image menuBackground;
	
	private TextField login;
	private PasswordTextField pass;
	
	private String username;
	private String password;
	
	private UnicodeFont uiFont;
	private Color uiBlue = new Color(72,104,247);
	private Color uiRed = new Color(176,32,32);
	
	public Menu(int state){
		
	}
	
	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		//disable fps meter
		gc.setShowFPS(false);
		
		//Loading things for font
		uiFont = new UnicodeFont("fonts/MINECRAFTIA-REGULAR.TTF", 22, true, false);
		uiFont.addAsciiGlyphs();
		uiFont.getEffects().add(new ColorEffect());
		uiFont.loadGlyphs();
		
		//background
		menuBackground = new Image("res/intro.png");
		
		//TextFields
		login = new TextField(gc, uiFont, 400, 360, 250, 30);
		login.setBackgroundColor(Color.darkGray);
		login.setBorderColor(uiBlue);
		login.setTextColor(uiRed);
		login.setFocus(true);
		
		pass = new PasswordTextField(gc, uiFont, 400, login.getY() + 70, 250, 30);
		pass.setBackgroundColor(Color.darkGray);
		pass.setBorderColor(uiBlue);
		pass.setTextColor(uiRed);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//background
		g.setBackground(Color.lightGray);
		menuBackground.draw(0, 0, gc.getWidth(), gc.getHeight(), Color.white);
		
		//Textfields and buttons
		uiFont.drawString(login.getX(), login.getY() - 30, "Username:", uiBlue);
		login.render(gc, g);
		uiFont.drawString(pass.getX(), pass.getY() - 30, "Password:", uiBlue);
		pass.render(gc, g);
		uiFont.drawString(pass.getX(), pass.getY() + 60, "Press ENTER to login", uiRed);
		
		//g.drawString(mouse, 300, 530); //graphic 0,0 is in top left corner
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		//Keyboard input
		Input input = gc.getInput();

		//Action keys in TextFields
		if (login.hasFocus()){
			if (input.isKeyPressed(Input.KEY_ENTER)){
				pass.setFocus(true);
			}
			else if (input.isKeyPressed(Input.KEY_DOWN)){
				pass.setFocus(true);
			}
			else if (input.isKeyPressed(Input.KEY_TAB)){
				pass.setFocus(true);
			}
		}
		else if (pass.hasFocus()){
			if (input.isKeyPressed(Input.KEY_ENTER)){
				saveUser(login.getText(), pass.getText());
			}
			else if (input.isKeyPressed(Input.KEY_UP)){
				login.setFocus(true);
			}
		}
		
		
		//if (input.isKeyPressed(Input.KEY_O)){ sbg.enterState(2); } //change state to options
		//if (input.isKeyPressed(Input.KEY_C)){ sbg.enterState(3); } //change state to credits
		//if (input.isKeyPressed(Input.KEY_S)){ sbg.enterState(4); } //change state to score
		
	}
	
	public int getID(){
		return 0;
	}
	
	public void saveUser(String username, String password){
		Account ac = new Account(username, password);
		System.out.println("Username: " + ac.getUsername() + " Password: " + ac.getPassword() + " Score: " + ac.getScore()); //DEBUG
	}
}
