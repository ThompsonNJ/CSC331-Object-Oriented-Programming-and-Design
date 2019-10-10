package nickThompsonFinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class PaintShop extends JFrame implements ActionListener {

	protected JPanel paintDisplayPanel;
	protected JTextField statusBar;

	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();

		if (s.equals("Exit")) {
			System.exit(0);
		} else if (s.equals("Open")) {
			statusBar.setText("User selected File/Open...");
			JFileChooser jfc = new JFileChooser("Resources/");
			int retVal = jfc.showOpenDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getPath();
				statusBar.setText("Executing method readPaintData....");
				
				readPaintData(fileName);
			} else {
				statusBar.setText("User canceled file open");
			}
		}

	}

	protected abstract void readPaintData(String fileName);

	public PaintShop() {
		setTitle("Paint Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		paintDisplayPanel = makePaintPanel();

		cp.add(paintDisplayPanel, BorderLayout.CENTER);

		statusBar = new JTextField();
		cp.add(statusBar, BorderLayout.SOUTH);

		buildMenu();

		pack();
		setVisible(true);
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());

		setJMenuBar(menuBar);
	}

	private JMenu createFileMenu() {
		JMenu m = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		m.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		m.add(menuItem);

		return m;
	}

	private JPanel makePaintPanel() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 400));
		p.setBackground(Color.YELLOW);

		return p;
	}

}
