package assignment4;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 * @author Nick Thompson
 * @version 1.0.0
 * 
 */

public class Board extends JFrame {
	private int numberOfRows;
	private int numberOfColumns;
	private Card[] deck;

	/**
	 * Class constructor that accepts an int numberOfPairs and creates the Board
	 * object.
	 * 
	 * @param numberOfPairs
	 */
	public Board(int numberOfPairs) {
		int numberOfCards = numberOfPairs * 2;
		calcDim(numberOfCards);

		setTitle("The Concentration Game");
		setSize(600, 600);

		Controller ctrlr = new Controller();
		Container cntnr = getContentPane();
		cntnr.setLayout(new GridLayout(numberOfRows, numberOfColumns, 1, 1));
		deck = new Card[numberOfCards];

		createCards(numberOfPairs, ctrlr);

		List<Card> generateBoard = Arrays.asList(deck);
		Collections.shuffle(generateBoard);
		for (Card c : generateBoard) {
			cntnr.add(c);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createCards(int numberOfPairs, Controller ctrlr) {
		Color[] faceColorChoices = new Color[] { Color.red, Color.yellow, Color.green, Color.blue, Color.magenta,
				Color.orange };
		for (int i = 0; i < numberOfPairs; i++) {
			Card b1 = new Card(faceColorChoices[i % 6]);
			b1.addActionListener(ctrlr);
			deck[i] = b1;
			Card b2 = new Card(faceColorChoices[i % 6]);
			b2.addActionListener(ctrlr);
			deck[i + numberOfPairs] = b2;
		}
	}

	private void calcDim(int numberOfCards) {
		int limit = (int) (Math.sqrt(numberOfCards));
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for (int i = 1; i <= limit; i += 1) {
			if (numberOfCards % i == 0) {
				factors.add(i);
				if (i != numberOfCards / i) {
					factors.add(numberOfCards / i);
				}
			}
		}
		if (factors.size() % 2 == 0) {
			numberOfRows = factors.get(factors.size() - 1);
			numberOfColumns = factors.get(factors.size() - 2);
		} else {
			numberOfRows = factors.get(factors.size() - 1);
			numberOfColumns = factors.get(factors.size() - 1);
		}
	}

	/**
	 * Public method that is called to check if the game is over. Returns true if
	 * all cards on the board are face up.
	 * 
	 * @return
	 */
	public boolean gameOver() {
		for (Card c : deck)
			if (!c.getIsFaceUp())
				return false;

		return true;
	}

	/**
	 * Main method used to run the program. Requests user input and calls the board
	 * constructor with that input.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("How many pairs would you like to play with?: ");
		int numberOfPairs = s.nextInt();
		new Board(numberOfPairs);
	}

	/**
	 * 
	 * @author Nick Thompson
	 * @version 1.0.0
	 * 
	 */

	private class Controller implements ActionListener {
		private boolean guessToggle = false;
		private Card[] checkMatches = new Card[2];
		private int totalClicks = 0;

		/**
		 * Public method that overrides the default actionPerformed method. Turns cards
		 * face up on click. If the last two cards clicked match, they remain faceup.
		 * Otherwise, they turn face down after 1 second. This method calls gameOver()
		 * to check if the game is over. If the game is over, the total number of
		 * accepted clicks is printed to the console and the program closes after 0.5
		 * seconds.
		 */

		@Override
		public void actionPerformed(ActionEvent ae) {
			// TODO Auto-generated method stub
			Object clickedObject = ae.getSource();
			if (((Card) clickedObject).getIsFaceUp() == false && !guessToggle) {
				((Card) clickedObject).setFaceUp();
				guessToggle = true;
				totalClicks++;
				checkMatches[0] = ((Card) clickedObject);

			} else if (((Card) clickedObject).getIsFaceUp() == false && guessToggle) {
				((Card) clickedObject).setFaceUp();
				totalClicks++;
				checkMatches[1] = ((Card) clickedObject);
				if (checkMatches[0].getFaceColor().equals(checkMatches[1].getFaceColor())) {
					guessToggle = false;
					checkMatches[1] = null;
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					checkMatches[0].setFaceDown();
					checkMatches[1].setFaceDown();
					guessToggle = false;
					checkMatches[1] = null;
				}
			}
			if (gameOver()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Total number of clicks: " + totalClicks);
				System.exit(0);
			}
		}

	}
}
