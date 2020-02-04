import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;

/**
 * The controller for the Tic-Tac-Toe application.
 * 
 * Communicates with the TicTacToegui, Player and Board objects to run the game.
 * 
 * @author A. Fardi & Mihai Robu
 * @version 2.0
 * @since February 03, 2020
 */
public class Controller {
	
	/** The gui. */
	GUI gui;
	
	/** The client that connects to the server */
	Client client;
	
	
	/**
	 * Instantiates a new tic-tac-toe controller.
	 *
	 * @param thegui the gui
	 * @param client the client
	 */
	public Controller(GUI theGUI, Client theClient) {
		gui = theGUI;
		gui.addActionListeners(new GameListener());
		client = theClient;
		client.setController(this);
		gui.setVisible(true);
		client.gaming();
	}


	/**
	 * The listener interface for receiving game events.
	 *
	 * @see GameEvent
	 */
	private class GameListener implements ActionListener{
		
		/**
		 * Action performed.
		 *
		 * @param e the ActionEvent object
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// get the coordinates of button click
			int[] position = gui.getButtonPosition((JButton) e.getSource());
			
			//TODO:
			// now we send the coordinates to server (via client class)
			//// invoke client "send" method

			//TODO:
			// get response from server (via client class)
			//// if response is valid, display to board
			////	maybe response from server is "X", "O" or "null"

			// TODO:
			// mark the GUI board ON BOTH CLIENTS!!! - done from server
			gui.markBoard((JButton) e.getSource(), 'X');
		}
	}
	
	public void switchBoard(String serverResponse) {
		switch(serverResponse.charAt(0)){
			//Input from Client required
			case '1':
				client.out(gui.getPlayerName());
				break;
			//Sending position of clicked button to server
			case '2':
				//TODO: Make "position" a member variable of the GUI, so that when a button is
				// clicked we can access it's value within this method. Position should be set to null
				// at the start of every turn in order to ignore out of turn clicks by the player.
				return;
			// Set the players symbol
			case '3':
				gui.setPlayerField(serverResponse.substring(1));
				break;
			//Server to Client message
			default:
				gui.setMessageWindow(serverResponse);
				break;
		}
	}

	public static void main (String[] args) {
		GUI gui = new GUI();
		Client client = new Client("localhost", 9898);
		Controller clientController = new Controller(gui, client);
	}
}
