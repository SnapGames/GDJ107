/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2018
 */
package com.snapgames.gdj.gdj107.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.snapgames.gdj.core.Game;
import com.snapgames.gdj.core.entity.AbstractGameObject;
import com.snapgames.gdj.core.entity.CameraObject;
import com.snapgames.gdj.core.ui.TextObject;

/**
 * Head Up Display (HUD). display Life level, energy, mana, items and score in
 * front of any other game object layer.
 * 
 * @author Frédéric Delorme
 *
 */
public class HeadUpDisplay extends AbstractGameObject {

	// Object moved by player
	private TextObject scoreTextObject = null;

	int dEnergy = 1;
	private GaugeObject energy;
	int dMana = 1;
	private GaugeObject mana;

	private ItemContainerObject[] itemContainers = new ItemContainerObject[2];

	// score
	private int score = 0;

	public HeadUpDisplay(Game game, CameraObject camera) {

		Font scoreFont = game.getGraphics().getFont().deriveFont(14.0f);

		int marginLeft = (int) (Game.WIDTH * camera.getMargin() * 2);
		int marginTop = (int) (Game.HEIGHT * camera.getMargin() * 2);
		int marginRight = (int) (Game.WIDTH * (1 - camera.getMargin() * 2));
		int marginBottom = (int) (Game.HEIGHT * (1 - camera.getMargin() * 2));

		// HUD Definition (layer 1)
		scoreTextObject = (TextObject) new TextObject("score").setText(String.format("%06d", score))
				.setShadowColor(Color.BLACK).setShadowBold(2).setFont(scoreFont).setPosition(marginLeft, marginTop)
				.setLayer(1).setPriority(1).setColor(Color.WHITE);

		addChild(scoreTextObject);
		energy = (GaugeObject) new GaugeObject("energy").setMinValue(0).setMaxValue(100).setValue(100)
				.setPosition(marginRight - 50, marginTop).setSize(42, 6).setLayer(1).setPriority(1)
				.setColor(new Color(1.0f, 0.0f, 0.0f, 0.7f));
		addChild(energy);

		mana = (GaugeObject) new GaugeObject("mana").setMinValue(0).setMaxValue(100).setValue(100)
				.setPosition(marginRight - 50, marginTop + 12).setSize(42, 6).setLayer(1).setPriority(1)
				.setColor(new Color(0.0f, 0.0f, 1.0f, 0.9f));
		addChild(mana);
		ItemContainerObject[] itemContainers = new ItemContainerObject[2];
		for (int i = 0; i < itemContainers.length; i++) {
			itemContainers[i] = (ItemContainerObject) new ItemContainerObject("itContainer_" + i)
					.setFont(game.getFont().deriveFont(9.0f))
					.setPosition(marginRight - (6 + (i + 1) * 22), marginBottom - 40).setSize(16, 16).setLayer(1)
					.setPriority(1).addAttribute("count", new Integer((int) (Math.random() * 10)));
			addChild(itemContainers[i]);
		}

	}

	/**
	 * 
	 */
	@Override
	public void draw(Game game, Graphics2D g) {
		scoreTextObject.draw(game, g);
		energy.draw(game, g);
		mana.draw(game, g);
		for (int i = 0; i < itemContainers.length; i++) {
			itemContainers[i].draw(game, g);
		}

	}

	/**
	 * Set Score value;
	 * 
	 * @param value
	 */
	public void setScore(int value) {
		score = value;
		scoreTextObject.text = String.format("%06d", value);
	}

	/**
	 * Set the player's energy value.
	 * 
	 * @param energyValue
	 */
	public void setEnergy(Integer energyValue) {
		energy.setValue(energyValue);

	}

	/**
	 * Set the player's Mana value.
	 * 
	 * @param manaValue
	 */
	public void setMana(Integer manaValue) {
		mana.setValue(manaValue);

	}

	/**
	 * return the score value.
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * return the Mana value
	 * 
	 * @return
	 */
	public int getMana() {
		return (int) mana.getAttribute("mana");
	}

	/**
	 * return the Mana value
	 * 
	 * @return
	 */
	public int getEnergy() {
		return (int) energy.getAttribute("mana");
	}

}
