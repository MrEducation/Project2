package launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import logic.MinesGame;

public class MineButton extends JButton{
	private static MinesGame theGame;
	//top left image
	private static final ClassLoader temp =	MineButton.class.getClassLoader();
	private static final Image Flag = Toolkit.getDefaultToolkit().getImage(
			temp.getResource("images/flag.GIF"));;
	private static final Image Bomb = Toolkit.getDefaultToolkit().getImage(
			temp.getResource("images/mine1.gif"));
	private static final Image Question = Toolkit.getDefaultToolkit().getImage(
			temp.getResource("images/wildcard.gif"));
	private static final Image imgs[] = {Bomb, Flag, Question};
	private int imgNum;
	private final int xPos, yPos;

	public MineButton(int x, int y) {
		super();
		imgNum = -1;
		xPos = x;
		yPos = y;
	}
	
	public void open(){
		theGame.openSpot(xPos, yPos);
	}

	public static void setGame(MinesGame temp) {
		theGame = temp;
	}
	
	public void chooseIcon(){// should be called on click (or pressed)
		if (getValue() == MinesGame.getBombValue()){
			this.setPressedIcon(new ImageIcon(Bomb));//idk why dis doesn't work
			imgNum = 0;
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (imgNum >= 0)
			g.drawImage(Bomb, 0, 0, this);
	}
	
	public int getValue() {
		return theGame.getValue(xPos, yPos);
	}
}
