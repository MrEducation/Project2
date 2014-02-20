package logic;

import java.util.Random;

import launcher.JMinesSweeperBoardPanel;

public class MinesGame {
	private JMinesSweeperBoardPanel theGUI;
	private int numCols, numRows, numBombs;
	private int ansGrid[][];
	boolean usrGrid[][];
	int xForBombs[], yForBombs[];
	private final int bombValue;

	public MinesGame(int cols, int rows, int bombs, JMinesSweeperBoardPanel daGUI) {
		theGUI = daGUI;
		numCols = cols;
		numRows = rows;
		numBombs = bombs;
		ansGrid = new int[numCols][numRows];// initializes to zeros
		usrGrid = new boolean[numCols][numRows];// initializes to false
		System.out.println(usrGrid[0][0]);
		bombValue = getBombValue();// 9 allows aligned print
		genBoard();
		printBoard();
	}

	/**
	 * (0,0) in the top left, y ascends down
	 * 
	 * @param x The column
	 * @param y The row
	 * @return
	 */
	public int getValue(int x, int y){
		return ansGrid[x][y];
	}
	
	public static final int getBombValue(){
		return 9;
	}
	
	/**
	 * Returns if you lose
	 * 
	 * @param x xPos
	 * @param y yPos
	 * @return value at position
	 */
	public int openSpot(int x, int y){
		usrGrid[x][y] = true;
		theGUI.openSpot(x, y, ansGrid[x][y]);
		if (ansGrid[x][y] == 9){
			openBombs();
			return getBombValue();
		}
		if (ansGrid[x][y] == 0){
			openSurrounding(x, y);
		}
		return ansGrid[x][y];
	}
	
	private void openBombs(){
		for (int i = 0; i < numBombs; ++i){
			theGUI.openSpot(xForBombs[i], yForBombs[i], ansGrid[xForBombs[i]][yForBombs[i]]);
		}
	}

	private void genBoard() {
		xForBombs = new int[numBombs];
		yForBombs  = new int[numBombs];
		Random temp = new Random();
		for (int i = 0; i < numBombs; ++i) {
			xForBombs[i] = temp.nextInt(numBombs);
			yForBombs[i] = temp.nextInt(numBombs);
		}
		for (int i = 0; i < numBombs; ++i) {

			ansGrid[xForBombs[i]][yForBombs[i]] = bombValue;// bombs are negative 1
			setSurrounding(xForBombs[i], yForBombs[i]);
		}
	}

	private void printBoard() {
		for (int y = 0; y < numRows; ++y) {
			for (int x = 0; x < numCols; ++x) {
				System.out.print(ansGrid[x][y] + " ");
			}
			System.out.println();
		}
	}

	private boolean isValid(int x, int y) {
		if (x < 0 || y < 0)
			return false;
		if (x >= numCols || y >= numRows)
			return false;
		if (usrGrid[x][y] == true)
			return false;
		return true;
	}
	
	private void openSurrounding(int x, int y) {
		// checks out of bounds
		if (isValid(x - 1, y - 1)) {// top left
			openSpot(x - 1, y - 1);
		}
		if (isValid(x, y - 1)) {// top
			openSpot(x, y - 1);
		}
		if (isValid(x + 1, y - 1)) {// top right
			openSpot(x + 1, y - 1);
		}
		if (isValid(x + 1, y)) {// right
			openSpot(x + 1, y);
		}
		if (isValid(x + 1, y + 1)) {// bot right
			openSpot(x + 1, y + 1);
		}
		if (isValid(x, y + 1)) {// bot
			openSpot(x, y + 1);
		}
		if (isValid(x - 1, y + 1)) {// bot left
			openSpot(x - 1, y + 1);
		}
		if (isValid(x - 1, y)) {// left
			openSpot(x - 1, y);
		}
	}

	private void setSurrounding(int x, int y) {
		// checks out of bounds
		if (isValid(x - 1, y - 1)) {// top left
			if (ansGrid[x - 1][y - 1] != bombValue) {
				ansGrid[x - 1][y - 1] += 1;
			}
		}
		if (isValid(x, y - 1)) {// top
			if (ansGrid[x][y - 1] != bombValue) {
				ansGrid[x][y - 1] += 1;
			}
		}
		if (isValid(x + 1, y - 1)) {// top right
			if (ansGrid[x + 1][y - 1] != bombValue) {
				ansGrid[x + 1][y - 1] += 1;
			}
		}
		if (isValid(x + 1, y)) {// right
			if (ansGrid[x + 1][y] != bombValue) {
				ansGrid[x + 1][y] += 1;
			}
		}
		if (isValid(x + 1, y + 1)) {// bot right
			if (ansGrid[x + 1][y + 1] != bombValue) {
				ansGrid[x + 1][y + 1] += 1;
			}
		}
		if (isValid(x, y + 1)) {// bot
			if (ansGrid[x][y + 1] != bombValue) {
				ansGrid[x][y + 1] += 1;
			}
		}
		if (isValid(x - 1, y + 1)) {// bot left
			if (ansGrid[x - 1][y + 1] != bombValue) {
				ansGrid[x - 1][y + 1] += 1;
			}
		}
		if (isValid(x - 1, y)) {// left
			if (ansGrid[x - 1][y] != bombValue) {
				ansGrid[x - 1][y] += 1;
			}
		}
	}
}
