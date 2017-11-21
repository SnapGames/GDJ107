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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger logger = LoggerFactory.getLogger(TileLayer.class);
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
	Map<String, TileSet> tileSets = new HashMap<>();

	private int tileWidth = 16, tileHeight = 16;
	private int x;
	private int y;
	private Map<String, String> mapTileMap = new HashMap<>();
	private Map<String, String> mapTileSet = new HashMap<>();

	public TileLayer(int priority) {
		this.priority = 0;
		this.root = null;
		this.x = 0;
		this.y = 0;
	}

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

	public TileLayer addTileset(String name, BufferedImage image) {
		this.tileSets.put(name,new TileSet(name).setImage(image).generateWithTileSize(tileWidth, tileHeight));
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
				if (getTileIndex(ix, iy) != 0) {
					Tile tile = getTile(ix, iy);
					g.drawImage(tile.getImage(), (ix - 1) * tileWidth, (iy - 1) * tileHeight, null);
				}
			}
		}
	}

	/**
	 * Get the tile index at coordinate (tx,ty).
	 * 
	 * @param tx
	 * @param ty
	 * @return
	 */
	public int getTileIndex(int tx, int ty) {
		return tiles[tx * width + ty];
	}

	/**
	 * Get a tile for coordinate (tx,ty). return null if no tile at this coordinate.
	 * 
	 * @param tx
	 *            the x coordinate where to search for.
	 * @param ty
	 *            the y coordinate where to search for.
	 * @return the Tile at tx,ty or null if no Tile.
	 */
	public Tile getTile(int tx, int ty) {
		if (getTileIndex(tx, ty) == 0) {
			return null;
		}
		return tileSets.get("A").get(getTileIndex(tx, ty));
	}

	/**
	 * load the map for this layer from the is InputStream.
	 * 
	 * @param is
	 * @return
	 */
	public TileLayer loadFrom(InputStream is) {
		Reader isr = null;
		BufferedReader br = null;

		try {
			boolean exit = false;

			isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader(isr);

			String l = null;
			StringBuffer sb = new StringBuffer();

			while ((l = br.readLine()) != null && !exit) {
				switch (l.toUpperCase()) {
				case "@TILESET":
					readTileSet(br);
					break;
				case "@TILE":
					initTileList(br);
					break;
				case "@MAP":
					readMap(br);
					break;
				case "@END":
					exit = true;
				}
			}

			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
		} catch (IOException e) {
			logger.error("unalbe to read the input stream : {}", e.getMessage());
		}

		return null;
	}

	/**
	 * Read and initialize the {@link TileSet} for this map.
	 * 
	 * @throws IOException
	 */
	private void readTileSet(BufferedReader br) throws IOException {
		String l = null;
		while ((l = br.readLine()) != null) {
			if (l.startsWith("@")) {
				break;
			}
			String[] keyval = l.split(":");
			TileSet ts = new TileSet(keyval[0],ImageIO.read(this.getClass().getResourceAsStream(keyval[1])));
			tileSets.put(keyval[0], ts);
		}
	}
	private void initTileList(BufferedReader br) {

	}

	private void readMap(BufferedReader br) {

	}


	/**
	 * return the width of this {@link TileLayer}.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * return the height of this {@link TileLayer}.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the view position for this layer.
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x * (root.width / this.width);
		this.y = y * (root.height / this.height);
	}

	/**
	 * set root {@link TileMap}.
	 * 
	 * @param tm
	 */
	public void setTileMap(TileMap tm) {
		this.root = tm;
	}
}
