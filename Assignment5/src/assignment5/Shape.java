package assignment5;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 *
 */
public abstract class Shape {
	private Color myColor;
	private int startX, startY;

	/**
	 * Abstract method used to draw the Shape.
	 * 
	 * @param g The Graphics object.
	 */
	public abstract void draw(Graphics g);

	/**
	 * Abstract method that returns true if the Shape contains the test point,
	 * otherwise returns false. The implementation of this method is determined by
	 * child classes.
	 * 
	 * @param x The x-coordinate of the test point.
	 * @param y The y-coordinate of the test point.
	 * @return boolean
	 */
	public abstract boolean contains(int x, int y);

	/**
	 * Abstract method that overrides the built-in toString() method to return the
	 * String in the format determined by a child class' implementation.
	 */
	public abstract String toString();

	/**
	 * Public method that sets the myColor class variable to a random color.
	 * 
	 */
	public void setColor() {
		Color[] possibleColors = new Color[] { Color.red, Color.green, Color.blue, Color.magenta };
		Random randIndex = new Random();
		myColor = possibleColors[randIndex.nextInt(4)];
	}

	/**
	 * Public method that returns the myColor class variable.
	 * 
	 * @return myColor: the Color of the Shape.
	 */
	public Color getColor() {
		return myColor;
	}

	/**
	 * Public method that returns the startX class variable.
	 * 
	 * @return startX: The x-coordinate of the starting point of the Shape.
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Public method that returns the startY class variable.
	 * 
	 * @return startY: The y-coordinate of the starting point of the Shape.
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Public method that sets the startX class variable to the accepted parameter.
	 * 
	 * @param startX The x-coordinate of the starting point of the Shape.
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}

	/**
	 * Public method that sets the startY class variable to the accepted parameter.
	 * 
	 * @param startY The y-coordinate of the starting point of the Shape.
	 */
	public void setStartY(int startY) {
		this.startY = startY;
	}
}