package nickThompsonFinal;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NickThompsonFinal extends PaintShop {
	private MouseController mCtrl = new MouseController();
	private ArrayList<Swatche> allSwatches = new ArrayList<Swatche>();
	private int rows, columns;

	public static void main(String[] args) {
		new NickThompsonFinal();
	}

	@Override
	protected void readPaintData(String fileName) {
		String fileData = "";

		try {
			fileData = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		String[] fileDataRows = fileData.replace("\r", "").split("\n");
		String[][] fileDataMatrix = new String[fileDataRows.length][];

		for (int i = 0; i < fileDataRows.length; i++) {
			fileDataMatrix[i] = fileDataRows[i].split(" ");
		}

		calcDim(fileDataRows.length);
		paintDisplayPanel.setLayout(new GridLayout(rows, columns));
		for (int i = 0; i < fileDataRows.length; i++) {
			for (int j = 0; j < fileDataMatrix[i].length; j += 6) {
				Swatche s = new Swatche();
				s.setName(fileDataMatrix[i][j]);
				s.setText(s.getName());
				s.setRedValue(Integer.valueOf(fileDataMatrix[i][j + 1]));
				s.setGreenValue(Integer.valueOf(fileDataMatrix[i][j + 2]));
				s.setBlueValue(Integer.valueOf(fileDataMatrix[i][j + 3]));
				s.setColor(new Color(s.getRedValue(), s.getGreenValue(), s.getBlueValue()));
				s.setBackground(s.getColor());
				s.setPrice(Double.valueOf(fileDataMatrix[i][j + 4]));
				s.setType(fileDataMatrix[i][j + 5]);
				s.addMouseListener(mCtrl);
				paintDisplayPanel.add(s);
				allSwatches.add(s);

			}

		}
		revalidate();
		repaint();

	}

	private void calcDim(int fileLength) {
		int limit = (int) (Math.sqrt(fileLength));
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for (int i = 1; i <= limit; i += 1) {
			if (fileLength % i == 0) {
				factors.add(i);
				if (i != fileLength / i) {
					factors.add(fileLength / i);
				}
			}
		}
		if (factors.size() % 2 == 0) {
			rows = factors.get(factors.size() - 1);
			columns = factors.get(factors.size() - 2);
		} else {
			rows = factors.get(factors.size() - 1);
			columns = factors.get(factors.size() - 1);
		}

	}

	private class MouseController implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent me) {
			// TODO Auto-generated method stub
			Swatche s = (Swatche) me.getSource();
			s.setText("$" + s.getPrice() + ", " + s.getType());

		}

		@Override
		public void mouseExited(MouseEvent me) {
			// TODO Auto-generated method stub
			for (Swatche s : allSwatches) {
				s.setText(s.getName());

			}

		}

		@Override
		public void mousePressed(MouseEvent me) {
			// TODO Auto-generated method stub
			Swatche clickedSwatche = (Swatche) me.getSource();
			Swatche closestSwatche = allSwatches.get(0);
			for (Swatche s : allSwatches) {
				if (clickedSwatche.findDistance(s) < closestSwatche.findDistance(clickedSwatche)
						&& !clickedSwatche.equals(s)) {
					closestSwatche = s;

				}

			}
			clickedSwatche.setText("MATCH");
			closestSwatche.setText("MATCH");

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
