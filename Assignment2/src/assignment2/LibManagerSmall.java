package assignment2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Scanner;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 *
 */
public class LibManagerSmall {
	private ArrayList<Book> bookList;
	private ArrayList<Patron> patronList;

	private String[] menuOptions;

	public static void main(String[] args) {
		LibManagerSmall lm = new LibManagerSmall();
		lm.execute();
	}

	public LibManagerSmall() {
		bookList = readBooks("Resources/books.txt");
		patronList = readPatrons("Resources/patrons.txt");
		recordLoans("Resources/loans.txt");
		menuOptions = new String[] { "Add Book", "Add Patron", "List By Author", "List By Year", "Show Borrower",
				"Show Borrowed Books", "Return Book", "Exit" };
	}

	private void execute() {

		while (true) {
			int opt = getMenuOption();
			switch (opt) {
			case 1:
				addBook();
				break;
			case 2:
				addPatron();
				break;
			case 3:
				listByAuthor();
				break;
			case 4:
				listByYear();
				break;
			case 5:
				showBorrowers();
				break;
			case 6:
				showBorrowedBooks();
				break;
			case 7:
				returnBook();
				break;
			case 8:
				exitProgram();
				break;
			default:
				System.out.println("No such option");
			}
		}

	}

	private int getMenuOption() {

		System.out.println("Select one of the following options");
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("\t" + (i + 1) + ". " + menuOptions[i]);
		}

		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();

