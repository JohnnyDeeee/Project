package org.loderunner.screens;

import javax.swing.JOptionPane;

import org.loderunner.table.Account;
import org.loderunner.table.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	private UnicodeFont uiFont;
	private Color uiBlue = new Color(72,104,247);
	private Color uiRed = new Color(176,32,32);
	
	private int playerScore;
	private int playerLives;
	private int currentLevel;
	
	private Level level;
	
	private float playerX;
	private float playerY;
	
	private SpriteSheet run_right_sheet, run_left_sheet, ladder_climb_sheet, bar_climb_sheet, falling_sheet, idle_right_sheet, idle_left_sheet;
	private Animation player_animation, run_right_animation, run_left_animation, ladder_climb_animation, bar_climb_animation, falling_animation, idle_right_animation, idle_left_animation;
	
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
		
		player_animation = run_right_animation;
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
		
		player_animation.draw(playerX, playerY);
		//player_animation.stop();
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{		
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)){ 
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? Your session will end and your score will be saved.", "Are you sure?", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION){
				saveScore();
				gc.exit();
			}
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)){
			player_animation = run_right_animation;
			player_animation.start();
			playerX += 0.1f;
		}else if (input.isKeyDown(Input.KEY_LEFT)){
			player_animation = run_left_animation;
			player_animation.start();
			playerX -= 0.1f;
		}else if (input.isKeyDown(Input.KEY_UP)){
			player_animation = ladder_climb_animation;
			player_animation.start();
			playerY -= 0.1f;
		}else if (input.isKeyDown(Input.KEY_DOWN)){
			player_animation = ladder_climb_animation;
			player_animation.start();
			playerY += 0.1f;
		}else if (input.isKeyDown(Input.KEY_Z)){
			player_animation = bar_climb_animation;
			player_animation.start();
			playerX -= 0.05f;
		}else if (input.isKeyDown(Input.KEY_X)){
			player_animation = bar_climb_animation;
			player_animation.start();
			playerX += 0.05f;
		}else if (input.isKeyDown(Input.KEY_C)){
			player_animation = falling_animation;
			player_animation.setLooping(false);
			player_animation.start();
			playerY += 0.05f;
		}else{
			if (player_animation.equals(run_left_animation)){
				player_animation = idle_left_animation;
				player_animation.start();
			}else if (player_animation.equals(run_right_animation)){
				player_animation = idle_right_animation;
				player_animation.start();
			}
		}
		
		if (input.isKeyPressed(Input.KEY_HOME)){ System.out.println(player_animation.getCurrentFrame()); } //DEBUG
		if (input.isKeyPressed(Input.KEY_1)){ level.telesladderActive = true; }//DEBUG
		if (input.isKeyPressed(Input.KEY_2)){ level.telesladderActive = false; }//DEBUG
		
		if (playerScore != Account.getScore()){
			Account.setScore(playerScore);
		}
		
		player_animation.update(delta);
	}
	
	public int getID(){
		return 1;
	}
	
	private void saveScore(){
		Account.replaceSelected(Account.getUsername(), Account.getPassword(), this.playerScore);
	}

}
