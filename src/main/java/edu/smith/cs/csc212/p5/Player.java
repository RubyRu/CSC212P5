package edu.smith.cs.csc212.p5;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;

public class Player extends GFX{
	/**
	 * Where am I? x-tile in the grid.
	 */
	private int x;
	/**
	 * Where am I? y-tile in the grid.
	 */
	private int y;
	/**
	 * What world do I belong to?
	 */
	protected World world;
	
	BufferedImage player;
	
	public Player() throws IOException {
		this.world = world;
		//super(world);
		player = ImageIO.read(new File("src/main/images/Player.png"));
	}

	@Override
	public void draw(Graphics2D g) {
		int centerX = (this.getWidth() - player.getWidth()) / 2;
		g.drawImage(player, centerX, 0, null);
	}
	
	/**
	 * Move this object to a given position (ignoring rules).
	 * 
	 * @param x the x-coordinate.
	 * @param y the y-coordinate.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Move this object to a given position (ignoring rules).
	 * 
	 * @param pt The (x,y) pair as an IntPoint.
	 */
	public void setPosition(IntPoint pt) {
		this.setPosition(pt.x, pt.y);
	}
	
	/**
	 * Move this object up
	 */
	public void moveUp() {
		this.y -= 1;
	}
	
	/**
	 * Move this object down
	 */
	public void moveDown() {
		this.y += 1;
	}
	
	/**
	 * Move this object left if possible.
	 * 
	 * @return true if it moved!
	 */
	public void moveLeft() {
		this.x -= 1;
	}

	/**
	 * Move this object right if possible.
	 * 
	 * @return true if it moved!
	 */
	public void moveRight() {
		this.x += 1;
	}
	
	/**
	 * Part of my position!
	 * 
	 * @return the x-coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Part of my position!
	 * 
	 * @return the y-coordinate.
	 */
	public int getY() {
		return y;
	}

}
