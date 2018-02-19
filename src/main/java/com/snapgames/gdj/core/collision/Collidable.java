/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2018
 */
package com.snapgames.gdj.core.collision;

import com.snapgames.gdj.core.entity.AbstractGameObject;
import com.snapgames.gdj.core.state.AbstractGameState;

/**
 * 
 * @author Frédéric Delorme
 *
 */
public interface Collidable {
	/**
	 * Name of the object
	 * 
	 * @return
	 */
	String getName();

	/**
	 * collision processing method called when ago collide the current object.
	 * 
	 * @param state
	 *            game state from the collision happened.
	 * @param ago
	 *            the colliding object.
	 */
	void onCollide(AbstractGameState state, AbstractGameObject ago);
}
