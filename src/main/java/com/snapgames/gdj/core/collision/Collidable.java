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

import java.util.List;

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
	 * collision list processing method.
	 * 
	 * @param collisionList
	 */
	void onCollide(AbstractGameState state, List<Sizeable> collisionList);
}
