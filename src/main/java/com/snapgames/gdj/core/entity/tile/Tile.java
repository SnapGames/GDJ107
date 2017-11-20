/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2017
 */
package com.snapgames.gdj.core.entity.tile;

import java.awt.image.BufferedImage;

/**
 * Tile element for
 * 
 * @author Frédéric Delorme
 *
 */
public class Tile {
	private static int idx = 0;
	private int id;
	private BufferedImage image;

	/**
	 * Create a Tile with the <code>img</code> image.
	 * 
	 * @param img
	 */
	public Tile(BufferedImage img) {
		id = idx++;
		image = img;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
