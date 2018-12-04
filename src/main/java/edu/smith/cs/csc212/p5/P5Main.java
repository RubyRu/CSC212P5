package edu.smith.cs.csc212.p5;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;
import me.jjfoley.gfx.TextBox;

/**
 * This class is like class PlayFish in P2
 */

public class P5Main extends GFX {
	public static int VISUAL_GRID_SIZE= 400;
	public static int LOGICAL_GRID_SIZE = 15;
	public static int DIALOGUE_WINDOW_SIZE = 130;
	BufferedImage player;

	/**
	 * this is where the game logic lives 
	 */
	Game game;
	/**
	 * This TextBox wraps up making fonts and centering text.
	 */
	TextBox gameState = new TextBox("");
	/**
	 * This is the dialogue window.
	 */
	Rectangle2D dialogueWindow;
	/**
	 * set color for the grid frame.
	 */
	public static Color GRID_COLOR = new Color(255, 170, 245);
	
	
	public P5Main() throws IOException {
		super(VISUAL_GRID_SIZE, VISUAL_GRID_SIZE + DIALOGUE_WINDOW_SIZE);
		game = new Game(LOGICAL_GRID_SIZE, LOGICAL_GRID_SIZE);		
		dialogueWindow = new Rectangle2D.Double(0,0,getWidth(),DIALOGUE_WINDOW_SIZE);
		player = ImageIO.read(new File("src/main/images/Player.png"));
	}
	
	/**
	 * how big is a tile?
	 * @return the width of a tile 
	 */
	public int getTileW() {
		return VISUAL_GRID_SIZE/game.world.getWidth();
	}
	public int getTileH() {
		return VISUAL_GRID_SIZE/game.world.getHeight();
	}
	
	
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(), getHeight());
		
		World world = game.world;
		
		// Use the tile-sizes.  
		int tw = getTileW();
		int th = getTileH(); 

		// set color of all grids.
		g.setColor(new Color(5,5,5));
		g.fillRect(0, 0, tw * world.getWidth(), th * world.getHeight());
		// Draw a grid to better picture how the game works.
		g.setColor(GRID_COLOR);
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				g.drawRect(x * tw, y * th, tw, th);
			}
		}
		
		// initialize by drawing the player in the center
		int centerX = (this.getWidth() - player.getWidth()) / 2;
		g.drawImage(player, centerX, 0, null);
	}	
	
	/**
	 * Convert Mouse coordinates to Grid coordinates.
	 * @param mouse maybe a Mouse location (or null).
	 * @return null or the grid coordinates of the Mouse.
	 */
	public IntPoint mouseToGame(IntPoint mouse) {
		if (mouse == null) return null;
		int x = mouse.x;
		int y = mouse.y - DIALOGUE_WINDOW_SIZE;
		if (x > 0 && x <= VISUAL_GRID_SIZE &&
				y > 0 && y <= VISUAL_GRID_SIZE) {
			int tx = x / getTileW();
			int ty = y / getTileH();
			return new IntPoint(tx, ty);
		}
		return null;
	}

	/**
	 * We separate our "PlayFish" game logic update here.
	 * @param secondsSinceLastUpdate - my GFX code can tell us how long it is between each update, but we don't actually care here.
	 */
	@Override
	public void update(double secondsSinceLastUpdate) {
		// Read the state of the keyboard:
		boolean up = this.processKey(KeyEvent.VK_W) || this.processKey(KeyEvent.VK_UP);
		boolean down = this.processKey(KeyEvent.VK_S) || this.processKey(KeyEvent.VK_DOWN);
		boolean left = this.processKey(KeyEvent.VK_A) || this.processKey(KeyEvent.VK_LEFT);
		boolean right = this.processKey(KeyEvent.VK_D) || this.processKey(KeyEvent.VK_RIGHT);
		boolean skip = this.processKey(KeyEvent.VK_SPACE);
        
		/**
		 * We separate our "PlayFish" game logic update here.
		 * @param secondsSinceLastUpdate - my GFX code can tell us how long it is between each update, but we don't actually care here.
		 
		// Move the player if we can:
		boolean moved = false;
		if (up) {
			moved = this.game.player.moveUp();
		} else if (down) {
			moved = this.game.player.moveDown();
		} else if (left) {
			moved = this.game.player.moveLeft();
		} else if (right) {
		    moved = this.game.player.moveRight();
		}

		
		IntPoint click = mouseToGame(this.processClick());
		
		// Only advance the game if the player presses something!
		if (skip || moved || click != null) {
			if (click != null) {
				this.game.click(click.x, click.y);
			}
			// Update game logic!
			this.game.step();
			// Update message at the top!
		}
		*/
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Game Started!");
		GFX app = new P5Main();
		app.start();
	}
  
}
