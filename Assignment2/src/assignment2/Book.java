package assignment2;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 *
 */
public class Book {
	// Properties
	private String bookId;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublicationYear;
	private Patron borrower;

	/**
	 * Class constructor that accepts an Id, Title, Author, Publication Year and
	 * creates a Book object with its borrower initialized to null.
	 * 
	 * @param bookId
	 * @param bookTitle
	 * @param bookAuthor
	 * @param bookPublicationYear
	 */

	// Constructor
	public Book(String bookId, String bookTitle, String bookAuthor, String bookPublicationYear) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookPublicationYear = bookPublicationYear;
		borrower = null;
	}

	// Behavior
	/**
	 * Public method that accepts a Patron object as an argument, and assigns the
	 * current Book object a borrower. Calls the borrow(Book) method of the Patron
	 * class.
	 * 
	 * @param p
	 */
	public void lend(Patron p) {
		borrower = p;
		p.borrow(this);
	}

	/**
	 * Public method that sets the current Book object's borrower to null.
	 */
	public void returnBook() {
		borrower = null;

	}

	/**
	 * Public method that accepts a String as an argument and compares the current
	 * Book object's bookId to the accepted argument. Returns true if the current
	 * Book object's bookId equals the accepted String; returns false otherwise.
	 * 
	 * @param bId
	 * @return
	 */
	public boolean hasId(String bId) {	
		if (bookId.equals(bId)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Public method that accepts a String as an argument and compares the current
	 * Book object's bookAuthor to the accepted argument. Returns true if the
	 * current Book object's bookAuthor equals the accepted String; returns false
	 * otherwise.
	 * 
	 * @param ba
	 * @return
	 */
	public boolean hasAuthor(String ba) {
		if (bookAuthor.equals(ba)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Public method that accepts a String as an argument and compares the current
	 * Book object's bookPublicationYear to the accepted argument. Returns true if
	 * the current Book object's bookPublicationYear equals the accepted String;
	 * returns false otherwise.
	 * 
	 * @param py
	 * @return
	 */
	public boolean hasPublicationYear(String py) {
		if (bookPublicationYear.equals(py)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Public method that accepts no arguments. Returns true if the current Book
	 * object's borrower is null; returns false otherwise.
	 * 
	 * @return
	 */
	public boolean isBorrowed() {
		if (borrower == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Public method that takes no arguments and returns the current Book object's
	 * bookId as a String.
	 * 
	 * @return
	 */
	public String getId() {
		return bookId;

	}

	/**
	 * Public method that takes no arguments and returns the current Book object's
	 * borrower. Will return a Patron object or null depending if the Book object
	 * has a borrower.
	 * 
	 * @return
	 */
	public Patron whoHasMe() {
		return borrower;
	}

	/**
	 * Public method that takes no arguments. Overrides Java's built-in toString()
	 * method to return a String consisting of the current Book object's
	 * bookId;bookTitle;bookAuthor;bookPublicationYear.
	 */
	public String toString() {
		return bookId + ";" + bookTitle + ";" + bookAuthor + ";" + bookPublicationYear;
	}
}
