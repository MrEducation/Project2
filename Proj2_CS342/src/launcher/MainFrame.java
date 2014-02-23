package launcher;

import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import logic.MinesGame;


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
	
	private String endString = "Do you want to quit the Game?";
	private String helpString = "Help?"; 
	private String infoString = "CS342 Project 2";
	
	public MainFrame(String title){
		super(title);
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		initMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//frame Icon
		MediaTracker tracker = new MediaTracker(this);
		URL temp = MainFrame.class.getClassLoader().getResource("images/reset.gif");//top left image
		Image img = Toolkit.getDefaultToolkit().getImage(temp);
		tracker.addImage(img, 0);
		setIconImage(img);
		mineGUI = new JMinesSweeperBoardPanel(); 
		this.add(mineGUI);// MAKE SURE THIS IS ADDED LAST!!!!!!!!
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		
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
		resetItem.addActionListener(new newActionListener());
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
		}
	}
	
	public static void main(String args[])
	{
		//setupMenuKey(new MainFrame("Mine Sweeper"));
		new MainFrame("Mine Sweeper");
	}



//	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
