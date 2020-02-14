import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author A. Fardi & Mihai Robu
 * @version 2.0
 * @since February 03, 2020
 */

public class Server implements Constants {
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader oSocketIn, xSocketIn;
    private PrintWriter oSocketOut, xSocketOut;
    private ExecutorService pool;
    
    /**
     * Constructor for Server that creates new socket and thread pool
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(9898);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts server and continues to accept new connections - when two clients connected, start a new Game (implements Runnable)
     */
    public void runServer(){
        try {
            while(true){
                //Getting the first player's connection
                socket = serverSocket.accept();
                connectXPlayer();
                //Getting the second player's connection
                socket = serverSocket.accept();
                connectOPlayer();
                //Start a new Game
                pool.execute(new Game(xSocketIn, oSocketIn, xSocketOut, oSocketOut));
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Server shutting down");
            xSocketIn.close();
            xSocketOut.close();
            oSocketIn.close();
            oSocketOut.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectXPlayer() throws IOException {
        System.out.println("Player X connected... waiting for opponent");
        xSocketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        xSocketOut = new PrintWriter(socket.getOutputStream(), true);
        xSocketOut.println("You are Player X... waiting for opponent");
        xSocketOut.print("3");
        xSocketOut.println(LETTER_X);
        
    }

    private void connectOPlayer() throws IOException{
        System.out.println("Player O connected");
        oSocketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        oSocketOut = new PrintWriter(socket.getOutputStream(), true);
        oSocketOut.print("3");
        oSocketOut.println(LETTER_O);
    }
    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }
}