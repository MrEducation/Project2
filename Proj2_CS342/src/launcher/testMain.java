package launcher;

import javax.swing.JFrame;

public class testMain extends JFrame{
	
	public static void main(String args[]){
		testMain temp = new testMain();
		temp.setVisible(true);
	}
	
	public testMain(){
		super("Hello");
		this.setSize(200, 100);
	}
}
