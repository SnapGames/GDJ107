/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2017
 */
package com.snapgames.gdj.core.entity.particles.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.snapgames.gdj.core.entity.particles.AbstractParticle;
import com.snapgames.gdj.core.entity.particles.ParticleSystem;
import com.snapgames.gdj.core.entity.particles.behaviors.RainBehavior;

/**
 * A Drop Particle.
 * 
 * @author Frédéric Delorme
 *
 */
public class Drop extends AbstractParticle {
	RainBehavior rb;

	/**
	 * create a Drop at position x.
	 * 
	 * @param x
	 */
	public Drop(ParticleSystem ps, float x, float y) {
		super();
		rb = (RainBehavior) ps.getBehavior();
		this.x = x;
		this.y = y;
		this.color = Color.WHITE;
	}

	@Override
	public void create(ParticleSystem particleSystem) {
		super.create(particleSystem);
	}

	@Override
	public void update(ParticleSystem ps, float time) {
		super.update(ps, time);
	}

	@Override
	public void draw(ParticleSystem ps, Graphics2D g) {
		g.setColor(color);
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, rb.dropDiameter, rb.dropDiameter);
		g.fill(circle);
	}
}
