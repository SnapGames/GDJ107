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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.snapgames.gdj.core.Game;

/**
 * The tile layer class is a layer of a full TileMap. it contains a table of
 * Tile drawing some view for the game. Its is a part of a full Level. Each Tile
 * layer has its own width and height, and its own tiles set. Display is
 * computed according to the TileMap view parameter.
 * 
 * @author Frédéric Delorme
 *
 */
public class TileLayer {
	/**
	 * internal index for this TileSet.
	 */
	private static int idx = 0;
	/**
	 * Parent tile map
	 */
	private TileMap root = null;

	/**
	 * rendering priority
	 */
	private int priority = 0;

	/**
	 * size of the tilelayer.
	 */
	private int width = 0, height = 0;

	/**
	 * array of tiles
	 */
	private int[] tiles = null;

	/**
	 * Set of Tiles used to draw the tilelayer.
	 */
	TileSet[] tileSets;

	private int tileWidth = 16, tileHeight = 16;

	public TileLayer(TileMap tm, int priority) {
		this.priority = 0;
		this.root = tm;
	}

	public TileLayer setSize(int w, int h) {
		this.width = w;
		this.height = h;
		this.tiles = new int[w * h];
		return this;
	}

	public TileLayer setTileSize(int tw, int th) {
		this.tileWidth = tw;
		this.tileHeight = th;
		return this;
	}

	public TileLayer setPriority(int p) {
		this.priority = p;
		return this;
	}

	public TileLayer setHeight(int h) {
		this.height = h;
		setSize(width, h);
		return this;
	}

	public TileLayer setWidth(int w) {
		this.width = w;
		setSize(w, height);
		return this;
	}

	public void setTile(int tileSetIdx, int tileIdx, int x, int y) {
		tiles[(x - 1) * width + (y - 1)] = tileSetIdx * 10000 + tileIdx;
	}

	public TileLayer setTileset(String name, BufferedImage image) {
		this.tileSets[idx++] = new TileSet(name).setImage(image).generateWithTileSize(tileWidth, tileHeight);
		return this;
	}

	/**
	 * @return
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Draw all the tiles for this layer.
	 * 
	 * @param game
	 * @param g
	 */
	public void draw(Game game, Graphics2D g) {

		int vx = root.getView().x * (width / root.getView().width);
		int vy = root.getView().y * (height * root.getView().height);

		int vw = root.getView().width;
		int vh = root.getView().height;

		for (int iy = vy; iy < vy + vh; iy++) {
			for (int ix = vx; ix < vx + vw; ix++) {
				if (tiles[ix * width + iy]!=0) {
					Tile tile = tileSets[0].get(tiles[ix * width + iy]);
					g.drawImage(tile.getImage(), (ix - 1) * tileWidth, (iy - 1) * tileHeight, null);
				}
			}
		}
	}

}
