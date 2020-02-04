import java.net.SocketException;

/**
 * Referee class starts the game by assigning opponents and giving Player 'X'
 * the first turn.
 * 
 * @author arsal
 *
 */
public class Referee {
	/**
	 * Declares 'X' Player
	 */
	private Player xPlayer;
	/**
	 * Declares 'O' Player
	 */
	private Player oPlayer;
	/**
	 * Declares a board object representing the playing field
	 */
	private Board board;
	
	/**
	 * Assigns default values for instance variables to null, actual assignments occur in setters 
	 */
	public Referee() {}
	
	/**
	 * Assigns opponents to each player and begins game with xPlayer starting first
	 */
	public void runTheGame() throws SocketException {
		oPlayer.setOpponent(xPlayer);
		xPlayer.setOpponent(oPlayer);
		board.display();
		xPlayer.play();
	}
	/**
	 * Assigns 'O' player to the oPlayer instance variable
	 * @param oPlayer
	 */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	/**
	 * Assigns 'X' player to the xPlayer instance variable
	 * @param xPlayer
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}
	/**
	 * Assigns board object to the Board instance variable, representing the playing field
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
}
