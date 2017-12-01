/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2017
 */
package com.snapgames.gdj.core.entity.particles;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.snapgames.gdj.core.Game;
import com.snapgames.gdj.core.entity.AbstractGameObject;

/**
 * The particle System is the main class to manage some particles in a
 * GameObject.
 * 
 * @author Frédéric Delorme
 *
 */
public abstract class ParticleSystem extends AbstractGameObject {

	private static List<Particle> particles = Arrays.asList(new Particle[200]);

	private List<Particle> systemParticles = new ArrayList<>();

	Class<? extends Particle> particleClass;

	/**
	 * Default constructor to create a new Particle System.
	 * 
	 * @param name
	 */
	public ParticleSystem(String name) {
		super(name);

	}

	public void initialize() throws InstantiationException, IllegalAccessException {
		for (int i = 0; i < systemParticles.size(); i++) {

			Particle part = particleClass.newInstance();
			part.initialize(this);
			systemParticles.set(i, part);
		}
	}
	
	/**
	 * resize the ParticleSystem number of particles.
	 * 
	 * @param size
	 *            number of particles for this ParticleSystem.
	 * @return
	 */
	ParticleSystem setNbParticles(int size) {
		systemParticles = Arrays.asList(new Particle[size]);
		return this;
	}

	/**
	 * initialize attributes for this ParticleSystem.
	 * 
	 * @param attributes
	 * @return
	 */
	ParticleSystem setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
		return this;
	}

	/**
	 * Set the particle implementation class to be managed by this particle system.
	 * 
	 * @param particleClass
	 * @return
	 */
	ParticleSystem setParticleClass(Class<? extends Particle> particleClass) {
		this.particleClass = particleClass;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.snapgames.gdj.core.entity.AbstractGameObject#update(com.snapgames.gdj.
	 * core.Game, long)
	 */
	@Override
	public void update(Game game, long dt) {
		for (Particle particle : systemParticles) {
			particle.update(this, dt);
		}
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
		for (Particle particle : systemParticles) {
			particle.draw(this, g);
		}

	}

}
