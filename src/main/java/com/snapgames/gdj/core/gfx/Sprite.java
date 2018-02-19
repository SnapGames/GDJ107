/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2018
 */
package com.snapgames.gdj.core.gfx;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.snapgames.gdj.core.entity.Actions;
import com.snapgames.gdj.core.math.Vector2D;

/**
 * Sprite to display animated images according to Animation list object.
 * 
 * @author Frédéric Delorme
 *
 */
public class Sprite {

	/**
	 * internal Sprite name.
	 */
	public String name;

	/**
	 * internal list of animations for that sprite.
	 */
	List<Animation> sprites = new ArrayList<>();
	/**
	 * default action.
	 */
	Actions animationIndex = Actions.IDLE;
	/**
	 * animation phase counter.
	 */
	int phase = 0;
	/**
	 * animation time.
	 */
	float animationTime = 0;

	/**
	 * Compute phase of animation according to elapsed time and phase duration. dt
	 * time in milliseconds.
	 * 
	 * @param dt
	 */
	public void update(float dt) {
		animationTime += dt;
		Animation a = sprites.get(animationIndex.ordinal());
		if (animationTime > a.getDuration(phase)) {
			phase += 1;
			if (phase > a.phaseMaxNumber()) {
				phase = 0;
			}
		}
	}

	/**
	 * Render active sprite image according to animation phase.
	 * 
	 * @param g
	 * @param a
	 * @param position
	 */
	public void render(Graphics2D g, Vector2D position) {
		g.drawImage(sprites.get(animationIndex.ordinal()).get(phase), (int) position.x, (int) position.y, null);
	}

	public void setAction(Actions a) {
		animationIndex = a;
		phase = 0;
		animationTime = 0;
	}
}
