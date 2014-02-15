package logic;

import java.util.Random;

public class MinesGame {
	private int numCols, numRows, numBombs;
	private int grid[][];
	private final int bombValue;

	public MinesGame(int cols, int rows, int bombs) {
		numCols = cols;
		numRows = rows;
		numBombs = bombs;
		grid = new int[numCols][numRows];// initializes to zeros
		bombValue = 9;// 9 allows aligned print
		genBoard();
		//printBoard();
	}

	/**
	 * (0,0) in the top left, y ascends down
	 * 
	 * @param x The column
	 * @param y The row
	 * @return
	 */
	public int getValue(int x, int y){
		return grid[x][y];
	}

	private void genBoard() {
		int xForBombs[] = new int[numBombs];
		int yForBombs[] = new int[numBombs];
		Random temp = new Random();
		for (int i = 0; i < numBombs; ++i) {
			xForBombs[i] = temp.nextInt(numBombs);
			yForBombs[i] = temp.nextInt(numBombs);
		}
		for (int i = 0; i < numBombs; ++i) {

			grid[xForBombs[i]][yForBombs[i]] = bombValue;// bombs are negative 1
			setSurrounding(xForBombs[i], yForBombs[i]);
		}
	}

	private void printBoard() {
		for (int x = 0; x < numCols; ++x) {
			for (int y = 0; y < numRows; ++y) {
				System.out.print(grid[x][y] + " ");
			}
			System.out.println();
		}
	}

	private boolean isValid(int x, int y) {
		if (x < 0 || y < 0)
			return false;
		if (x >= numCols || y >= numRows)
			return false;
		return true;
	}

	private void setSurrounding(int x, int y) {
		// checks out of bounds
		if (isValid(x - 1, y - 1)) {// top left
			if (grid[x - 1][y - 1] != bombValue) {
				grid[x - 1][y - 1] += 1;
			}
		}
		if (isValid(x, y - 1)) {// top
			if (grid[x][y - 1] != bombValue) {
				grid[x][y - 1] += 1;
			}
		}
		if (isValid(x + 1, y - 1)) {// top right
			if (grid[x + 1][y - 1] != bombValue) {
				grid[x + 1][y - 1] += 1;
			}
		}
		if (isValid(x + 1, y)) {// right
			if (grid[x + 1][y] != bombValue) {
				grid[x + 1][y] += 1;
			}
		}
		if (isValid(x + 1, y + 1)) {// bot right
			if (grid[x + 1][y + 1] != bombValue) {
				grid[x + 1][y + 1] += 1;
			}
		}
		if (isValid(x, y + 1)) {// bot
			if (grid[x][y + 1] != bombValue) {
				grid[x][y + 1] += 1;
			}
		}
		if (isValid(x - 1, y + 1)) {// bot left
			if (grid[x - 1][y + 1] != bombValue) {
				grid[x - 1][y + 1] += 1;
			}
		}
		if (isValid(x - 1, y)) {// left
			if (grid[x - 1][y] != bombValue) {
				grid[x - 1][y] += 1;
			}
		}
	}
}
