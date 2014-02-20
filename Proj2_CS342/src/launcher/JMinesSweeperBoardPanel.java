package launcher;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.*;
import logic.MinesGame;

public class JMinesSweeperBoardPanel extends JPanel{
	private final int numRows, numCols;
	private final int numBombs;
	private final int buttonSize;
	private final int pixSpacing;
	private final int pWidth, pHeight;
	private MineButton board[][];
	private KeyHandler mouseListener;
	
	
	
	public JMinesSweeperBoardPanel(){
		super();
		numRows = 10;
		numCols = 10;
		numBombs = 10;
		buttonSize = 50;
		pixSpacing = 1; // zero or greater
		pWidth = (buttonSize + pixSpacing) * numCols + pixSpacing;
		pHeight = (buttonSize + pixSpacing) * numRows + pixSpacing;
		mouseListener = new KeyHandler();
		board = new MineButton[numCols][numRows];// (x, y)
		MineButton.setGame(new MinesGame(numRows, numCols, numBombs, this));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(pWidth, pHeight));
		addSquares();
	}
	
	private void addSquares(){
		MineButton aButton;// deriving instance later?
		int xLoc, yLoc;
		for(int y = 0; y < numRows; y++){
			yLoc = pixSpacing + (buttonSize + pixSpacing) * y;
			for(int x = 0; x < numCols; x++){
				aButton = new MineButton(x, y);
				aButton.addMouseListener(mouseListener);
				xLoc = pixSpacing + (buttonSize + pixSpacing) * x;
				aButton.setLocation(xLoc, yLoc); // relative location to msBoardPanel
				aButton.setSize(buttonSize, buttonSize);
				aButton.setMargin(new Insets(5, 5, 5, 5));// centers text
				board[x][y] = aButton;
				this.add(aButton);
			}
		}
	}
	
	public void openSpot(int x, int y, int val){
		board[x][y].open();
	}
}
