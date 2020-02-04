import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;


public class Game implements Constants, Runnable {
    /** Declares Board object, which is the playing field */
    private Board theBoard;
    /**
     * Declares the Referee object, which sets the opponents to each other and
     * initiates the game
     */
    private Referee theRef;
    /** The X and O players */
    Player xPlayer, oPlayer;

    /**
     * Constructor for Game class creates a new Board object for the game
     */
    public Game(BufferedReader xSocketIn, BufferedReader oSocketIn, PrintWriter xSocketOut, PrintWriter oSocketOut) {
        xPlayer = new Player(xSocketIn, xSocketOut, LETTER_X);
        oPlayer = new Player(oSocketIn, oSocketOut, LETTER_O);
        theRef = new Referee();
        theBoard = new Board();
    }

    /**
     * Runs the game
     */
    @Override
    public void run() {
        try {
            // Intro to game
            playerWelcome();
            getNames();
            xPlayer.out().println("You are facing: " + oPlayer.getName());
            oPlayer.out().println("You are facing: " + xPlayer.getName());

            // Game Setup
            xPlayer.setBoard(theBoard);
            oPlayer.setBoard(theBoard);
            theRef.setBoard(theBoard);
            theRef.setoPlayer(oPlayer);
            theRef.setxPlayer(xPlayer);

            // Game Start
            theRef.runTheGame();

            // TODO Maybe turn into rematch option? or have this as exit?
            // try {
            //     while (!xPlayer.in().readLine().equals("Q") || !oPlayer.in().readLine().equals("Q")) {

            //     }
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        }
        catch (SocketException e) {
            xPlayer.out().println("The other player has disconnected, please restart the game");
            oPlayer.out().println("The other player has disconnected, please restart the game");
        }
    }

    private void getNames(){
        xPlayer.out().println("1");
        oPlayer.out().println("1");
        try{
            xPlayer.setName(xPlayer.in().readLine());
            oPlayer.setName(oPlayer.in().readLine());
            // // TODO Better name validation - do in a method
            // while(xPlayer.getName().equals(null) || oPlayer.getName().equals(null)){
            //     if(xPlayer.getName().equals(null)){
            //         xPlayer.out().print("Please try again: ");
            //         xPlayer.out().println("1");
            //         xPlayer.setName(xPlayer.in().readLine());
            //     }
            //     if(oPlayer.getName().equals(null)){
            //         oPlayer.out().print("Please try again: ");
            //         oPlayer.out().println("1");
            //         oPlayer.setName(xPlayer.in().readLine());
            //     }
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playerWelcome() {
        xPlayer.out().println("Opponent found, game starting...");
        oPlayer.out().println("Opponent found, game starting...");
    }
    
}