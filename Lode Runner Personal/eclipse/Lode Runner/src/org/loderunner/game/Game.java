package org.loderunner.game;

import org.loderunner.screens.Credits;
import org.loderunner.screens.Menu;
import org.loderunner.screens.Options;
import org.loderunner.screens.Play;
import org.loderunner.screens.Score;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	
	public static final String gameversion = "v0.01";
	public static final String gamename = "Lode Runner" + " " + gameversion;
	public static final int menu = 0;
	public static final int play = 1;
	public static final int options = 2;
	public static final int credits = 3;
	public static final int score = 4;

	public Game(String gamename){
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Options(options));
		this.addState(new Credits(credits));
		this.addState(new Score(score));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(options).init(gc, this);
		this.getState(credits).init(gc, this);
		this.getState(score).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		
		try {
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