		return choice;
	}

	/* MAKE NO CHANGES ABOVE THIS LINE */
	/* COMPLETE ALL THE CODE STUBS BELOW */

	private void exitProgram() {
		System.out.println("Exiting..");
		writeToBookFile("Resources/books.txt");
		writeToPatronFile("Resources/patrons.txt");
		writeToLoansFile("Resources/loans.txt");
		System.exit(0);
	}
	

	private ArrayList<Book> readBooks(String filename) {
		// TODO Auto-generated method stub
		System.out.println("Reading file " + filename);
		String bookFileData = "";
		try {
			bookFileData = new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		String[] bookData = bookFileData.replace("\t", "").split("[;\n]");
		ArrayList<Book> tempBookList = new ArrayList<>();
		String removeTitleSpace;

		for (int i = 0; i < (bookData.length - 3); i += 4) {
			removeTitleSpace = bookData[i + 1];
			if (Character.isWhitespace(removeTitleSpace.charAt(removeTitleSpace.length() - 1))) {
				removeTitleSpace = removeTitleSpace.substring(0, removeTitleSpace.length() - 1);
			}
			tempBookList.add(new Book(bookData[i].replace(" ", ""), removeTitleSpace, bookData[i + 2],
					bookData[i + 3].replace("\n", "").replace("\r", "").replace(" ", "")));
		}

		// TODO Auto-generated method stub

		return tempBookList;
	}

	private ArrayList<Patron> readPatrons(String filename) {
		// TODO Auto-generated method stub
		System.out.println("Reading file " + filename);
		String patronFileData = "";
		try {
			patronFileData = new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		String[] patronData = patronFileData.replace("\n", "").replace(" ", "").split("[\t\r;]");
		ArrayList<Patron> tempPatronList = new ArrayList<>();

		for (int i = 0; i < (patronData.length - 1); i += 2) {
			tempPatronList.add(new Patron(patronData[i], patronData[i + 1]));
		}
		return tempPatronList;
	}

	private void recordLoans(String filename) {
		// TODO Auto-generated method stub
		System.out.println("Reading file " + filename);
		String loansFileData = "";
		try {
			loansFileData = new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		String[] loansData = loansFileData.replace("\t", "").replace(" ", "").replace("\r", "").split("[\n,]");
		for (int i = 0; i < loansData.length; i += 2) {
			Book b = locateBook(loansData[i]);
			Patron p = locatePatron(loansData[i + 1]);
			if (b.isBorrowed()) {
			} else {
				b.lend(p);
			}
		}
	}

	private Book locateBook(String bookId) {
		// TODO Auto-generated method stub
		System.out.println("Locating book with id = " + bookId);
		for (Book b : bookList) {
			if (b.hasId(bookId)) {
				return b;
			}
		}
		return null;
	}

	private Patron locatePatron(String patronId) {
		// TODO Auto-generated method stub
		System.out.println("Locating patron with id =" + patronId);
		for (Patron p : patronList) {
			if (p.hasId(patronId)) {
				return p;
			}
		}
		return null;
	}

	private void showBorrowedBooks() {
		// TODO Auto-generated method stub
		System.out.println("Executing showBorrowedBooks");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a PatronId: ");
		String patronId = s.next().toUpperCase();
		Patron p = locatePatron(patronId);
		if (p == null) {
			System.out.println(patronId + " is not a valid PatronId.");
			System.out.println("PatronIds must be in the form P# (e.g. P107)\n");
		} else {
			ArrayList<Book> patronBookList = p.showMyBooks();
			System.out.println(p);
			if (patronBookList.size() == 0) {
				System.out.println("No books found for patron " + p);
			} else {
				for (Book b : patronBookList) {
					System.out.println(b);
				}
			}
		}
	}

	private void showBorrowers() {
		// TODO Auto-generated method stub
		System.out.println("Executing showBorrowers");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a BookId: ");
		String bookId = s.next().toUpperCase();
		Book b = locateBook(bookId);
		if (b == null) {
			System.out.println(bookId + " is not a valid BookId.");
			System.out.println("BookIds must be in the form B# (e.g. B107)\n");
		} else {
			Patron p = b.whoHasMe();
			System.out.println(p + " has " + b);
		}
	}

	private void listByYear() {
		// TODO Auto-generated method stub
		System.out.println("Executing listByYear");
		Scanner minY = new Scanner(System.in);
		System.out.println("Enter the minimum publication year: ");
		String minBookPublicationYear = minY.next();

		Scanner maxY = new Scanner(System.in);
		System.out.println("Enter the maximum publication year: ");
		String maxBookPublicationYear = maxY.next();

		ArrayList<Book> bookPublicationYearList = new ArrayList<>();
		ArrayList<Integer> years = new ArrayList<>();
		int i = Integer.valueOf(minBookPublicationYear);
		while (i <= Integer.valueOf(maxBookPublicationYear)) {
			years.add(i);
			i++;
		}

		for (Book b : bookList) {
			for (int j : years) {
				if (b.hasPublicationYear(String.valueOf(j))) {
					bookPublicationYearList.add(b);
				}
			}
		}
		if (bookPublicationYearList.size() == 0) {
			System.out.println(
					"No books published from " + minBookPublicationYear + " to " + maxBookPublicationYear + ".");
		} else {
			for (Book b : bookPublicationYearList) {
				System.out.println(b);
			}
		}

	}

	private void listByAuthor() {
		// TODO Auto-generated method stub
		System.out.println("Executing listByAuthor");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter an Author: ");
		String bookAuthor = s.nextLine();
		ArrayList<Book> bookAuthorList = new ArrayList<>();
		for (Book b : bookList) {
			if (b.hasAuthor(bookAuthor)) {
				bookAuthorList.add(b);
			}
		}
		if (bookAuthorList.size() == 0) {
			System.out.println(bookAuthor + " not found.");
		} else {
			for (Book b : bookAuthorList) {
				System.out.println(b);
			}
		}
	}

	private void addPatron() {
		// TODO Auto-generated method stub
		System.out.println("Executing addPatron");
		int highestPatronId = 0;
		int patronIdInt;
		for (Patron p : patronList) {
			patronIdInt = Integer.parseInt(p.getId().substring(1));
			if (patronIdInt > highestPatronId) {
				highestPatronId = patronIdInt;
			}

		}
		String patronId = "P" + Integer.toString(highestPatronId + 1);

		Scanner pn = new Scanner(System.in);
		System.out.println("Enter the patron's name: ");
		String patronName = pn.nextLine();

		patronList.add(new Patron(patronId, patronName));
		System.out.println(patronId + "; " + patronName);
		System.out.println("has been added as a patron.");
	}

	private void addBook() {
		// TODO Auto-generated method stub
		System.out.println("Executing addBook");
		int highestBookId = 0;
		int bookIdInt;
		for (Book b : bookList) {
			bookIdInt = Integer.parseInt(b.getId().substring(1));
			if (bookIdInt > highestBookId) {
				highestBookId = bookIdInt;
			}

		}
		String bookId = "B" + Integer.toString(highestBookId + 1);

		Scanner bt = new Scanner(System.in);
		System.out.println("Enter a book title: ");
		String bookTitle = bt.nextLine();

		Scanner ba = new Scanner(System.in);
		System.out.println("Enter the author: ");
		String bookAuthor = ba.nextLine();

		Scanner bpy = new Scanner(System.in);
		System.out.println("Enter the publication year: ");
		String bookPublicationYear = bpy.next();

		bookList.add(new Book(bookId, bookTitle, bookAuthor, bookPublicationYear));
		System.out.println(bookId + "; " + bookTitle + "; " + bookAuthor + "; " + bookPublicationYear);
		System.out.println("has been added to the library.");
	}

	private void returnBook() {
		// TODO Auto-generated method stub
		System.out.println("Executing returnBook");
		Scanner rb = new Scanner(System.in);
		System.out.println("Enter the BookId: ");
		String bookToReturn = rb.next().toUpperCase();
		Book b = locateBook(bookToReturn);
		if (b == null) {
			System.out.println(bookToReturn + " is not a valid BookId.");
			System.out.println("BookIds must be in the form B# (e.g. B107)\n");
		} else {
			Patron p = b.whoHasMe();
			b.returnBook();
			p.returnBook(b);
			System.out.println(p + " has returned " + b);

		}
	}

	private void writeToPatronFile(String filename) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			for (int i = 0; i < patronList.size() - 1; i++) {
				out.write(patronList.get(i).toString() + "\n");
			}
			out.write(patronList.get(patronList.size() - 1).toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToBookFile(String filename) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			for (int i = 0; i < bookList.size() - 1; i++) {
				out.write(bookList.get(i).toString() + "\n");
			}
			out.write(bookList.get(bookList.size() - 1).toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToLoansFile(String filename) {
		ArrayList<String> bookLoaned = new ArrayList<>();
		ArrayList<String> patronBorrower = new ArrayList<>();
		for (Book b : bookList) {
			Patron p = b.whoHasMe();
			if (p != null) {
				bookLoaned.add(b.getId());
				patronBorrower.add(p.getId());
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			for (int i = 0; i < bookLoaned.size() - 1; i++) {
				out.write(bookLoaned.get(i) + ", " + patronBorrower.get(i) + "\n");
			}
			out.write(bookLoaned.get(bookLoaned.size() - 1) + ", " + patronBorrower.get(patronBorrower.size() - 1));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}