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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Frédéric Delorme
 *
 */
public class Animation {
	// animation based image.
	BufferedImage image;
	// list of animation phase (cropping of images)
	List<Rectangle> phases = new ArrayList<>();
	// list of duration of each phase.
	List<Integer> durations = new ArrayList<>();

	/**
	 * create an animation with the <code>img</code> image.
	 * 
	 * @param img
	 */
	public Animation(BufferedImage img) {
		this.image = img;
	}

	/**
	 * Add a phase to the image.
	 * 
	 * @param r
	 */
	public void addPhase(Rectangle r, int duration) {
		phases.add(r);
		durations.add(duration);
	}

	/**
	 * retrieve an image of the phase.
	 * 
	 * @param phase
	 * @return
	 */
	public BufferedImage get(int phase) {
		Rectangle r = phases.get(phase);
		return image.getSubimage(r.x, r.y, r.width, r.height);
	}

	/**
	 * retrieve the corresponding phase duration.
	 * 
	 * @param phase
	 * @return
	 */
	public int getDuration(int phase) {
		return durations.get(phase).intValue();
	}

	/**
	 * retrieve the max number of image for this animation.
	 * 
	 * @return
	 */
	public int phaseMaxNumber() {
		return phases.size();
	}
}
