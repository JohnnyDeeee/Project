package org.loderunner.screens;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.loderunner.entities.Player;
import org.loderunner.table.Account;
import org.loderunner.table.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	private UnicodeFont uiFont;
	private Color uiBlue = new Color(72,104,247);
	private Color uiRed = new Color(176,32,32);
	
	private int playerScore;
	private int playerLives;
	private int currentLevel;
	
	private Level level;
	private Player player;
	
	private Animation run_right, run_left, ladder_climb, bar_climb, falling, idle_right, idle_left;
	
	private float playerX;
	private float playerY;
	private boolean canClimb;
	
	
	public Play(int state){
		
	}
	
	@SuppressWarnings("unchecked")
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setShowFPS(false);

		//Loading things for font
		uiFont = new UnicodeFont("fonts/MINECRAFTIA-REGULAR.TTF", 22, true, false);
		uiFont.addAsciiGlyphs();
		uiFont.getEffects().add(new ColorEffect());
		uiFont.loadGlyphs();
		
		level = new Level("Level1.txt", gc);
		playerScore = 0;
		playerLives = 3;
		currentLevel = 1;
		
		playerX = 112.0f;
		playerY = 452.0f;
		player = new Player(playerX, playerY, gc);
		run_right = player.getAnimation().get(0);
		run_left = player.getAnimation().get(1);
		ladder_climb = player.getAnimation().get(2);
		bar_climb = player.getAnimation().get(3);
		falling = player.getAnimation().get(4);
		idle_right = player.getAnimation().get(5);
		idle_left = player.getAnimation().get(6);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.setBackground(Color.black);
		
		//Draw string in specified font
		uiFont.drawString(50, 30, "PLAYER: ", uiBlue);
		uiFont.drawString(170, 30, Account.getUsername(), uiRed);
		
		uiFont.drawString(50, 550, "SCORE: ", uiBlue);
		uiFont.drawString(150, 550, Integer.toString(playerScore), uiRed);
		
		uiFont.drawString(300, 550, "LIVES: ", uiBlue);
		uiFont.drawString(400, 550, Integer.toString(playerLives), uiRed);
		
		uiFont.drawString(550, 550, "LEVEL: ", uiBlue);
		uiFont.drawString(650, 550, Integer.toString(currentLevel), uiRed);
		
		level.render(gc.getGraphics());
		
		player.render(gc, sbg, g);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		player.update(gc, sbg, delta);
		player.boundingbox.setLocation(playerX, playerY);
		
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)){ 
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? Your session will end and your score will be saved.", "Are you sure?", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION){
				saveScore();
				gc.exit();
			}
		}
		
		collisionDetection();
		
		float move = (float)(100*(delta/1000.0));
		if (input.isKeyDown(Input.KEY_RIGHT)){
			player.setAnimation(run_right);
			player.startAnimation();
			player.setPos(playerX += move, playerY);
		}else if (input.isKeyDown(Input.KEY_LEFT)){
			player.setAnimation(run_left);
			player.startAnimation();
			player.setPos(playerX -= move, playerY);
		}else if (input.isKeyDown(Input.KEY_UP) && canClimb){
			player.setAnimation(ladder_climb);
			player.startAnimation();
			player.setPos(playerX, playerY -= move);
		}else if (input.isKeyDown(Input.KEY_DOWN) && canClimb){
			player.setAnimation(ladder_climb);
			player.startAnimation();
			player.setPos(playerX, playerY += move);
		}else if (input.isKeyDown(Input.KEY_Z)){
			player.setAnimation(bar_climb);
			player.startAnimation();
			player.setPos(playerX -= move/2, playerY);
		}else if (input.isKeyDown(Input.KEY_X)){
			player.setAnimation(bar_climb);
			player.startAnimation();
			player.setPos(playerX += move/2, playerY);
		}else if (input.isKeyDown(Input.KEY_C)){
			player.setAnimation(falling);
			player.getCurrentAnimation().setLooping(false);
			player.startAnimation();
			//set looping false
			player.setPos(playerX, playerY += move);
		}else{
			if (player.equals(run_right)){
				player.setAnimation(idle_right);
				player.startAnimation();
			}else if (player.equals(run_left)){
				player.setAnimation(idle_left);
				player.startAnimation();
			}else if (player.equals(ladder_climb) || player.equals(bar_climb)){
				player.stopAnimation();
			}
		}
		
		//if (input.isKeyPressed(Input.KEY_HOME)){ System.out.println(player_animation.getCurrentFrame()); } //DEBUG
		if (input.isKeyPressed(Input.KEY_1)){ level.telesladderActive = true; }//DEBUG
		if (input.isKeyPressed(Input.KEY_2)){ level.telesladderActive = false; }//DEBUG
		System.out.println("canClimb = " + canClimb); //DEBUG
		
		
		if (playerScore != Account.getScore()){
			Account.setScore(playerScore);
		}
		
	}
	
	public int getID(){
		return 1;
	}
	
	private void saveScore(){
		Account.replaceSelected(Account.getUsername(), Account.getPassword(), this.playerScore);
	}
	
	private void collisionDetection(){
		/*
		for (int i = 0; i < level.ladder_bbox.size(); i++){
			if (player.boundingbox.intersects(level.ladder_bbox.get(i)) && !canClimb){
				canClimb = true;
			}
		}
		
		for (int i = 0; i < level.void_bbox.size(); i++){
			if (Math.round(player.boundingbox.getMaxY()) == Math.round(level.void_bbox.get(i).getMaxY()) && canClimb){
				//System.out.println("void maxY = " + level.void_bbox.get(i).getMaxY()); //DEBUG
				canClimb = false;
			}
		}
		*/

		for (int i = 0; i < level.ladder_bbox.size(); i++){
			if (player.boundingbox.intersects(level.ladder_bbox.get(i)) && !canClimb){
				canClimb = true;
			}
		}
		
		for (int i = 0; i < level.void_bbox.size(); i++){
			if (player.boundingbox.getCenterX() >= level.void_bbox.get(i).getMinX() && player.boundingbox.getCenterX() <= level.void_bbox.get(i).getMaxX()){
				if (player.boundingbox.getMaxY() <= level.void_bbox.get(i).getMaxY() && player.boundingbox.getMaxY() >= level.void_bbox.get(i).getMinY()){
					canClimb = false;
				}
			}
		}
	}

}
