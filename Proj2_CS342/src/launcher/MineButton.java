package launcher;

import javax.swing.JButton;

import logic.MinesGame;

public class MineButton extends JButton{
	private static MinesGame theGame;
	private final int xPos, yPos;

	public MineButton(int x, int y) {
		super();
		xPos = x;
		yPos = y;
	}

	public static void setGame(MinesGame temp) {
		theGame = temp;
	}

	public int getValue() {
		return theGame.getValue(xPos, yPos);
	}
}
