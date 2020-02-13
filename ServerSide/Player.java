import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

/**
 * Defines the Player, their opponent (which is another object of Player type),
 * and their mark. Asks for inputs during each turn and, after checking that the
 * move is valid, marks the board at the corresponding position. Also gives the
 * option to play again by clearing the board.
 * 
 * @author arsal
 *
 */

public class Player {
    /** Declares name of the Player */
    private String name;
    /** Declares Board object to represent the playing field */
    private Board board;
    /** Declares the opponent to the Player */
    private Player opponent;
    /** Declares the mark ('X' or 'O') for the player */
    private char mark;
    /** The input socket for the player (from the client) */
    private BufferedReader socketIn;
    /** The output socket for the player (for writing to the client) */
    private PrintWriter socketOut;

    public Player(BufferedReader socketIn, PrintWriter socketOut, char mark) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
        this.mark = mark;
    }

    /**
     * Starts the game and displays the board. Checks if game is finished by
     * invoking checkMove(). If the came can continue, it will change the turn to
     * the opponent. Gives option of playing again with the same players once the
     * game ends.
     */
	public void play() {
        opponent.out().println(name + "'s turn");
		while(checkMove()) {
            this.makeMove();
            if(this.checkMove()) {
                opponent.makeMove();
            }
            else
                break;
        }
        System.out.println("Game finished");
		// this.playAgain();
    }
    
	/**
	 * Receives inputs from user on which position they would like to play.
	 * Checks if the entry is valid by invoking isValid(), before adding the player's mark to the board.
	 */
	public void makeMove() {
		int row = 3;
        int col = 3;
        String position = "";
		while(isValid(row, col) == false) {
            try {
                socketOut.println("2");
                position = socketIn.readLine();
                row = Character.getNumericValue(position.charAt(0));
                col = Character.getNumericValue(position.charAt(1));
                if(isValid(row,col) == false)
                    socketOut.println("Invalid entry, please try again");
            }
            catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        updateBoards(position);
	}

    private void updateBoards(String position) {
        board.addMark(Character.getNumericValue(position.charAt(0)), Character.getNumericValue(position.charAt(1)), mark);
        socketOut.println("4" + mark + position);
        opponent.out().println("4" + mark + position);
        socketOut.println(opponent.getName() + "'s turn");
    }

    /**
     * Assigns board object to the Board instance variable, representing the playing
     * field
     * 
     * @param board
     */
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * Assigns an opponent to the current Player object
	 * @param opponent
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
    }

    /** 
     * Returns the players opponent
     * @return opponent
    */
    public Player getOpponent(){
        return opponent;
    }
	/**
	 * Returns true if a move is valid, meaning it is within the boundaries of the board 
	 * and the position is open.
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isValid(int row, int col) {
		if(row > 2 || col > 2)
			return false;
		else if (board.getMark(row, col) == mark || board.getMark(row, col) == opponent.mark)
			return false;
		else
			return true;
	}
	/**
	 * Returns false if a move results in the end of a game, either by the Board being full or one of the Players winning.
	 * @return
	 */
	private boolean checkMove() {
	    if (board.xWins()) {
            String message = "GAME OVER: " + name + " wins!";
            gameOver(message);
			return false;
		}
		else if(board.oWins()) {
            String message = "GAME OVER: " + opponent.getName() + " wins!";
            gameOver(message);
			return false;
        }
        else if(board.isFull()) {
            String message = "It's a tie!";
            gameOver(message);
			return false;
		}
		return true;
	}
	
    private void gameOver(String message) {
        socketOut.print("5");
        opponent.out().print("5");
        socketOut.println(message);
        opponent.out().println(message);
    }

	/**
	 * Returns the name of the player
	 * @return
	 */
	public String getName() {
		return this.name;
    }

    /**
	 * Sets the name of the player
	 */
	public void setName(String name) {
		this.name = name;
    }
    
    /**
     * Getter for the output socket of the player.
     * @return socketOut the output socket of the player
     */
    public PrintWriter out() {
        return socketOut;
    }

    /**
     * Getter for the input socket of the player.
     * @return socketIn the input socket of the player
     */
    public BufferedReader in(){
        return socketIn;
    }

	
}
