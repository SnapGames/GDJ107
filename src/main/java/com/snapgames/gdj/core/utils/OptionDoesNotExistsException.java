/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj106
 * 
 * @year 2017
 */
package com.snapgames.gdj.core.utils;

/**
 * 
 * @author Frédéric Delorme
 *
 */
public class OptionDoesNotExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1804050701564110786L;
	String optionName;

	/**
	 * @param name
	 */
	public OptionDoesNotExistsException(String name) {
		super(String.format("option %s does not exists", name));
		optionName = name;
	}

}
