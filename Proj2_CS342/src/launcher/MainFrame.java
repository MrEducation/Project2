package launcher;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import logic.MinesGame;

/**************************************************************
//TODO Reset button and the number of bombs left in top panel 
***************************************************************/

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
	
	private static JLabel flagCountText;
	private JLabel timerText;
	private Timer timer;
	private float timeElapsed;

	private String endString = "Do you want to quit the Game?";
	private String helpString = "Click into the minefield to expose free space.\n"
			+ "Right-click to place flags";
	private String infoString = "CS342 Project 2";

	private static int numFlagToMark;
	private static final String filename = "Scores.txt";
	private static final File scoresFile = new File(filename);
	private static ArrayList<Score> scores;

	private static class Score {
		public String name;// won't be used outside MainFrame class
		public float time;// won't be used outside MainFrame class

		public Score(float t, String n) {
			name = n;
			time = t;
		}

		public int compareTo(Score other) {
			if (this.time < other.time)
				return -1;
			else if (this.time > other.time)
				return 1;
			else return 0;
		}
		
		public String toString(){
			return String.format("%.2f",time) + "    " + name;
		}
		
	}
	
	private static String scoresAsString(){
		if (scores == null)
			return "";
		String temp = "";
		for (int i = 0; i < scores.size(); ++i){
			temp += (i + 1) + ".) " + scores.get(i).toString() + '\n';
		}
		return temp;
	}

	public static void main(String args[]) {
		scores = new ArrayList<Score>();
		// System.out.println(temp.getAbsolutePath());
		BufferedReader reader = null;
		BufferedWriter writer = null;
		numFlagToMark = 10;
		try {
			reader = new BufferedReader(new FileReader(scoresFile));
			for (String t = reader.readLine(); t != null; t = reader.readLine()) {
				StringTokenizer st = new StringTokenizer(t);
				while (st.hasMoreTokens()) {
					scores.add(new Score(Float.parseFloat(st.nextToken()), st.nextToken()));
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			try {
				writer = new BufferedWriter(new FileWriter(scoresFile));
				for (int i = 0; i < 10; i++) {
					writer.write("9999.0    Computer\n");
					scores.add(new Score(9999.0f, "Computer"));
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
	
	public static void changeFlagCount(boolean isAdding){
		if (isAdding)
			--numFlagToMark;
		else ++numFlagToMark;
		flagCountText.setText("Bombs Left: " + numFlagToMark + "      ");
			
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
		flagCountText = new JLabel();
		flagCountText.setText("Bombs Left: " + numFlagToMark + "      ");
		initMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// frame
		URL temp = MainFrame.class.getClassLoader().getResource("images/gnomine_1.png");// top left image
		Image img = Toolkit.getDefaultToolkit().getImage(temp);
		setIconImage(img);

		JPanel infoBar = new JPanel();
		infoBar.setLayout(new FlowLayout());
		timerText = new JLabel();
		timeElapsed = 0;
		timerText.setText("" + timeElapsed);
		infoBar.add(flagCountText);
		infoBar.add(timerText);

		mineGUI = new JMinesSweeperBoardPanel(this);
		this.add(infoBar, BorderLayout.NORTH);
		this.add(mineGUI);// MAKE SURE THIS IS ADDED LAST!!!!!!!!
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		timer = new Timer(50, this);
		timer.start();
	}

	public void resetGame() {
		MineButton.reset();
		this.remove(mineGUI);
		timer.restart();
		timeElapsed = 0;
		this.timerText.setText("0.00");
		mineGUI = new JMinesSweeperBoardPanel(this);
		this.add(mineGUI);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private static void writeToFile(){
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(scoresFile));
			for (int i = 0; i < 10; i++) {
				writer.write(scores.get(i).toString() + '\n');
			}
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void handleEndGame(boolean hasWon){
		
		int val;
		String name = null;
		if (hasWon){
			timer.stop();// pause timer
			if (timeElapsed < scores.get(9).time)
				name = JOptionPane.showInputDialog(this, "New High: " +
						String.format("%.2f",timeElapsed) + ". What's your name?");
			if (name != null && !name.isEmpty()){
				Score temp = new Score(timeElapsed, name);
				int i = 9;
				for (; i >= 0 && temp.compareTo(scores.get(i)) < 0; --i);
				scores.add(i + 1, temp);
				scores.remove(10);
				//save highscore
				JOptionPane.showMessageDialog(this, scoresAsString(), "Scores",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			val = JOptionPane.showConfirmDialog(this, "Restart?", "Game Over",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (val == JOptionPane.YES_OPTION)
				resetGame();
			else{
				writeToFile();
				System.exit(0);
			}
		}else{
			timer.stop();// pause timer
			val = JOptionPane.showConfirmDialog(this, "You lost. Restart?", "Game Over",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (val == JOptionPane.YES_OPTION)
				resetGame();
			else{
				writeToFile();
				System.exit(0);
			}
		}
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
		toptenItem.addActionListener(this);

		this.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e) {
		int val;
		
		
		if (e.getSource() == helpItem){
			timer.stop();// pause timer
			JOptionPane.showMessageDialog(this, helpString, "Help",
					JOptionPane.INFORMATION_MESSAGE);
			timer.start();// pause timer
		} else if (e.getSource() == aboutItem){
			timer.stop();// pause timer
			JOptionPane.showMessageDialog(this, infoString, "Information",
					JOptionPane.INFORMATION_MESSAGE);
			timer.start();// pause timer
		} else if (e.getSource() == exitItem) {
			timer.stop();// pause timer
			val = JOptionPane.showConfirmDialog(this, endString, "Game End",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (val == JOptionPane.YES_OPTION)
				System.exit(0);
			timer.start();// pause timer
		} else if (e.getSource() == resetItem) {
			resetGame();
		} else if (e.getSource() == timer) {
			timeElapsed += 0.05;
			timerText.setText("" + String.format("%.2f",timeElapsed));
		}else if (e.getSource() == toptenItem) {
			timer.stop();// pause timer
			JOptionPane.showMessageDialog(this, scoresAsString(), "Scores",
					JOptionPane.INFORMATION_MESSAGE);
			timer.start();
		}
	}
}
