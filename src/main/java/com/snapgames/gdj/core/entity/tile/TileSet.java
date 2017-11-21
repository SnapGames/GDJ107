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
 * This Tiles set contains tiles extracted from an image.
 * 
 * @author Frédéric Delorme
 *
 */
public class TileSet {
	private static int idx = 0;
	private String name = "tileset_" + String.format("%02d", (idx++));
	private int nbTileX = 0, nbTileY = 0;
	/**
	 * tiles for this {@link TileSet}.
	 */
	private Tile[] tiles = null;
	private BufferedImage image;

	/**
	 * create a new TileSet from image with tile sized (width,height).
	 * 
	 * @param image
	 *            image to extract Tiles from.
	 * @param width
	 *            width of a tile
	 * @param height
	 *            height of a tile.
	 */
	public TileSet(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	public TileSet setImage(BufferedImage image) {
		this.image = image;
		return this;
	}

	/**
	 * 
	 * @param name
	 * @param image
	 */
	public TileSet(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
	}

	/**
	 * populate the TileSet with Tile with (width,height).
	 * 
	 * @param width
	 *            width of the tile to generate.
	 * @param height
	 *            height of the tile to generate.
	 * @return
	 */
	public TileSet generateWithTileSize(int width, int height) {
		nbTileX = image.getWidth() / width;
		nbTileY = image.getHeight() / height;
		tiles = new Tile[nbTileX * nbTileY];

		for (int y = 0; y < nbTileY; y++) {
			for (int x = 0; x < nbTileX; x++) {
				Tile t = new Tile(image.getSubimage(x * width, y * height, width, height));
			}
		}

		return this;
	}

	/**
	 * Retrieve the tile identified by <code>idx</code> from this TileSet.
	 * 
	 * @param idx
	 *            index of the tile.
	 * @return
	 */
	public Tile get(int idx) {
		assert (tiles != null);
		assert (idx < tiles.length && idx >= 0);
		return tiles[idx];
	}

}
