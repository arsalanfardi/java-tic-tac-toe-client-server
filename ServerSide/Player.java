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
        this.socketOut = (socketOut);
        this.mark = mark;
    }

    /**
     * Starts the game and displays the board. Checks if game is finished by
     * invoking checkMove(). If the came can continue, it will change the turn to
     * the opponent. Gives option of playing again with the same players once the
     * game ends.
     */
	public void play() throws SocketException {
        socketOut.print(board.display());
        opponent.out().println(name + "'s turn");
		while(true) {
            this.makeMove();
			if(this.checkMove())
                opponent.makeMove();
            else
                break;
        }
        socketOut.print(board.display());
        opponent.out().print(board.display());
        socketOut.println("2");
        opponent.out().println("2");
		// this.playAgain();
    }
    
	/**
	 * Receives inputs from user on which position they would like to play.
	 * Checks if the entry is valid by invoking isValid(), before adding the player's mark to the board.
	 */
	public void makeMove() throws SocketException {
		// Scanner scan = new Scanner(System.in);
		int row = 3;
		int col = 3;
		while(isValid(row, col) == false) {
            try {
                socketOut.println(name + ": enter the row of your next " + mark);
                socketOut.println("1");
                row = Integer.parseInt(socketIn.readLine());
                socketOut.println(name + ": enter the column of your next " + mark);
                socketOut.println("1");
                col = Integer.parseInt(socketIn.readLine());
                if(isValid(row,col) == false)
                    socketOut.println("Invalid entry, please try again");
            }
            catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        board.addMark(row, col, mark);
        //socketOut.print(opponent.getName() + "'s turn" + board.display());
        socketOut.println(board.display());
        socketOut.println(opponent.getName() + "'s turn");
        opponent.out().print(board.display());
	}
	/**
	 * Assigns board object to the Board instance variable, representing the playing field
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
	 * Returns true if a move results in the end of a game, either by the Board being full or one of the Players winning.
	 * @return
	 */
	private boolean checkMove() {
		if(board.isFull()) {
            socketOut.println("It's a tie!");
            opponent.out().println("It's a tie!");
			return false;
		}
		else if (board.xWins()) {
            socketOut.println("GAME OVER: " + name + " wins!");
            opponent.out().println("GAME OVER: " + name + " wins!");
			return false;
		}
		else if(board.oWins()) {
            socketOut.println("GAME OVER: " + opponent.getName() + " wins!");
            opponent.out().println("GAME OVER: " + opponent.getName() + " wins!");
			return false;
		}
		return true;
	}
	/**
	 * Receives user input on whether they would like to play again.
	 * If so, clears the board and invokes play() once again.
	 */
	private void playAgain() {
		System.out.println("Enter 1 to play again, or any other key to end the game");
		// Scanner scan = new Scanner(System.in);
		// int input = Integer.parseInt(scan.nextLine());
		// if(input == 1) {
		// 	board.clear();
		// 	this.play();
		// }
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
