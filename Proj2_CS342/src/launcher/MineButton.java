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
	private static Image allImgs[] = new Image[12];
	
	private static boolean imgsIsSet = false;
	private static ImageIcon[] numImgList = null;
	private static ImageIcon[] mineImgList = null;
	private static ImageIcon flagImg 	= null;
	private static ImageIcon qImg 	= null;
	
	private boolean hasSetIcon = false, isOpen = false;
	private int imgIndex;
	private int markIndex = 0;// 0  = blank, 1 = flag, 2 = ?
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
		if (markIndex > 0){
			return;
		}
		getModel().setPressed(true);
		getModel().setEnabled(false);
		chooseIcon(true);
		theGame.openSpot(xPos, yPos);
	}
	
	public void toggle(){
		++markIndex;
		markIndex %= 3;
		if (markIndex != 0)
			getModel().setEnabled(false);
		else
			getModel().setEnabled(true);
		chooseIcon(false);
	}

	public static void setGame(MinesGame temp) {
		theGame = temp;
	}
	
	public void chooseIcon(boolean isOpening){// should be called on click (or pressed)
		if (markIndex == 0 && isOpening)
			imgIndex = getValue();// for now
		else if (markIndex == 0){
			imgIndex = 0;
		}else{
			imgIndex = 9 + markIndex;
		}
		//this.setPressedIcon(mineImgList[0]);//idk why dis doesn't work
		hasSetIcon = imgIndex > 0;
		this.repaint();
		//hasSetIcon = false;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (!hasSetIcon || imgIndex < 1)
			return;
		int x = this.getSize().width / 2 - allImgs[imgIndex].getWidth(null) / 2;
		int y = this.getSize().height / 2 - allImgs[imgIndex].getHeight(null) / 2;
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
		//System.out.println(mineImgList[0]);
		
		allImgs[9] = mineImgList[0].getImage();
		
		flagImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				temp.getResource("images/flag.gif")));
		allImgs[10] = flagImg.getImage();
		qImg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				temp.getResource("images/wildcard.gif")));
		allImgs[11] = qImg.getImage();
		
		imgsIsSet = true;
	}
}
