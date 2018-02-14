/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * GDJ105
 * 
 * @year 2017
 */
package com.snapgames.gdj.gdj107.entity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snapgames.gdj.core.collision.Collidable;
import com.snapgames.gdj.core.collision.Sizeable;
import com.snapgames.gdj.core.entity.AbstractGameObject;
import com.snapgames.gdj.core.state.AbstractGameState;

/**
 * The Player entity.
 * 
 * @author Frédéric Delorme
 *
 */
public class Player extends AbstractGameObject implements Collidable {

	private static final Logger logger = LoggerFactory.getLogger(Player.class);

	/**
	 * the default Constructor, doing nothing but creating an instance.
	 */
	public Player() {
		super();
	}

	/**
	 * Initialize the player object with a name and the default values.
	 * 
	 * @param name
	 */
	public Player(String name) {
		super(name, 0, 0, 0, 0);
		initDefaultValues();
	}

	/**
	 * 
	 */
	protected void initDefaultValues() {
		this.hSpeed = 0.05f;
		this.vSpeed = 0.05f;
		this.priority = 1;
		this.layer = 1;
		this.showDebuginfo = true;
		attributes.put("energy", new Integer(100));
		attributes.put("mana", new Integer(100));
		attributes.put("level", new Integer(1));
	}

	/**
	 * Perform collision processing for this object.
	 * 
	 * @param collisionList
	 */
	public void onCollide(AbstractGameState state, List<Sizeable> collisionList) {
		if (collisionList != null && !collisionList.isEmpty()) {
			for (Sizeable s : collisionList) {
				AbstractGameObject ago = (AbstractGameObject) s;
				if (ago.isVisible() && boundingBox.intersects(ago.boundingBox)) {
					int d = 0;
					switch (ago.getClass().getName()) {
					case "Eatable":
						d = (Integer) ago.attributes.get("power");
						// eat only if energy low.
						if (addValueToAttribute(this, "energy", d, 0, 100)) {
							state.removeObject(ago);
						}
						break;
					case "Enemy":
						addValueToAttribute(this, "energy", -1, 0, 100);
						break;
					default:
						break;
					}
				}
			}
		}
	}

	/**
	 * add <code>d</code> to attribute <code>name</code> from game object
	 * <code>ago</code> and verify <code>min</code> and <code>max</code> value. If
	 * value as been updated, return <code>true</code>, else <code>false</code>.
	 * 
	 * @param ago
	 * @param name
	 * @param d
	 * @param min
	 * @param max
	 * @return
	 */
	private boolean addValueToAttribute(AbstractGameObject ago, String name, int d, int min, int max) {
		if (ago != null && ago.attributes != null && ago.attributes.containsKey(name)) {
			int value = (Integer) ago.attributes.get(name);
			if (value + d < max && value + d > min) {
				value += d;
				attributes.put(name, value);
				return true;
			}
		} else {
			logger.error("GameObject {} does not have property named {}", ago.name, name);
		}
		return false;
	}

}
