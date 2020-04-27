package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }
    public void communicateWithServer(){
        try{
            Socket myServer = new Socket(serverIP,serverPort);
            System.out.println(String.format("Client connected to server! serverIP: %s, serverPort: %s)", serverIP, serverPort));
            clientStrategy.clientStrategy(myServer.getInputStream(), myServer.getOutputStream());
            myServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
