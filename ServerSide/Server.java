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

public class Server {
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader oSocketIn, xSocketIn;
    private PrintWriter oSocketOut, xSocketOut;
    private ExecutorService pool;
    
    public Server() {
        try {
            serverSocket = new ServerSocket(9898);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runServer(){
        try {
            while(true){
                //Getting the first player's connection
                socket = serverSocket.accept();
                System.out.println("Player X connected... waiting for opponent");
                xSocketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                xSocketOut = new PrintWriter(socket.getOutputStream(), true);
                xSocketOut.println("You are Player X... waiting for opponent");
                xSocketOut.println("3X");
                //Getting the second player's connection
                socket = serverSocket.accept();
                System.out.println("Player O connected");
                oSocketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                oSocketOut = new PrintWriter(socket.getOutputStream(), true);
                xSocketOut.println("Player O connected");
                oSocketOut.println("Player O connected");
                oSocketOut.println("3O");
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

    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();

        // Question for Dr. M - do we need to ensure thread shutdown before server shutdown?
        // if yes, is this the right place? OR in the try-catch before socket close
        // server.pool.shutdown();
        // try {
        //     server.pool.awaitTermination(1, TimeUnit.MINUTES);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}