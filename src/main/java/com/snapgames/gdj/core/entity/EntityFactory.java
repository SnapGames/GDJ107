/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2017
 */
package com.snapgames.gdj.core.entity;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Frédéric Delorme
 *
 */
public class EntityFactory {
	/**
	 * the internal logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(EntityFactory.class);
	/**
	 * The internal instance.
	 */
	private static EntityFactory instance;

	/**
	 * The list of class that are managed by the factory.
	 */
	Map<String, Class<?>> entities = new HashMap<>();

	/**
	 * Add a type of entity to be created by the factory.
	 * 
	 * @param name
	 * @param clazz
	 */
	public void addEntityType(String name, Class<? extends GameObject> clazz) {
		entities.put(name, clazz);
	}

	/**
	 * request to create an Entity of type <code>entityType</code> with this
	 * <code>name</code>.
	 * 
	 * @param entityType
	 *            the declared type of entity to create.
	 * @param name
	 *            the name of the instance.
	 * @return
	 */
	public GameObject createEntityType(String entityType, String name) {
		GameObject go = null;
		if (entities.containsKey(entityType)) {
			Class<?> c = entities.get(entityType);
			try {
				go = (GameObject) c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error("unable to create entity {} for type {}", name, entityType);
			}
			go.setName(name);
		}
		return go;
	}

	/**
	 * static call to createEntity.
	 * 
	 * @param entityType
	 * @param name
	 * @see #createEntityType(String, String)
	 * @return
	 */
	public static GameObject createEntity(String entityType, String name) {
		return getInstance().createEntityType(entityType, name);
	}

	/**
	 * static call to addEntityType.
	 * 
	 * @param entityType
	 * @param entityClass
	 * @see #addEntityType(String, Class)
	 */
	public static void addType(String entityType, Class<? extends GameObject> entityClass) {
		getInstance().addEntityType(entityType, entityClass);
	}

	/**
	 * return the existing instance of the {@link EntityFactory}.
	 * 
	 * @return
	 */
	public static EntityFactory getInstance() {
		if (instance == null) {
			instance = new EntityFactory();
		}
		return instance;
	}
}
