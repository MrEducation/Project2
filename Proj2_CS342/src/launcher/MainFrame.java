package launcher;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MainFrame extends JFrame implements ActionListener {
	private JMinesSweeperBoardPanel mineGUI;

	private JMenuBar menuBar = null;

	private JMenu menuGame = null;
	private JMenuItem resetItem = null;
	private JMenuItem toptenItem = null;
	private JMenuItem exitItem = null;

	private JMenu menuHelp = null;
	private JMenuItem helpItem = null;
	private JMenuItem aboutItem = null;

	private JLabel timerText;
	private Timer timer;
	private int timeElapsed;

	private String endString = "Do you want to quit the Game?";
	private String helpString = "Help?";
	private String infoString = "CS342 Project 2";

	private static class Score {
		public String name;// won't be used outside MainFrame class
		public int time;// won't be used outside MainFrame class

		public Score(int t, String n) {
			name = n;
			time = t;
		}

		public int compareTo(Score other) {
			return this.time - other.time;
		}
	}

	private static ArrayList<Score> scores;

	public static void main(String args[]) {
		File temp = new File("Scores.txt");
		scores = new ArrayList<Score>();
		// System.out.println(temp.getAbsolutePath());
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(temp));
			int i = 0;
			for (String t = reader.readLine(); t != null; t = reader.readLine()) {
				StringTokenizer st = new StringTokenizer(t);
				while (st.hasMoreTokens()) {
					// System.out.println(Integer.parseInt(st.nextToken()) + " " + i++);
					// System.out.println(st.nextToken() + " " + i++);
					scores.add(new Score(Integer.parseInt(st.nextToken()), st.nextToken()));
				}
			}
		} catch (FileNotFoundException e) {

			try {
				writer = new BufferedWriter(new FileWriter(temp));
				for (int i = 0; i < 10; i++) {
					writer.write("9999    Computer\n");
					scores.add(new Score(9999, "Computer"));
				}
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		new MainFrame("Mine Sweeper");
	}

	public MainFrame(String title) {
		super(title);
		this.setLayout(new BorderLayout());
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		initMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// frame
		URL temp = MainFrame.class.getClassLoader().getResource(
				"images/gnomine_1.png");// top left image
		Image img = Toolkit.getDefaultToolkit().getImage(temp);
		setIconImage(img);

		JPanel infoBar = new JPanel();
		infoBar.setLayout(new FlowLayout());
		timerText = new JLabel();
		timeElapsed = 0;
		timerText.setText("" + timeElapsed);
		infoBar.add(timerText);

		mineGUI = new JMinesSweeperBoardPanel();
		this.add(infoBar, BorderLayout.NORTH);
		this.add(mineGUI);// MAKE SURE THIS IS ADDED LAST!!!!!!!!
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		timer = new Timer(1000, this);
		timer.start();
	}

	public void resetGame() {
		this.remove(mineGUI);
		mineGUI = new JMinesSweeperBoardPanel();
		this.add(mineGUI);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	// member method
	public void initMenu() {
		menuBar = new JMenuBar();
		menuGame = new JMenu("Game");
		menuGame.setMnemonic(KeyEvent.VK_M);
		resetItem = new JMenuItem("Reset");
		toptenItem = new JMenuItem("Top ten");
		exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		menuHelp = new JMenu("Help");
		helpItem = new JMenuItem("Help");
		aboutItem = new JMenuItem("About");

		menuGame.add(resetItem);
		menuGame.add(toptenItem);
		menuGame.add(exitItem);
		menuHelp.add(helpItem);
		menuHelp.add(aboutItem);

		menuBar.add(menuGame);
		menuBar.add(menuHelp);

		// toptenItem.addActionListener(new toptenActionListener());
		resetItem.addActionListener(this);
		aboutItem.addActionListener(this);
		helpItem.addActionListener(this);
		exitItem.addActionListener(this);

		this.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e) {
		int val;
		if (e.getSource() == helpItem)
			JOptionPane.showMessageDialog(this, helpString, "Help",
					JOptionPane.INFORMATION_MESSAGE);
		else if (e.getSource() == aboutItem)
			JOptionPane.showMessageDialog(this, infoString, "Information",
					JOptionPane.INFORMATION_MESSAGE);
		else if (e.getSource() == exitItem) {
			val = JOptionPane.showConfirmDialog(this, endString, "Game End",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (val == JOptionPane.YES_OPTION)
				System.exit(0);
		} else if (e.getSource() == resetItem) {
			resetGame();
		} else if (e.getSource() == timer) {
			timerText.setText("" + ++timeElapsed);
		}
	}
}
