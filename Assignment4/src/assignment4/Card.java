package assignment4;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 * 
 */

public class Card extends JButton {
	private Color faceColor;
	private static Color backColor = Color.gray;
	private boolean isFaceUp = false;

	/**
	 * Class constructor that creates a Card object giving it a face color
	 * determined by the board and sets it to be face down.
	 * 
	 * @param faceColor
	 */
	public Card(Color faceColor) {
		this.faceColor = faceColor;
		setFaceDown();
	}

	/**
	 * Public method that overrides the built-in toString() method to return the
	 * current Card's faceColor.
	 */
	public String toString() {
		return "" + faceColor;
	}

	/**
	 * Public method that paints the current Card object with its appropriate
	 * faceColor and sets the class variable isFaceUp to true.
	 */
	public void setFaceUp() {
		setBackground(faceColor);
		paintImmediately(0, 0, getWidth(), getHeight());
		isFaceUp = true;
	}

	/**
	 * Public method that paints the current Card object with its appropriate
	 * backColor and sets the class variable isFaceUp to false.
	 */
	public void setFaceDown() {
		setBackground(backColor);
		isFaceUp = false;
		paintImmediately(0, 0, getWidth(), getHeight());
	}

	/**
	 * Public method that returns the current Card object's isFaceUp variable.
	 * 
	 * @return
	 */
	public boolean getIsFaceUp() {
		return isFaceUp;
	}

	/**
	 * Public method that returns the current Card object's faceColor variable.
	 * 
	 * @return
	 */
	public Color getFaceColor() {
		return faceColor;
	}
}
