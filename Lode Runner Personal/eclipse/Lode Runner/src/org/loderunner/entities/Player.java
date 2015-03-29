package org.loderunner.entities;

import java.util.ArrayList;

import org.loderunner.table.Account;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	//Fields
	private float posX, posY;
	private SpriteSheet run_right_sheet, run_left_sheet, ladder_climb_sheet, bar_climb_sheet, falling_sheet, idle_right_sheet, idle_left_sheet;
	private Animation player_animation, run_right_animation, run_left_animation, ladder_climb_animation, bar_climb_animation, falling_animation, idle_right_animation, idle_left_animation;
	private ArrayList<Animation> animations = new ArrayList<Animation>();
	public Shape boundingbox;
	
	//Properties
	public void setPos(float posX, float posY){
		this.posX = posX;
		this.posY = posY;
	}
	public float getPosX(){
		return this.posX;
	}
	public float getPosY(){
		return this.posY;
	}
	public void setAnimation(Animation am){
		this.player_animation = am;
	}
	public void stopAnimation(){
		player_animation.stop();
	}
	public void startAnimation(){
		player_animation.start();
	}
	public ArrayList<Animation> getAnimation(){
		return animations;
	}
	public Animation getCurrentAnimation(){
		return this.player_animation;
	}
	
	//Constructor
	public Player(float posX, float posY, GameContainer gc) throws SlickException{
		this.posX = posX;
		this.posY = posY;
		init(gc);
	}
	
	//Methods
	private void init(GameContainer gc) throws SlickException {
		run_right_sheet = new SpriteSheet("res/player/run_right.png", 32, 32);
		run_left_sheet = new SpriteSheet(run_right_sheet.getFlippedCopy(true, false), 32, 32);
		ladder_climb_sheet = new SpriteSheet("res/player/ladder_climb.png", 32, 32);
		bar_climb_sheet = new SpriteSheet("res/player/bar_climb.png", 32, 32);
		falling_sheet = new SpriteSheet("res/player/falling.png", 32, 32);
		idle_right_sheet = new SpriteSheet("res/player/idle_right.png", 32, 32);
		idle_left_sheet = new SpriteSheet(idle_right_sheet.getFlippedCopy(true, false),  32, 32);
		
		run_right_animation = new Animation(run_right_sheet, 150);
		run_left_animation = new Animation(run_left_sheet, 150);
		ladder_climb_animation = new Animation(ladder_climb_sheet, 150);
		bar_climb_animation = new Animation(bar_climb_sheet, 250);
		falling_animation = new Animation(falling_sheet, 200);
		idle_right_animation = new Animation(idle_right_sheet, 800);
		idle_left_animation = new Animation(idle_left_sheet, 800);
		
		player_animation = idle_right_animation;
		
		animations.add(0, run_right_animation);
		animations.add(1, run_left_animation);
		animations.add(2, ladder_climb_animation);
		animations.add(3, bar_climb_animation);
		animations.add(4, falling_animation);
		animations.add(5, idle_right_animation);
		animations.add(6, idle_left_animation);
		
		boundingbox = new Rectangle(posX, posY, 32, 32);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		player_animation.draw(posX, posY);
		//player_animation.stop();
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		player_animation.update(delta);
	}
	
	public boolean equals(Animation am){
		if (player_animation.equals(am)){
			return true;
		}else{
			return false;
		}
	}
}
