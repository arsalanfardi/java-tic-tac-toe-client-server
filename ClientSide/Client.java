import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket aSocket;
    private BufferedReader stdIn;
    private BufferedReader socketIn;
    private PrintWriter socketOut;
    private Controller controller;
    private boolean live;

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

    public void setLive(boolean live){
        this.live = live;
    }
    public void setController (Controller controller) {
        this.controller = controller;
    }

    public void out(String s){
        socketOut.println(s);
    }

}