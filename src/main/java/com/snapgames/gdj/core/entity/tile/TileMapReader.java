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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snapgames.gdj.core.entity.EntityFactory;
import com.snapgames.gdj.core.entity.GameObject;

/**
 * 
 * @author Frédéric Delorme
 *
 */
public class TileMapReader {

	private static final Logger logger = LoggerFactory.getLogger(TileMapReader.class);
	private static TileMapReader instance;
	private TileMap tilemap;
	private Map<String, TileSet> tileSets = new HashMap<>();
	private Map<String, Tile> mappingTileToTileSet = new HashMap<>();
	private Map<String, GameObject> objects = new ConcurrentHashMap<>();

	/**
	 * load the map for this layer from the is InputStream.
	 * 
	 * @param is
	 * @return
	 */
	public TileMap loadTileMapFrom(InputStream is) {
		tilemap = null;
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
				case "@TILEMAP":
					tilemap = readTileMap(br);
					break;
				case "@TILESET":
					readTileSet(br);
					break;
				case "@TILEMAPPING":
					initTileList(br);
					break;
				case "@TILELAYER":
					readLayerMap(br);
					break;
				case "@LAYERIMAGE":
					readLayerImage(br);
					break;
				case "@END":
					exit = true;
					break;
				case "@OBJECTS":
					// TODO implement the reader for Objects.
					addObjects(br);
					break;
				}
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
		} catch (IOException e) {
			logger.error("unable to read the input stream : {}", e.getMessage());
		}

		return tilemap;
	}

	private void addObjects(BufferedReader br) {
		// TODO implement call to EntityFactory to create Object in map.
		EntityFactory.createEntity("", "");
		objects.put("objectname", null);
	}

	private void readLayerImage(BufferedReader br) {
		// TODO implement layer reading

	}

	private TileMap readTileMap(BufferedReader br) throws IOException {
		TileMap map = null;
		String l = br.readLine();
		String[] keyval = l.split(":");
		if (keyval[0].equals("name")) {
			map = new TileMap(keyval[1]);
		}
		return map;
	}

	/**
	 * Read and initialize the {@link TileSet} for this map.
	 * 
	 * @throws IOException
	 */
	private void readTileSet(BufferedReader br) throws IOException {
		String l = null;
		while ((l = br.readLine()) != null) {
			if (l.startsWith("@END")) {
				break;
			}
			String[] keyval = l.split(":");
			TileSet ts = new TileSet(keyval[0], ImageIO.read(this.getClass().getResourceAsStream(keyval[1])));
			tileSets.put(keyval[0], ts);
		}
	}

	private void initTileList(BufferedReader br) throws IOException {
		String l = null;
		while ((l = br.readLine()) != null) {
			if (l.startsWith("@END")) {
				break;
			}
			String[] keyval = l.split(":");
			String[] values = { keyval[1].substring(1, 1), keyval[1].substring(2) };
			mappingTileToTileSet.put(keyval[0], tileSets.get(values[0]).get(Integer.parseInt(values[1])));
		}

	}

	private void readLayerMap(BufferedReader br) throws NumberFormatException, IOException {
		TileLayer layer = new TileLayer(0);
		String l = null;
		while ((l = br.readLine()) != null) {
			if (l.startsWith("@END")) {
				break;
			}
			String[] keyval = l.split(":");
			switch (keyval[0]) {
			case "name":
				// TODO implement the reading of the name for a TileLayer
				break;
			case "p":
				// TODO implement the reading for the priority of a TileLayer
				break;
			case "map":
				// TODO implement the reading of a map for TileLayer.
				break;
			}
		}
	}

	private TileMap loadMapFrom(InputStream resourceAsStream) {
		TileMap tm = loadTileMapFrom(resourceAsStream);
		return tm;
	}

	public static TileMap loadFrom(InputStream resourceAsStream) {
		return getInstance().loadMapFrom(resourceAsStream);
	}

	private static TileMapReader getInstance() {
		if (instance == null) {
			instance = new TileMapReader();
		}
		return instance;
	}

}
