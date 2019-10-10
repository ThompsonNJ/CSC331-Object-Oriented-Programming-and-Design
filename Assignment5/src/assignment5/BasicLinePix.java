package assignment5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Nick Thompson
 * @version 2.0.0
 *
 */

@SuppressWarnings("serial")
public class BasicLinePix extends JFrame {

	private JPanel drawingPanel; // user draws here

	private JPanel statusBar;
	private JLabel statusLabel;// used to show informational messages

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu drawMenu;
	private EventHandler eh;

	private String shapeToDraw = "Line";
	private ArrayList<Shape> drawnShapes = new ArrayList<Shape>();

	public static void main(String[] args) {
		BasicLinePix lp = new BasicLinePix();
		lp.setVisible(true);
	}

	/**
	 * Class constructor that creates a BasicLinePix object.
	 */
	public BasicLinePix() {
		setTitle("Basic Line Pix 2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp;

		cp = getContentPane();
		cp.setLayout(new BorderLayout());

		eh = new EventHandler();

		drawingPanel = makeDrawingPanel();
		drawingPanel.addMouseListener(eh);
		drawingPanel.addMouseMotionListener(eh);

		statusBar = createStatusBar();

		cp.add(drawingPanel, BorderLayout.CENTER);
		cp.add(statusBar, BorderLayout.SOUTH);

		buildMenu();

		pack();
	}

	private void buildMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		drawMenu = new JMenu("Draw");

		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Line");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);

		menuItem = new JMenuItem("Rectangle");
		menuItem.addActionListener(eh);
		drawMenu.add(menuItem);

		menuBar.add(fileMenu);
		menuBar.add(drawMenu);

		setJMenuBar(menuBar);
	}

