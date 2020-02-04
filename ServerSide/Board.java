/**
 * Board class defines the dimensions and structure of the board before
 * displaying it. Also checks for winners to the game.
 * 
 * @author arsal
 *
 */
public class Board implements Constants {
	/**
	 * Declares two-dimensional array to represent the board
	 */
	private char theBoard[][];
	/**
	 * Declares integer variable to count the number of marks on the board
	 */
	private int markCount;
	/**
	 * Constructor for Board class creates a 3x3 array with each element assigned as an empty space character
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	/**
	 * Returns the mark at a certain position of theBoard
	 * @param row
	 * @param col
	 * @return
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	/**
	 * Returns true if the amount of marks on the Board is 9, indicating a full board
	 * @return
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	/**
	 * Uses private method to check if player X has won, and returns true if that is the case
	 * @return
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}
	/**
	 * Uses private method to check if player O has won, and returns true if that is the case
	 * @return
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	/**
	 * Defines the structure of the board that is printed to the screen
	 */
	public String display() {
		
		String s = displayColumnHeaders();
		s += addHyphens();
		for (int row = 0; row < 3; row++) {
			s += addSpaces();
			s += "    row " + row + " ";
			for (int col = 0; col < 3; col++)
				s += "|  " + getMark(row, col) + "  ";
			s += "|\n" + addSpaces() + addHyphens();
		}
		return s;
	}
	/**
	 * Adds a mark, which is a character type passed as an argument, to a certain position of theBoard
	 * @param row
	 * @param col
	 * @param mark
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}
	/**
	 * Clears theBoard by making every position a space character once again
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	/**
	 * Checks each row, column, and diagonal of the board for the same mark to determine if there is a winner.
	 * Returns 1 if a winner is found, 0 otherwise.
	 * @param mark
	 * @return
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	/**
	 * Structure of the board's column headers
	 */
	String displayColumnHeaders() {
		String s = "          ";
		for (int j = 0; j < 3; j++)
			s += "|col " + j;
		s += "\n";
		return s;
	}
	/**
	 * Structure of the rows of the board
	 */
	String addHyphens() {
		String s = "          ";
		for (int j = 0; j < 3; j++)
			s += "+-----";
		s += "+\n";
		return s;
	}
	/** 
	 * Creates spaces between columns of the board
	 */
	String addSpaces() {
		String s = "          ";
		for (int j = 0; j < 3; j++)
			s += "|     ";
		s += "|\n";
		return s;
	}

	// public static void main(String[] args) {
	// 	Board board = new Board();
	// 	System.out.println(board.display());
	// }
}
