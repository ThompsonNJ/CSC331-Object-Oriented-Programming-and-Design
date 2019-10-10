package assignment2;

import java.util.ArrayList;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 *
 */
public class Patron {
	// Properties
	private String patronId;
	private String patronName;
	private ArrayList<Book> myBooks;

	/**
	 * Class constructor that accepts an Id and name and creates a Patron object
	 * with its ArrayList of Book objects initialized to an empty ArrayList.
	 * 
	 * @param patronId
	 * @param patronName
	 */
	// Constructor
	public Patron(String patronId, String patronName) {
		this.patronId = patronId;
		this.patronName = patronName;
		myBooks = new ArrayList<Book>();
	}

	// Behavior
	/**
	 * Public method that accepts a Book object as an argument and adds it to the
	 * current Patron object's ArrayList myBooks.
	 * 
	 * @param b
	 */
	public void borrow(Book b) {
		myBooks.add(b);
	}

	/**
	 * public method that accepts a Book object as an argument and remove it from
	 * the current Patron object's ArrayList myBooks.
	 * 
	 * @param b
	 */
	public void returnBook(Book b) {
		myBooks.remove(b);
	}

	/**
	 * Public method that accepts a String as an argument and compares the current
	 * Patron object's patronId to the accepted argument. Returns true if the
	 * current Patron object's patronId equals the accepted String; returns false
	 * otherwise.
	 * 
	 * @param pId
	 * @return
	 */
	public boolean hasId(String pId) {
		if (patronId.equals(pId)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Public method that takes no arguments and returns the current Patron object's
	 * patronId as a String.
	 * 
	 * @return
	 */
	public String getId() {
		return patronId;
	}

	/**
	 * Public method that takes no arguments and returns the current Patron object's
	 * ArrayList myBooks.
	 * 
	 * @return
	 */
	public ArrayList<Book> showMyBooks() {
		return myBooks;
	}

	/**
	 * Public method that takes no arguments. Overrides Java's built-in toString()
	 * method to return a String consisting of the current Patron object's
	 * patronId;patronName;.
	 */
	public String toString() {
		return patronId + ";" + patronName + ";";
	}
}
