import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
// TODO: Auto-generated Javadoc
/**
 * The frame for the Tic-Tac-Toe game with the grid and various text areas and fields.
 * 
 * Handles the creation of all frames and message boxes. Uses a GridBagLayout for enhanced customization.
 * 
 * @author A. Fardi
 * @version 1
 * @since November 6, 2019
 */
public class GUI extends JFrame {
	
	/** Array of nine buttons for the tic-tac-toe */
	private JButton[][] buttons = new JButton[3][3];
	
	/** The player field. */
	private JTextField userNameField, playerField;
	
	/** The message window. */
	private JTextArea messageWindow;
	
	/**
	 * Instantiates a new tic tac toe view.
	 */
	public GUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		createSubPanels(mainPanel, gbc);
		setSize(400, 300);
		add(mainPanel);
		setTitle("Tic-Tac-Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Gets the player name.
	 *
	 * @param symbol the symbol
	 * @return the player name
	 */
	public String getPlayerName(char symbol) {
		String name = JOptionPane.showInputDialog("Enter your name:");
		return name;
	}
	
	/**
	 * Creates the message box.
	 *
	 * @param s the s
	 */
	public void createMessageBox(String s) {
		JOptionPane.showMessageDialog(null, s);
		dispose();
	}
	
	/**
	 * Adds the action listeners.
	 *
	 * @param gameListener the action listener for the class
	 */
	public void addActionListeners(ActionListener gameListener) {
		for(int i=0;i<buttons.length; i++) {
			for (int j=0;j<buttons.length; j++)
				buttons[i][j].addActionListener(gameListener);
		}
	}
	
	/**
	 * Sets the message window.
	 *
	 * @param s the new message window
	 */
	public void setMessageWindow(String s) {
		messageWindow.setText(s);
	}
	/**
	 * Sets the player text field with either 'O' or 'X'.
	 * 
	 * @param s the symbol of the player ('O' or 'X')
	 */
	public void setUserNameField(String s) {
		userNameField.setText(s);
	}
	/**
	 * Sets the player text field with either 'O' or 'X'.
	 * 
	 * @param s the symbol of the player ('O' or 'X')
	 */
	public void setPlayerField(String s) {
		playerField.setText(s);
	}
	/**
	 * Matches the selected button (the source) to a button in the buttons array, and marks the button with the provided mark.
	 * 
	 * @param source the selected button
	 * @param mark the Player's mark
	 */
	public void markBoard(JButton source, char mark) {
		OUTER_LOOP:
		for(int i=0;i<buttons.length; i++) {
			for (int j=0;j<buttons.length; j++) {
				if (source == buttons[i][j]) {
					buttons[i][j].setText(String.valueOf(mark));
					break OUTER_LOOP;
				}
			}
		}
	}
	/**
	 * Returns the position of the selected button by matching the source to a button in the buttons array.
	 * 
	 * @param source the selected button
	 * @return integer array with row and column of selected button
	 */
	public int[] getButtonPosition(JButton source) {
		for(int i=0;i<buttons.length; i++) {
			for (int j=0;j<buttons.length; j++) {
				if (source == buttons[i][j]) {
					return new int[]{i,j};
				}
			}
		}
		return null;
	}


//////////Helper Functions
	/**
	 * Creates the sub panels.
	 *
	 * @param mainPanel the main panel
	 * @param gbc the GridBagConstraints
	 */
	private void createSubPanels(JPanel mainPanel, GridBagConstraints gbc) {
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    addGridPanel(mainPanel, gbc);
	    gbc.gridx = 1;
	    addTextPanel(mainPanel, gbc);
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    createPlayerPanel(mainPanel, "Player:", gbc);
	    gbc.gridy = 2;
	    createUserNamePanel(mainPanel, "UserName:", gbc);
	}

	/**
	 * Creates the player panel.
	 *
	 * @param mainPanel the main panel
	 * @param labelText the label text
	 * @param gbc the GridBagConstraints
	 */
	private void createPlayerPanel(JPanel mainPanel, String labelText, GridBagConstraints gbc) {
		JPanel textPanel = new JPanel();
	    textPanel.setSize(200, 100);
	    JLabel label = new JLabel(labelText);
	    JLabel gap = new JLabel("              ");
	    playerField = new JTextField(null, 3);
	    playerField.setEditable(false);
	    textPanel.add(label);
	    textPanel.add(playerField);
	    textPanel.add(gap);
	    mainPanel.add(textPanel, gbc);
	}

	/**
	 * Adds the grid panel.
	 *
	 * @param mainPanel the main panel
	 * @param gbc the GridBagConstraints
	 */
	private void addGridPanel(JPanel mainPanel, GridBagConstraints gbc) {
		JPanel ticTacToePanel = new JPanel(new GridLayout(3, 3, 1, 1));
		addButtons(ticTacToePanel);
		mainPanel.add(ticTacToePanel, gbc);
	}
	
	/**
	 * Adds the buttons.
	 *
	 * @param panel the panel
	 */
	private void addButtons(JPanel panel) {
		panel.setSize(150, 150);
		for(int i=0;i<buttons.length; i++) {
			for(int j=0;j<buttons.length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(50, 50));
				panel.add(buttons[i][j]);
			}
		}
		panel.setVisible(true);
	}
	
	/**
	 * Creates the user name panel.
	 *
	 * @param mainPanel the main panel
	 * @param labelText the label text
	 * @param gbc the GridBagConstraints
	 */
	private void createUserNamePanel(JPanel mainPanel, String labelText, GridBagConstraints gbc) {
		JPanel textPanel = new JPanel();
	    textPanel.setSize(200, 100);
	    JLabel label = new JLabel(labelText);
	    userNameField = new JTextField(null, 6);
	    userNameField.setEditable(false);
	    textPanel.add(label);
	    textPanel.add(userNameField);
	    mainPanel.add(textPanel, gbc);
	}

	  /**
     * Converts and reverses the messages in an ArrayList of Strings
     * @return String of messages, properly formatted for output to message area
     */
    // private String convertMessageList () {
    //     String converted = "";
    //     for (int i = messageList.size() - 1; i >= 0; i--) {
    //         converted += messageList.get(i) + "\n";
    //     }
	// 	return converted;
	// }

	/**
	 * Adds the text panel.
	 *
	 * @param mainPanel the main panel
	 * @param gbc the GridBagConstraints
	 */
	private void addTextPanel(JPanel mainPanel, GridBagConstraints gbc) {
		JPanel textPanel = new JPanel(new BorderLayout(5,5));
		textPanel.setSize(300, 350);
		messageWindow = new JTextArea("", 6, 20);
		messageWindow .setEditable(false);
		JScrollPane scrollPane = new JScrollPane(messageWindow);
		textPanel.add(new JLabel("  Message Window"), BorderLayout.NORTH);
		textPanel.add(new JLabel(" "), BorderLayout.WEST);
		textPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(textPanel, gbc);
	}
}
