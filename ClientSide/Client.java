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
    private String info = ""; // this is information from server

    public Client
(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter(aSocket.getOutputStream(), true);
            System.out.println("Client connected");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void gaming() {
        String serverResponse = "";
        // read response from the socket
            while(true){
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

    public void setController (Controller controller) {
        this.controller = controller;
    }

    public String getInfo () {
        return this.info;
    }

}