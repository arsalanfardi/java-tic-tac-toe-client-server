// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.JButton;

// /**
//  * The controller for the Tic-Tac-Toe application.
//  * 
//  * Communicates with the TicTacToeView, Player and Board objects to run the game.
//  * 
//  * @author A. Fardi
//  * @version 1
//  * @since November 6, 2019
//  */
// public class TicTacToeController implements Constants {
	
// 	/** The view. */
// 	TicTacToeView view;
	
// 	/** The o player. */
// 	Player xPlayer, oPlayer;
	
// 	/** Tracks which player's turn */
// 	Player turn;
	
// 	/** The board. */
// 	Board board;
	
// 	/**
// 	 * Instantiates a new tic-tac-toe controller.
// 	 *
// 	 * @param theView the the view
// 	 * @param theBoard the the board
// 	 */
// 	public TicTacToeController(TicTacToeView theView, Board theBoard) {
// 		view = theView;
// 		view.addActionListeners(new GameListener());
// 		board = theBoard;
// 		createPlayers();
// 		startGame();
// 	}
// 	/**
// 	 * Receives inputs from user on which position they would like to play, and changes the turn to the opponent.
// 	 *
// 	 * @param player the player making the turn.
// 	 */
// 	public void makeMove(Player player) {
// 		view.setMessageWindow(player.getName() + "'s turn");
// 		view.setPlayerField(String.valueOf(player.getMark()));
// 		view.setUserNameField(player.getName());
// 		turn = player.getOpponent();
// 	}
// ///////////Helper functions
// 	/**
// 	 * The listener interface for receiving game events.
// 	 *
// 	 * @see GameEvent
// 	 */
// 	private class GameListener implements ActionListener{
		
// 		/**
// 		 * Action performed.
// 		 *
// 		 * @param e the ActionEvent object
// 		 */
// 		@Override
// 		public void actionPerformed(ActionEvent e) {
// 			int[] position = view.getButtonPosition((JButton) e.getSource());
// 			if (board.getMark(position[0], position[1]) == SPACE_CHAR) {
// 				makeMove(turn);
// 				view.markBoard((JButton) e.getSource(), turn.getMark());
// 				board.addMark(position[0], position[1], turn.getMark());
// 			}
// 			checkMove();
// 		}
// 	}
	
// 	/**
// 	 * Starts the game.
// 	 */
// 	private void startGame() {
// 		xPlayer.setBoard(board);
// 		oPlayer.setBoard(board);
// 		oPlayer.setOpponent(xPlayer);
// 		xPlayer.setOpponent(oPlayer);
// 		makeMove(xPlayer);
// 	}
	
// 	/**
// 	 * Creates the players.
// 	 */
// 	private void createPlayers() {
// 		String x_name = view.getPlayerName(LETTER_X);
// 		String y_name = view.getPlayerName(LETTER_O);
// 		xPlayer = new Player(x_name, LETTER_X);
// 		oPlayer = new Player(y_name, LETTER_O);
// 	}
	
// 	/**
// 	 * Checks for completion of the game and, if so, closes the frame.
// 	 */
// 	private void checkMove() {
// 		if(board.oWins()) {
// 			view.setMessageWindow("GAME OVER");
// 			view.createMessageBox("GAME OVER: " + oPlayer.getName() + " wins!");
// 		}
// 		else if (board.xWins()) {
// 			view.setMessageWindow("GAME OVER");
// 			view.createMessageBox("GAME OVER: " + xPlayer.getName() + " wins!");
// 		}
// 		else if(board.isFull()) {
// 			view.setMessageWindow("GAME OVER");
// 			view.createMessageBox("It's a tie!");
// 		}

// 	}
// }
