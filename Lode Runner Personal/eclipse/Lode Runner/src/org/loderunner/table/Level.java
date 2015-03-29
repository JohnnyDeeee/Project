package org.loderunner.table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Level {

	//Fields
	private File file;
	private Image block, ladder, bar, telesladder, telesladder_begin_notactive, telesladder_begin_active;
	public ArrayList<Shape> void_bbox = new ArrayList<Shape>();
	public ArrayList<Shape> block_bbox = new ArrayList<Shape>();
	public ArrayList<Shape> ladder_bbox = new ArrayList<Shape>();
	public ArrayList<Shape> bar_bbox = new ArrayList<Shape>();
	public ArrayList<Shape> telesladderbegin_bbox = new ArrayList<Shape>();
	public ArrayList<Shape> telesladder_bbox = new ArrayList<Shape>();
	public boolean telesladderActive = false;
	//Properties

	
	//Constructor
	public Level(String filename, GameContainer gc) throws SlickException{
		String dir = System.getProperty("user.dir");
		this.file = new File(dir + "\\bin\\org\\loderunner\\" + filename);
		init(gc);
	}
	
	//Methods
	private void init(GameContainer gc) throws SlickException {
		 block = new Image("res/objects/block.png");
		 ladder = new Image("res/objects/ladder.png");
		 bar = new Image("res/objects/bar.png");
		 telesladder = new Image("res/objects/telesladder.png");
		 telesladder_begin_notactive = new Image("res/objects/telesladder_begin_notactive.png");
		 telesladder_begin_active = new Image("res/objects/telesladder_begin_active.png");
	}
	
	public void render(Graphics g) throws SlickException{
		try {
			if (this.file.exists()) {
				FileReader fr = new FileReader(this.file);
				BufferedReader br = new BufferedReader(fr);
				Scanner sc = new Scanner(file);
				int blockX = 80;
				int blockY = 100;
				int maxLength = 0;
				int curLength = 0;
				
				while (sc.hasNext()){
					String tmpStr = sc.nextLine();
		            if (!tmpStr.equalsIgnoreCase("")) {
		                String replaceAll = tmpStr.replaceAll("\\s+", "");
		                maxLength += replaceAll.length();
		            }
				}

				while (curLength < maxLength) {
					String key = Character.toString((char)br.read());
					//System.out.println("Key: " + key); //DEBUG
					
					if (key.equals(";")){
						blockY += block.getHeight();
						blockX = 80;
						curLength++;
					}else if (key.equals("x")){
						g.drawImage(block, blockX, blockY, Color.white);
						block_bbox.add(new Rectangle(blockX, blockY, block.getWidth(), block.getHeight()));
						blockX += block.getWidth();
						curLength++;
					}else if (key.equals("-")){
						void_bbox.add(new Rectangle(blockX, blockY, block.getWidth(), block.getHeight()));
						blockX += block.getWidth();
						curLength++;
					}else if (key.equals("l")){
						g.drawImage(ladder, blockX, blockY, Color.white);
						ladder_bbox.add(new Rectangle(blockX, blockY, ladder.getWidth(), ladder.getHeight()));
						blockX += ladder.getWidth();
						curLength++;
					}else if (key.equals("*")){
						g.drawImage(bar, blockX, blockY, Color.white);
						bar_bbox.add(new Rectangle(blockX, blockY, bar.getWidth(), bar.getHeight()));
						blockX += bar.getWidth();
						curLength++;
					}else if (key.equals("T")){
						if (!telesladderActive){
							g.drawImage(telesladder_begin_notactive, blockX, blockY, Color.white);
							telesladderbegin_bbox.add(new Rectangle(blockX, blockY, telesladder_begin_notactive.getWidth(), telesladder_begin_notactive.getHeight()));
							blockX += telesladder_begin_notactive.getWidth();
						}else{
							g.drawImage(telesladder_begin_active, blockX, blockY, Color.white);
							telesladderbegin_bbox.add(new Rectangle(blockX, blockY, telesladder_begin_active.getWidth(), telesladder_begin_active.getHeight()));
							blockX += telesladder_begin_active.getWidth();
						}
						curLength++;
					}else if (key.equals("t")){
						if (telesladderActive){
							g.drawImage(telesladder, blockX, blockY, Color.white);
							telesladder_bbox.add(new Rectangle(blockX, blockY, telesladder.getWidth(), telesladder.getHeight()));
							blockX += telesladder.getWidth();
						}else{
							//dont draw just update next pos
							blockX += telesladder.getWidth();
						}
						curLength++;
					}
					
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
