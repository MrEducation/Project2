package launcher;

import java.net.URL;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class MainFrame extends JFrame implements ActionListener{
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
	
	public MainFrame(String title){
		super(title);
		this.setLayout(new BorderLayout());
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		initMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//frame 
		URL temp = MainFrame.class.getClassLoader().getResource("images/mine2.gif");//top left image
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
	
	class bectTimeActionListener implements ActionListener { //for TopTen
		public void actionPerformed(ActionEvent e){
			//will do later
		}
	}
	
	public void resetGame(){
		this.remove(mineGUI);
		mineGUI = new JMinesSweeperBoardPanel();
		this.add(mineGUI);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	//member method
	public void initMenu(){
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
		
		exitItem.addActionListener(this);
		
		menuGame.add(resetItem);
		menuGame.add(toptenItem);
		menuGame.add(exitItem);
		menuHelp.add(helpItem);
		menuHelp.add(aboutItem);
		
		menuBar.add(menuGame);
		menuBar.add(menuHelp);

		//toptenItem.addActionListener(new toptenActionListener());
		resetItem.addActionListener(this);
		aboutItem.addActionListener(this);
		helpItem.addActionListener(this);
		
		
		this.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e){
		int val;
		if(e.getSource() == helpItem)
			JOptionPane.showMessageDialog(this, helpString,"Help", JOptionPane.INFORMATION_MESSAGE);
		else if(e.getSource() == aboutItem)
			JOptionPane.showMessageDialog(this, infoString, "Information", JOptionPane.INFORMATION_MESSAGE);
		else if (e.getSource() == exitItem){
			val = JOptionPane.showConfirmDialog(this,endString, "Game End",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(val == JOptionPane.YES_OPTION) System.exit(0);
		} else if (e.getSource() == resetItem){
			resetGame();
		} else if (e.getSource() == timer){
			timerText.setText("" + ++timeElapsed);
		}
	}
	
	public static void main(String args[])
	{
		new MainFrame("Mine Sweeper");
	}
}
