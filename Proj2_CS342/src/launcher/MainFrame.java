package launcher;

import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


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
	
	public MainFrame(String title){
		super(title);
		initMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frame Icon
		MediaTracker tracker = new MediaTracker(this);
		URL temp = MainFrame.class.getClassLoader().getResource("images/reset.gif");//top left image
		Image img = Toolkit.getDefaultToolkit().getImage(temp);
		tracker.addImage(img, 0);
		setIconImage(img);
		this.add(new JMinesSweeperBoardPanel());
		this.pack();
		this.setVisible(true);
		
		
	}
	
	//member method
	public void initMenu(){
		menuBar = new JMenuBar();
		menuGame = new JMenu("Game");
		resetItem = new JMenuItem("Reset");
		toptenItem = new JMenuItem("Top ten");
		exitItem = new JMenuItem("eXit");
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
		
		
		this.setJMenuBar(menuBar);
	}

	public static void main(String args[])
	{
		new MainFrame("Mine Sweeper");
	}
}
