package assignment1;

/**
 * @author Nick Thompson
 * @version 1.0.0 Returns a Fraction object created by a given numerator and
 *          denominator, or created by a given decimal.
 */
public class Fraction {
	// Properties
	private double numerator;
	private double denominator;

	// Constructor
	/**
	 * Class constructor that accepts a numerator parameter and denominator
	 * parameter and creates a Fraction object.
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(double numerator, double denominator) {
		double gcd = greatestCommonDivisor(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
		fixSigns();
	}

	/**
	 * Class constructor that accepts a decimal parameter and creates a Fraction
	 * object.
	 * 
	 * @param decimalInput
	 */
	public Fraction(double decimalInput) {
		String[] splitDecimalInput = (String.valueOf(decimalInput)).split("\\.");
		double mantissa = Double.parseDouble(splitDecimalInput[1]);
		double divisorForMantissa = Math.pow(10, splitDecimalInput[1].length());

		numerator = (Double.parseDouble(splitDecimalInput[0]) * divisorForMantissa) + mantissa;
		denominator = divisorForMantissa;

		double gcd = greatestCommonDivisor(numerator, denominator);
		numerator = numerator / gcd;
		denominator = denominator / gcd;
		fixSigns();
	}

	// Methods
	/**
	 * Private method that makes the numerator and denominator positive if both the
	 * numerator and denominator were originally negative, or makes the numerator
	 * negative and the denominator positive if the numerator was originally
	 * positive and the denominator was originally negative.
	 */
	private void fixSigns() {
		if ((denominator < 0 && numerator > 0) || (denominator < 0 && numerator < 0)) {
			numerator *= -1;
			denominator *= -1;
		}
	}

	/**
	 * Private method that finds the greatest common divisor of the Fraction object.
	 * 
	 * @param tempNumerator
	 * @param tempDenominator
	 * @return
	 */
	private double greatestCommonDivisor(double tempNumerator, double tempDenominator) {
		if (tempDenominator == 0)
			return tempNumerator;
		return greatestCommonDivisor(tempDenominator, tempNumerator % tempDenominator);
	}

	/**
	 * Public method that formats the printing of the Fraction object.
	 */
	public String toString() {
		return Math.round(numerator) + "/" + Math.round(denominator);
	}

	/**
	 * Public method that converts the Fraction object to a decimal six places long.
	 * 
	 * @return
	 */
	public double toDecimal() {
		return Math.floor(numerator / denominator * 1000000) / 1000000;
	}

	/**
	 * Public method that adds two Fraction objects together and returns a new
	 * Fraction object.
	 * 
	 * @param addend
	 * @return
	 */
	public Fraction add(Fraction addend) {
		double newNumerator = (numerator * addend.denominator) + (addend.numerator * denominator);
		double newDenominator = denominator * addend.denominator;
		return new Fraction(newNumerator, newDenominator);
	}

	/**
	 * Public method that multiplies two Fraction objects together and returns a new
	 * Fraction object.
	 * 
	 * @param factor
	 * @return
	 */
	public Fraction mult(Fraction factor) {
		double newNumerator = numerator * factor.numerator;
		double newDenominator = denominator * factor.denominator;
		return new Fraction(newNumerator, newDenominator);
	}

	/**
	 * Public method that subtracts two Fraction objects together and returns a new
	 * Fraction object.
	 * 
	 * @param subtrahend
	 * @return
	 */
	public Fraction sub(Fraction subtrahend) {
		double newNumerator = (numerator * subtrahend.denominator) - (subtrahend.numerator * denominator);
		double newDenominator = denominator * subtrahend.denominator;
		return new Fraction(newNumerator, newDenominator);
	}

	/**
	 * Public method that divides two Fraction objects together and returns a new
	 * Fraction object.
	 * 
	 * @param divisor
	 * @return
	 */
	public Fraction div(Fraction divisor) {
		double newNumerator = numerator * divisor.denominator;
		double newDenominator = denominator * divisor.numerator;
		return new Fraction(newNumerator, newDenominator);
	}

	/**
	 * Public method that compares two Fraction objects together and returns a 1,
	 * -1, or 0 depending if the Fraction object compared is greater than, less
	 * than, or equal to respectively.
	 * 
	 * @param inputtedFraction
	 * @return
	 */
	public int compareTo(Fraction inputtedFraction) {
		if (this.toDecimal() > inputtedFraction.toDecimal())
			return 1;
		else if (this.toDecimal() < inputtedFraction.toDecimal())
			return -1;
		else
			return 0;
	}
}