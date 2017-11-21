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
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.snapgames.gdj.core.Game;
import com.snapgames.gdj.core.entity.AbstractGameObject;
import com.snapgames.gdj.gdj107.entity.Player;

/**
 * A Tilemap is a graphical object drawing some tiles (basic graphics element on
 * the screen according to its view position.
 * 
 * @author Frédéric Delorme
 *
 */
public class TileMap extends AbstractGameObject {

	List<TileLayer> layers = new ArrayList<>();
	private Rectangle view = new Rectangle(0, 0, 0, 0);
	private int tileWidth = 16, tileHeight = 16;

	public TileMap() {
		super();
	}

	/**
	 * create a new {@link TileMap} named <code>name</code>.
	 * 
	 * @param name
	 *            the name for this {@link TileMap}.
	 */
	public TileMap(String name) {
		super(name, 0, 0, 0, 0);
	}

	/**
	 * Set the size of the {@link TileMap}.
	 * 
	 * @param width
	 *            the width of this {@link TileMap}.
	 * @param height
	 *            the height of this {@link TileMap}.
	 */
	public TileMap setSize(int width, int height) {
		this.view.width = width;
		this.view.height = height;
		return this;
	}

	/**
	 * Add a layer to the {@link TileMap}.
	 * 
	 * @param layer
	 *            the layer to be added to the TileMap.
	 * @return
	 */
	public TileMap addLayer(TileLayer layer) {
		layers.add(layer);
		if(this.width<layer.getWidth() 
				|| this.height<layer.getHeight()) {
			this.width=layer.getWidth();
			this.height = layer.getHeight();
		}
		/*
		 * Sort all the layers to be ready at draw time.
		 */
		layers.sort(new Comparator<TileLayer>() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(TileLayer o1, TileLayer o2) {
				return o1.getPriority().compareTo(o2.getPriority());
			}
		});
		return this;
	}

	/**
	 * Set the tile size for this {@link TileMap}.
	 * 
	 * @param tileWidth
	 *            width of a tile in this {@link TileMap}.
	 * @param tileHeight
	 *            height of a tile in this {@link TileMap}.
	 * @return
	 */
	public TileMap setTileSize(int tileWidth, int tileHeight) {
		this.tileWidth = width;
		this.tileHeight = height;
		return this;
	}

	/**
	 * set the view position for this {@link TileMap}.
	 * 
	 * @param x
	 *            X position of the view in the {@link TileMap}.
	 * @param y
	 *            Y position of the view in the {@link TileMap}.
	 * @return
	 */
	public TileMap setViewPosition(int x, int y) {
		this.view.x = x;
		this.view.y = y;
		for(TileLayer layer:layers) {
			layer.setPosition(x,y);
		}
		return this;
	}

	/**
	 * return the view object for this {@link TileMap}.
	 * 
	 * @return
	 */
	public Rectangle getView() {
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.snapgames.gdj.core.entity.AbstractGameObject#draw(com.snapgames.gdj.core.
	 * Game, java.awt.Graphics2D)
	 */
	@Override
	public void draw(Game game, Graphics2D g) {
		super.draw(game, g);
		for (TileLayer tl : layers) {
			tl.draw(game, g);
		}
	}

	/**
	 * detect if an object collide with the tilemap.
	 * 
	 * @param object
	 * @return
	 */
	public boolean collide(AbstractGameObject object) {
		int tx = (int)object.x+(object.width/2);
		int ty = (int)object.y+(object.height/2);
		Tile t = getTile(1,tx,ty);
		return false;
	}


	/**
	 * 
	 * @param tileIndex
	 * @param tx
	 * @param ty
	 * @return
	 */
	private Tile getTile(int tileIndex, int tx, int ty) {
		return layers.get(ty).getTile(tx,ty);
	}

}