	private JPanel makeDrawingPanel() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 400));
		p.setBackground(Color.YELLOW);

		return p;
	}

	private JPanel createStatusBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		statusLabel = new JLabel("Line selected");
		panel.add(statusLabel, BorderLayout.CENTER);

		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));

		return panel;
	}

	/**
	 * Public method that overrides the java.awt.Window.paint. Draws Shapes on the
	 * drawingPanel.
	 */
	// this method overrides the paint method defined in JFrame
	// add code here for drawing the lines on the drawingPanel
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics g1 = drawingPanel.getGraphics();
		for (Shape s : drawnShapes) {
			s.draw(g1);
		}

		// Send a message to each line in the drawing to
		// draw itself on g1
	}

	// Inner class - instances of this class handle action events
	private class EventHandler implements ActionListener, MouseListener, MouseMotionListener {

		private int startX, startY; // line's start coordinates
		private int lastX, lastY; // line's most recent end point

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("Line")) {
				shapeToDraw = "Line";
				statusLabel.setText("Line selected");
			} else if (arg0.getActionCommand().equals("Rectangle")) {
				shapeToDraw = "Rectangle";
				statusLabel.setText("Rectangle selected");
			} else if (arg0.getActionCommand().equals("New")) {
				drawnShapes.clear();
			} else if (arg0.getActionCommand().equals("Open")) {
				JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir") + "/Resources"));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					drawnShapes.clear();
					repaint();
					File selectedFile = fileChooser.getSelectedFile();
					String loadedFile = "Resources/" + selectedFile.getName();
					String fileData = "";
					try {
						fileData = new String(Files.readAllBytes(Paths.get(loadedFile)));
					} catch (IOException ie) {
						ie.printStackTrace();
					}
					String[] fileDataRows = fileData.replace("\r", "").split("\n");
					String[][] fileDataMatrix = new String[fileDataRows.length][];
					for (int i = 0; i < fileDataRows.length; i++) {
						fileDataMatrix[i] = fileDataRows[i].split(",");
					}

					for (int i = 0; i < fileDataRows.length; i++) {
						for (int j = 0; j < fileDataMatrix[i].length; j++) {
							if (fileDataMatrix[i][j].equals("L")) {
								drawnShapes.add(new Line(Integer.valueOf(fileDataMatrix[i][j + 1]),
										Integer.valueOf(fileDataMatrix[i][j + 2]),
										Integer.valueOf(fileDataMatrix[i][j + 3]),
										Integer.valueOf(fileDataMatrix[i][j + 4])));
							} else if (fileDataMatrix[i][j].equals("R")) {
								int startX = Integer.valueOf(fileDataMatrix[i][j + 1]);
								int startY = Integer.valueOf(fileDataMatrix[i][j + 2]);
								int width = Integer.valueOf(fileDataMatrix[i][j + 3]);
								int height = Integer.valueOf(fileDataMatrix[i][j + 4]);

								Rectangle drawnRect = new Rectangle(startX, startY, width, height);
								drawnRect.setTopLeftCorner(new int[] { startX, startY + height });
								drawnRect.setTopRightCorner(new int[] { startX + width, startY + height });
								drawnRect.setBottomLeftCorner(new int[] { startX, startY });
								drawnRect.setBottomRightCorner(new int[] { startX + width, startY });

								drawnShapes.add(drawnRect);
							}
						}
					}
				}
			} else if (arg0.getActionCommand().equals("Save")) {
				JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir") + "/Resources"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fileChooser.setFileFilter(filter);
				String loadedFile = "";
				int returnValue = fileChooser.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String selectedFilePath = selectedFile.getPath();
					if (!selectedFilePath.endsWith(".txt")) {
						selectedFile = new File(selectedFilePath + ".txt");
					}
					loadedFile = "Resources/" + selectedFile.getName();
				}
				FileWriter fw = null;
				try {
					fw = new FileWriter(loadedFile, false);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw);
					for (int i = 0; i < drawnShapes.size(); i++) {
						if (i == drawnShapes.size() - 1) {
							out.write(drawnShapes.get(i).toString());
						} else {
							out.write(drawnShapes.get(i).toString() + "\n");
						}
					}
					out.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (arg0.getActionCommand().equals("Exit")) {
				statusLabel.setText("Exiting program...");
				System.exit(0);

			}
			repaint();

		}

		@Override
		public void mousePressed(MouseEvent e) {

			startX = e.getX();
			startY = e.getY();

			// initialize lastX, lastY
			lastX = startX;
			lastY = startY;

			repaint();

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (!e.isControlDown()) {
				// Implement rubber-band cursor
				Graphics g = drawingPanel.getGraphics();
				g.setColor(Color.black);
				g.setXORMode(drawingPanel.getBackground());

				if (shapeToDraw.equals("Line")) {
					// REDRAW the line that was drawn
					// most recently during this drag
					// XOR mode means that yellow pixels turn black
					// essentially erasing the existing line
					g.drawLine(startX, startY, lastX, lastY);

					// draw line to current mouse position
					// XOR mode: yellow pixels become black
					// black pixels, like those from existing lines, temporarily become
					// yellow
					g.drawLine(startX, startY, e.getX(), e.getY());
					lastX = e.getX();
					lastY = e.getY();

				} else if (shapeToDraw.equals("Rectangle")) {
					g.drawRect(Math.min(startX, lastX), Math.min(startY, lastY), Math.abs(lastX - startX),
							Math.abs(lastY - startY));

					g.drawRect(Math.min(startX, e.getX()), Math.min(startY, e.getY()), Math.abs(e.getX() - startX),
							Math.abs(e.getY() - startY));
					lastX = e.getX();
					lastY = e.getY();
				}
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!e.isControlDown()) {
				if (shapeToDraw.equals("Line")) {
					drawnShapes.add(new Line(startX, startY, lastX, lastY));
				} else if (shapeToDraw.equals("Rectangle")) {
					Rectangle drawnRect = new Rectangle(Math.min(startX, lastX), Math.min(startY, lastY),
							Math.abs(lastX - startX), Math.abs(lastY - startY));
					drawnRect.setTopLeftCorner(new int[] { Math.min(startX, lastX), Math.min(startY, lastY) });
					drawnRect.setTopRightCorner(new int[] { Math.max(startX, lastX), Math.min(startY, lastY) });
					drawnRect.setBottomLeftCorner(new int[] { Math.min(startX, lastX), Math.max(startY, lastY) });
					drawnRect.setBottomRightCorner(new int[] { Math.max(startX, lastX), Math.max(startY, lastY) });

					drawnShapes.add(drawnRect);
				}
				repaint();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.isControlDown()) {
				for (Shape s : drawnShapes) {
					if (s.contains(e.getX(), e.getY())) {
						drawnShapes.remove(s);
						break;
					}
				}

			}
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}