package launcher;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeyHandler extends MouseAdapter {
	private boolean isDwn[] = new boolean[2];// to use for two mouse press together
	private boolean hasClicked[] = new boolean[2];// to be reset at check
	
	
	@Override
	public void mouseClicked(MouseEvent e) {//combination of press release
		MineButton temp = (MineButton)e.getSource();
		System.out.println("The vlaue under the button: " + temp.getValue());
		if (e.getButton() == MouseEvent.BUTTON1){
			System.out.println("Left, clicked");
		}
		if (e.getButton() == MouseEvent.BUTTON3){
			System.out.println("Right, clicked");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {//combination of press release
		if (e.getButton() == MouseEvent.BUTTON1){
			System.out.println("Left, pressed");
		}
		if (e.getButton() == MouseEvent.BUTTON3){
			System.out.println("Right, pressed");
			isDwn[0] = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {//combination of press release
		if (e.getButton() == MouseEvent.BUTTON1){
			System.out.println("Left, released");
		}
		if (e.getButton() == MouseEvent.BUTTON3){
			System.out.println("Right, released");
			isDwn[0] = false;
			hasClicked[0] = true;
		}
	}
	
	/**
	 * Returns true if both are down
	 * 
	 * @return True if both are down
	 */
	public boolean isBothDown(){
		return isDwn[0] && isDwn[1];
	}

}
