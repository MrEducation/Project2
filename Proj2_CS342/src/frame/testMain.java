package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;

public class testMain extends JFrame implements ActionListener{
	
	public static void main(String args[]){
		testMain temp = new testMain();
		temp.setVisible(true);
	}
	
	public testMain(){
		super("Minesweeper");
		getContentPane().setLayout(new BorderLayout());
		JMenuBar temp = new JMenuBar();
		JMenu menu = new JMenu("FirstMenu");
		temp.add(menu);
		menu.add(new JMenuItem("A text-only menu item",
                         KeyEvent.VK_T));
		this.add(temp, BorderLayout.PAGE_START);
		JPanel msBoard = new JPanel();
		msBoard.setLayout(null);//for custom layout
		JButton aButton;
		int buttonSize = 30, numBlocks, sideCount;
		numBlocks = 100;
		sideCount = (int)(Math.sqrt(numBlocks) + 0.001);//in case of weird round off error
		msBoard.setMinimumSize(new Dimension(buttonSize * sideCount, buttonSize * sideCount));
		msBoard.setBounds(0, 0, buttonSize * sideCount, buttonSize * sideCount);
		for(int i = 0; i < numBlocks; i++){
			aButton = new JButton("" + i);
			int xLoc = 1 + (buttonSize + 1) * (i % sideCount);
			int yLoc = 1 + (buttonSize + 1) * (i / sideCount);
			aButton.setLocation(xLoc, yLoc);
			aButton.setSize(buttonSize, buttonSize);
			//aButton.setBorder(null);
			//aButton.setMargin(new Insets(0, 0, 0, 0));
			aButton.setMargin(new Insets(5, 5, 5, 5));
			//aButton.setMargin(m);
			//aButton.setBounds(aButton.getBounds());
			msBoard.add(aButton);
			this.add(msBoard);
		}
		
		Insets ins = this.getInsets();
		System.out.println(ins);
		//this.setSize(ins.left + ins.right, ins.top + ins.bottom);
		this.setSize(sideCount * (buttonSize + 3), (sideCount + 1) * (buttonSize + 3));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//do a system.exit at end, i believe
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
