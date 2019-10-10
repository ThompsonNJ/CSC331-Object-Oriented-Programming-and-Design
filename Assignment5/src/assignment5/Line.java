package assignment5;

import java.awt.Graphics;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 * 
 *          Public class for Line Objects. Extends the abstract class Shape.
 *
 */
public class Line extends Shape {
	private int endX, endY;

	/**
	 * Class constructor that creates a Line object. Accepts startX, startY, endX,
	 * endY as parameters. Assigns the Line object a random color.
	 * 
	 * @param startX The x-coordinate of the starting point of the Line.
	 * @param startY The y-coordinate of the starting point of the Line.
	 * @param endX   The x-coordinate of the ending point of the Line.
	 * @param endY   The y-coordinate of the ending point of the Line.
	 */
	public Line(int startX, int startY, int endX, int endY) {
		setStartX(startX);
		setStartY(startY);
		this.endX = endX;
		this.endY = endY;
		setColor();

	}

	/**
	 * Implements Shape's abstract draw method. Sets the color of the Graphics
	 * object to be the color of the Line. Calls the drawLine(int, int, int, int)
	 * method of the Graphics object.
	 * 
	 * @param g The Graphics object.
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawLine(getStartX(), getStartY(), endX, endY);
	}

	/**
	 * Implements Shape's abstract contains method. Uses the starting and ending
	 * points of the Line object to determine if the test point is within 0.5 units
	 * of the Line object.
	 * 
	 * @param x The x-coordinate of the test point.
	 * @param y The y-coordinate of the test point.
	 * @return boolean
	 */
	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return (distanceOf(getStartX(), getStartY(), x, y) + distanceOf(endX, endY, x, y)) > distanceOf(getStartX(),
				getStartY(), endX, endY) - 0.5
				&& (distanceOf(getStartX(), getStartY(), x, y) + distanceOf(endX, endY, x, y)) < distanceOf(getStartX(),
						getStartY(), endX, endY) + 0.5;
	}

	/**
	 * Uses the distance formula to return the distance between two points.
	 * 
	 * @param x1 The x-coordinate of the first point.
	 * @param y1 The y-coordinate of the first point.
	 * @param x2 The x-coordinate of the second point.
	 * @param y2 The y-coordinate of the second point.
	 * @return The calculated distance
	 */
	public double distanceOf(int x1, int y1, int x2, int y2) {
		return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
	}

	/**
	 * Overrides the built-in toString() method to return the String in the desired
	 * format.
	 * 
	 */
	public String toString() {
		return "L," + getStartX() + "," + getStartY() + "," + endX + "," + endY;
	}

}
