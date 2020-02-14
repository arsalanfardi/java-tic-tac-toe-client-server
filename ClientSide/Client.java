import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author A. Fardi & Mihai Robu
 * @version 2.0
 * @since February 03, 2020
 */

 /**
  * Client Class used for connecting to TicTacToe server
  */
public class Client {
    private Socket aSocket;
    private BufferedReader stdIn;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private Controller controller;
    private boolean live;

    /**
     * Constructor for Client that connects to server and initializes input/output streams
     */
    public Client(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter(aSocket.getOutputStream(), true);
            System.out.println("Client connected");
            live = true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Method that constantly listens for instructions from server
     */
    public void gaming() {
        String serverResponse = "";
        // read response from the socket
            while(live){
            try{
                serverResponse = socketIn.readLine(); //read response from the socket
                if(serverResponse != null){
                    controller.switchBoard(serverResponse);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Setter for live (while the player is gaming)
     * @param live boolean - true if player is still gaming, false when game is over
     */
    public void setLive(boolean live){
        this.live = live;
    }

    /**
     * Sets controller on client side
     * @param controller
     */
    public void setController (Controller controller) {
        this.controller = controller;
    }

    /**
     * Used for outputting player messages to server
     */
    public void out(String s){
        socketOut.println(s);
    }

}