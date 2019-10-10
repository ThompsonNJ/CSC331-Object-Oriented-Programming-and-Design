package assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 * 
 */

public class Matrix {
	// Properties
	private int numberOfRows;
	private int numberOfColumns;
	private double[][] data;

	// Constructor
	/**
	 * Class constructor that accepts a 2d array of doubles and creates a Matrix
	 * object from the accepted 2d array. Initializes the class variables
	 * numberOfRows/numberOfColumns with the appropriate values.
	 * 
	 * @param data
	 */
	public Matrix(double[][] data) {
		numberOfRows = data.length;
		numberOfColumns = data[0].length;
		this.data = new double[numberOfRows][numberOfColumns];
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				this.data[i][j] = data[i][j];

	}

	/**
	 * Class constructor that accepts a file path as a String and creates a Matrix
	 * object from the accepted file path. Initializes the class variables
	 * numberOfRows/numberOfColumns with the appropriate values.
	 * 
	 * @param filename
	 */

	public Matrix(String filename) {
		String fileString = readFromFile(filename);
		String[] fileStringRows = fileString.replace("\r", "").split("\n");
		String[][] fileStringMatrix = new String[fileStringRows.length][];
		for (int i = 0; i < fileStringRows.length; i++)
			fileStringMatrix[i] = fileStringRows[i].split(" ");

		numberOfRows = fileStringMatrix.length;
		numberOfColumns = fileStringMatrix[0].length;
		data = new double[numberOfRows][numberOfColumns];
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				data[i][j] = Double.valueOf(fileStringMatrix[i][j]);
			}
		}
	}

	// Behavior
	private String readFromFile(String filename) {
		String matrixFileData = "";
		try {
			matrixFileData = new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return matrixFileData;
	}

	/**
	 * Public method that takes no arguments. Overrides Java's built-in toString()
	 * method to return a String consisting of the current Matrix's data.
	 */

	public String toString() {
		String matrix = "";
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++) {
				if (j == numberOfColumns - 1) {
					matrix += data[i][j] + "\n";
				} else {
					matrix += data[i][j] + " ";
				}
			}

		return numberOfRows + "x" + numberOfColumns + " matrix\n" + matrix;
	}

	/**
	 * Public method that accepts a Matrix object as an argument, iterates over the
	 * current matrix and the accepted matrix, and checks if each of their cells are
	 * equal. Overrides Java's built-in equals() method.
	 * 
	 * @param other
	 * @return
	 */

	public boolean equals(Matrix other) {
		if (numberOfRows == other.numberOfRows && numberOfColumns == other.numberOfColumns) {
			for (int i = 0; i < numberOfRows; i++)
				for (int j = 0; j < numberOfColumns; j++)
					if (data[i][j] != other.data[i][j])
						return false;
			return true;
		}
		return false;
	}

	/**
	 * Public method take takes no parameters. Returns a new Matrix object which is
	 * the transposed current Matrix object.
	 * 
	 * @return
	 */
	public Matrix transpose() {
		double[][] newData = new double[numberOfColumns][numberOfRows];
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				newData[j][i] = data[i][j];

		return new Matrix(newData);
	}

	/**
	 * Public method that accepts a Matrix object as a parameter. Adds the value of
	 * each cell from the accepted Matrix object and the current Matrix object if
	 * the two matrices are the same dimensions. Returns a new Matrix object
	 * consisting of the sums.
	 * 
	 * @param other
	 * @return
	 */

	public Matrix add(Matrix other) {
		if (numberOfRows == other.numberOfRows && numberOfColumns == other.numberOfColumns) {
			double[][] newMatrixData = new double[numberOfRows][numberOfColumns];
			for (int i = 0; i < numberOfRows; i++)
				for (int j = 0; j < numberOfColumns; j++)
					newMatrixData[i][j] = data[i][j] + other.data[i][j];
			return new Matrix(newMatrixData);
		}
		System.out.println("Cannot add matrices with different dimensions.");
		return null;
	}

	/**
	 * Public method that accepts a double as a parameter. Iterates over the current
	 * Matrix object and multiplies each cell with the accepted double. Returns a
	 * new Matrix object from the scalar multiplication.
	 * 
	 * @param scalar
	 * @return
	 */
	public Matrix mult(double scalar) {
		double[][] newMatrixData = new double[numberOfRows][numberOfColumns];
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				newMatrixData[i][j] = data[i][j] * scalar;
		return new Matrix(newMatrixData);
	}

	/**
	 * Public method that accepts a Matrix object as a parameter and multiples it
	 * with the current Matrix object. Returns the resulting Matrix object.
	 * 
	 * @param other
	 * @return
	 */
	public Matrix mult(Matrix other) {
		if (numberOfColumns == other.numberOfRows) {
			double[][] newMatrixData = new double[numberOfRows][other.numberOfColumns];
			for (int i = 0; i < numberOfRows; i++)
				for (int j = 0; j < other.numberOfColumns; j++)
					for (int k = 0; k < numberOfColumns; k++)
						newMatrixData[i][j] += data[i][k] * other.data[k][j];

			return new Matrix(newMatrixData);
		}

		System.out.print("Incorrect dimensions!");
		return null;
	}
}
