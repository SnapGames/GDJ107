/**
 * McGivrer's Blog 
 *
 * Entity Component System framework 
 *
 * @copyright 2018
 */
package com.snapgames.gdj.core.math;

/**
 * Internal Class to manipulate simple Vector2D.
 * 
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 *
 */
public class Vector2D {
	public static final Vector2D ZERO = new Vector2D(0, 0);
	public static final Vector2D IDENTITY = new Vector2D(1, 1);
	public float x, y;

	public Vector2D() {
		x = 0.0f;
		y = 0.0f;
	}

	/**
	 * @param x
	 * @param y
	 */
	public Vector2D(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Compute distance between this vector and the vector <code>v</code>.
	 * 
	 * @param v
	 *            the vector to compute distance with.
	 * @return
	 */
	public float distance(Vector2D v) {
		float v0 = x - v.x;
		float v1 = y - v.y;
		return (float) Math.sqrt(v0 * v0 + v1 * v1);
	}

	/**
	 * Normalization of this vector.
	 */
	public Vector2D normalize() {
		// sets length to 1
		//
		double length = Math.sqrt(x * x + y * y);

		if (length != 0.0) {
			float s = 1.0f / (float) length;
			x = x * s;
			y = y * s;
		}

		return new Vector2D(x, y);
	}

	/**
	 * Add the <code>v</code> vector to this vector.
	 * 
	 * @param v
	 *            the vector to add to this vector.
	 * @return this.
	 */
	public Vector2D add(Vector2D v) {
		x += v.x;
		y += v.y;
		return this;
	}

	/**
	 * Multiply vector by a factor.
	 * 
	 * @param factor
	 *            the factor to multiply the vector by.
	 * @return this.
	 */
	public Vector2D multiply(float factor) {
		x *= factor;
		y *= factor;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(").append(String.format("%02.2f", x)).append(",").append(String.format("%02.2f", y)).append(")");
		return builder.toString();
	}

	/**
	 * Substract the Vector2D v to this current and return result.
	 * 
	 * @param v
	 *            vector2D to be substracted.
	 * @return Vector2D resulting of substraction.
	 */
	public Vector2D substract(Vector2D v) {
		return new Vector2D(x - v.x, y - v.y);
	}

	/**
	 * Dot product for current instance {@link Vector2D} and the <code>v1</code>
	 * vector.
	 * 
	 * @param v1
	 * @return
	 */
	public double dot(Vector2D v1) {
		return this.x * v1.x + this.y * v1.y;
	}

}