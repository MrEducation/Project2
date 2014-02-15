package launcher;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JMinesSweeperBoardPanel extends JPanel  implements ActionListener{
	private final int numRows, numCols;
	private final int buttonSize;
	private final int pixSpacing;
	private final int pWidth, pHeight;
	private JButton board[][];
	
	public JMinesSweeperBoardPanel(){
		super();
		numRows = 10;
		numCols = 10;
		buttonSize = 50;
		pixSpacing = 1; // zero or greater
		pWidth = (buttonSize + pixSpacing) * numCols + pixSpacing;
		pHeight = (buttonSize + pixSpacing) * numRows + pixSpacing;
		board = new JButton[numCols][numRows];// (x, y)
		this.setLayout(null);
		this.setPreferredSize(new Dimension(pWidth, pHeight));
		addSquares();
	}
	
	private void addSquares(){
		JButton aButton;// deriving instance later?
		int xLoc, yLoc;
		for(int y = 0; y < numRows; y++){
			yLoc = pixSpacing + (buttonSize + pixSpacing) * y;
			for(int x = 0; x < numCols; x++){
				aButton = new JButton("(" + y + ", " + x + ")");
				xLoc = pixSpacing + (buttonSize + pixSpacing) * x;
				aButton.setLocation(xLoc, yLoc); // relative location to msBoardPanel
				aButton.setSize(buttonSize, buttonSize);
				aButton.setMargin(new Insets(5, 5, 5, 5));// centers text
				board[x][y] = aButton;
				this.add(aButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
