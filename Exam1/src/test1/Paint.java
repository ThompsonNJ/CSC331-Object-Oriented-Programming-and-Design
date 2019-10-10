package test1;

public class Paint {
	// Properties
	private String name;
	private int redValue;
	private int greenValue;
	private int blueValue;
	private double price;

	// Constructor
	public Paint(String name, int redValue, int greenValue, int blueValue, double price) {
		this.name = name;
		this.redValue = redValue;
		this.greenValue = greenValue;
		this.blueValue = blueValue;
		this.price = price;
	}

	// Behavior
	public String toString() {
		return "name = " + name + ", red = " + redValue + ", green = " + greenValue + ", blue = " + blueValue
				+ ", price = " + price;
	}

	public boolean equals(Paint inputtedPaint) {
		if (this.redValue == inputtedPaint.redValue && this.greenValue == inputtedPaint.greenValue
				&& this.blueValue == inputtedPaint.blueValue) {
			return true;
		} else {
			return false;
		}
	}

	public Paint mix(Paint inputtedPaint) {
		String newName = name + " " + inputtedPaint.name;
		int newRedValue = (redValue + inputtedPaint.redValue) / 2;
		int newGreenValue = (greenValue + inputtedPaint.greenValue) / 2;
		int newBlueValue = (blueValue + inputtedPaint.blueValue) / 2;
		double newPrice = (price + inputtedPaint.price) / 2;
		return new Paint(newName, newRedValue, newGreenValue, newBlueValue, newPrice);
	}

	public double distanceTo(Paint inputtedPaint) {
		return Math.sqrt((redValue - inputtedPaint.redValue) * (redValue - inputtedPaint.redValue)
				+ (greenValue - inputtedPaint.greenValue) * (greenValue - inputtedPaint.greenValue)
				+ (blueValue - inputtedPaint.blueValue) * (blueValue - inputtedPaint.blueValue));
	}
	public void updatePrice(double inputtedPrice) {
		price = inputtedPrice;
	}

}
