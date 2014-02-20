package launcher;

import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import logic.MinesGame;


public class MainFrame extends JFrame{
	
	private JMenuBar menuBar = null;
	
	private JMenu menuGame = null;
	private JMenuItem resetItem = null;
	private JMenuItem toptenItem = null;
	private JMenuItem exitItem = null;
	
	private JMenu menuHelp = null;
	private JMenuItem helpItem = null;
	private JMenuItem aboutItem = null;
	
	private String endString = "Do you want to quit the Game?";
	private String helpString = "Help?"; 
	private String infoString = "CS342 Project 2";
	
	public MainFrame(String title){
		super(title);
		initMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//frame Icon
		MediaTracker tracker = new MediaTracker(this);
		URL temp = MainFrame.class.getClassLoader().getResource("images/reset.gif");//top left image
		Image img = Toolkit.getDefaultToolkit().getImage(temp);
		tracker.addImage(img, 0);
		setIconImage(img);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		this.add(new JMinesSweeperBoardPanel());
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	/* [ActionListener] */
	class newActionListener implements ActionListener {//when choose "Reset" on the menuBar
		public void actionPerformed(ActionEvent e){
			resetGame(); 
		}
	}
	
	class bectTimeActionListener implements ActionListener { //for TopTen
		public void actionPerformed(ActionEvent e){
			//will do later
		}
	}
	
	class exitActionListener implements ActionListener { //for exit menu
		public void actionPerformed(ActionEvent e){
			int val;
			val = JOptionPane.showConfirmDialog(MainFrame.this,endString, "Game End",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(val == JOptionPane.YES_OPTION) System.exit(0);
		}
	}
	
	class dialogActionListener implements ActionListener{ 
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == helpItem)
				JOptionPane.showMessageDialog(MainFrame.this, helpString,"Help", JOptionPane.INFORMATION_MESSAGE);
			else if(e.getSource() == aboutItem)
				JOptionPane.showMessageDialog(MainFrame.this, infoString, "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public void resetGame(){
		//remove game
		//set new game
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	
	
	//member method
	public void initMenu(){
		menuBar = new JMenuBar();
		menuGame = new JMenu("Game");
		resetItem = new JMenuItem("Reset");
		toptenItem = new JMenuItem("Top ten");
		exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		menuHelp = new JMenu("Help");
		helpItem = new JMenuItem("Help");
		aboutItem = new JMenuItem("About");
		
		exitItem.addActionListener(new exitActionListener());
		
		menuGame.add(resetItem);
		menuGame.add(toptenItem);
		menuGame.add(exitItem);
		menuHelp.add(helpItem);
		menuHelp.add(aboutItem);
		
		menuBar.add(menuGame);
		menuBar.add(menuHelp);
		
		//toptenItem.addActionListener(new toptenActionListener());
		//resetItem.addActionListener(new resetActionListener());
		aboutItem.addActionListener(new dialogActionListener());
		helpItem.addActionListener(new dialogActionListener());
		
		this.setJMenuBar(menuBar);
	}

	
	public static void main(String args[])
	{
		new MainFrame("Mine Sweeper");
	}
}
