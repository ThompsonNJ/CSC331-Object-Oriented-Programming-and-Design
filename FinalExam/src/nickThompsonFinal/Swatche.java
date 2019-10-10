package nickThompsonFinal;

import java.awt.Color;

import javax.swing.JButton;

public class Swatche extends JButton {
	private String name;
	private int redValue, greenValue, blueValue;
	private Color myColor;
	private double price;
	private String type;

	public Swatche() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setColor(Color c) {
		myColor = c;
	}

	public Color getColor() {
		return myColor;

	}

	public void setRedValue(int redValue) {
		this.redValue = redValue;
	}

	public int getRedValue() {
		return redValue;

	}

	public void setGreenValue(int greenValue) {
		this.greenValue = greenValue;
	}

	public int getGreenValue() {
		return greenValue;

	}

	public void setBlueValue(int blueValue) {
		this.blueValue = blueValue;
	}

	public int getBlueValue() {
		return blueValue;

	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String toString() {
		return name;
	}

	public double findDistance(Swatche other) {
		return Math.sqrt(((other.getRedValue() - redValue) * (other.getRedValue() - redValue))
				+ ((other.getGreenValue() - greenValue) * (other.getGreenValue() - greenValue))
				+ ((other.getBlueValue() - blueValue) * (other.getBlueValue() - blueValue)));
	}
}
