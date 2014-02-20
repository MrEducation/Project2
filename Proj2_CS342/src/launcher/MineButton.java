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

	/*imgIcon field*/
	private static Image allImgs[] = new Image[10];
	
	private static boolean imgsIsSet = false, hasSetIcon = false;
	private static ImageIcon[] numImgList = null;
	private static ImageIcon[] mineImgList = null;
	private static ImageIcon	flagImg 	= null;
	
	private int imgIndex;
	private final int xPos, yPos;

	public MineButton(int x, int y) {
		super();
		imgIndex = -1;
		xPos = x;
		yPos = y;
		if (!imgsIsSet)
			setImg();
	}
	
	public void open(){
		theGame.openSpot(xPos, yPos);
		chooseIcon();
	}

	public static void setGame(MinesGame temp) {
		theGame = temp;
	}
	
	public void chooseIcon(){// should be called on click (or pressed)
		imgIndex = getValue();// for now
		//this.setPressedIcon(mineImgList[imgIndex]);//idk why dis doesn't work
		
		hasSetIcon = true;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (!hasSetIcon || imgIndex < 1)
			return;
		int x = this.getPreferredSize().width / 2 - allImgs[imgIndex].getWidth(this) / 2;
		int y = this.getPreferredSize().height / 2 - allImgs[imgIndex].getHeight(this) / 2;
		if (imgIndex > 0)
			g.drawImage(allImgs[imgIndex], x, y, this);
	}
	
	public int getValue() {
		return theGame.getValue(xPos, yPos);
	}	
	
	/*image loading method*/
	private static void setImg(){
		ClassLoader temp =	MineButton.class.getClassLoader();
		numImgList = new ImageIcon[8];
		
		for(int i=1; i<8; i++){
			numImgList[i] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
					temp.getResource("images/"+ i +"s.gif")));
			allImgs[i] = numImgList[i].getImage();
		}
		
		mineImgList = new ImageIcon[2];
		
		mineImgList[0] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				temp.getResource("images/mine1.gif")));
		mineImgList[1] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				temp.getResource("images/mine2.gif")));
		System.out.println(mineImgList[0]);
		
		allImgs[9] = mineImgList[0].getImage(); 
		
		flagImg = new ImageIcon("images/flag.gif");
		imgsIsSet = true;
	}
}
