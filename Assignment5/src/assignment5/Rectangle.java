package assignment5;

import java.awt.Graphics;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 * 
 *          Public class for Rectangle Objects. Extends the abstract class
 *          Shape.
 *
 */
public class Rectangle extends Shape {
	private int width;
	private int height;
	private int[] topLeftCorner;
	private int[] topRightCorner;
	private int[] bottomLeftCorner;
	private int[] bottomRightCorner;

	/**
	 * 
	 * Class constructor that creates a Rectangle object. Accepts startX, startY,
	 * width, height as parameters. Assigns the Rectangle object a random color.
	 * 
	 * @param startX The x-coordinate of the starting point of the Rectangle.
	 * @param startY The y-coordinate of the starting point of the Rectangle.
	 * @param width  The width of the Rectangle.
	 * @param height The height of the Rectangle.
	 */
	public Rectangle(int startX, int startY, int width, int height) {
		setStartX(startX);
		setStartY(startY);
		this.width = width;
		this.height = height;
		setColor();
	}

	/**
	 * Implements Shape's abstract draw method. Sets the color of the Graphics
	 * object to be the color of the Rectangle. Calls the drawRect(int, int, int,
	 * int) method of the Graphics object.
	 * 
	 * @param g The Graphics object.
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawRect(getStartX(), getStartY(), width, height);

	}

	/**
	 * Overrides the built-in toString() method to return the String in the desired
	 * format.
	 */
	public String toString() {
		return "R," + getStartX() + "," + getStartY() + "," + width + "," + height;
	}

	/**
	 * Implements Shape's abstract contains method. Reformats the accepted int x and
	 * int y parameters to be in the form of an array int[]{x, y}. Uses the corners
	 * of the Rectangle object to determine if the test point is close to an edge of
	 * the Rectangle. Returns true if the test point is within 0.5 units of the
	 * edge. Returns false otherwise.
	 * 
	 * @param x The x-coordinate of the test point.
	 * @param y The y-coordinate of the test point.
	 * @return boolean
	 */
	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		int[] tempPoint = new int[] { x, y };
		return (distanceOf(topLeftCorner, tempPoint)
				+ distanceOf(bottomLeftCorner, tempPoint) > distanceOf(topLeftCorner, bottomLeftCorner) - 0.5
				&& distanceOf(topLeftCorner, tempPoint)
						+ distanceOf(bottomLeftCorner, tempPoint) < distanceOf(topLeftCorner, bottomLeftCorner) + 0.5)
				|| (distanceOf(topLeftCorner, tempPoint)
						+ distanceOf(topRightCorner, tempPoint) > distanceOf(topLeftCorner, topRightCorner) - 0.5
						&& distanceOf(topLeftCorner, tempPoint)
								+ distanceOf(topRightCorner, tempPoint) < distanceOf(topLeftCorner, topRightCorner)
										+ 0.5)
				|| (distanceOf(bottomRightCorner, tempPoint)
						+ distanceOf(topRightCorner, tempPoint) > distanceOf(bottomRightCorner, topRightCorner) - 0.5
						&& distanceOf(bottomRightCorner, tempPoint)
								+ distanceOf(topRightCorner, tempPoint) < distanceOf(bottomRightCorner, topRightCorner)
										+ 0.5)
				|| (distanceOf(bottomRightCorner, tempPoint)
						+ distanceOf(bottomLeftCorner, tempPoint) > distanceOf(bottomRightCorner, bottomLeftCorner)
								- 0.5
						&& distanceOf(bottomRightCorner, tempPoint) + distanceOf(bottomLeftCorner,
								tempPoint) < distanceOf(bottomRightCorner, bottomLeftCorner) + 0.5);

	}

	/**
	 * Uses the distance formula to return the distance between two points.
	 * 
	 * @param firstPoint  The first point that is passed in.
	 * @param secondPoint The second point that is passed in.
	 * @return The calculated distance.
	 */
	public double distanceOf(int[] firstPoint, int[] secondPoint) {
		return Math.sqrt(((secondPoint[0] - firstPoint[0]) * (secondPoint[0] - firstPoint[0]))
				+ ((secondPoint[1] - firstPoint[1]) * (secondPoint[1] - firstPoint[1])));

	}

	/**
	 * Sets the coordinates of the top left corner of the Rectangle object.
	 * 
	 * @param topLeftCorner The top left corner of the Rectangle object.
	 */
	public void setTopLeftCorner(int[] topLeftCorner) {
		this.topLeftCorner = topLeftCorner;
	}

	/**
	 * Sets the coordinates of the top right corner of the Rectangle object.
	 * 
	 * @param topRightCorner The top right corner of the Rectangle object.
	 */
	public void setTopRightCorner(int[] topRightCorner) {
		this.topRightCorner = topRightCorner;

	}

	/**
	 * Sets the coordinates of the bottom left corner of the Rectangle object.
	 * 
	 * @param bottomLeftCorner The bottom left corner of the Rectangle object.
	 */
	public void setBottomLeftCorner(int[] bottomLeftCorner) {
		this.bottomLeftCorner = bottomLeftCorner;

	}

	/**
	 * Sets the coordinates of the bottom right corner of the Rectangle object.
	 * 
	 * @param bottomRightCorner The bottom right corner of the Rectangle object.
	 */
	public void setBottomRightCorner(int[] bottomRightCorner) {
		this.bottomRightCorner = bottomRightCorner;
	}
}
