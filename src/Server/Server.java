package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Server {
    private int Port;
    private int listeningIntervalMS;
    private IServerStrategy serverStrategy;
    private volatile boolean stop; //volatile - refreshes the value in all threads

    public Server(int port, int listeningIntervalMS, IServerStrategy serverStrategy) {
        this.Port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.serverStrategy = serverStrategy;
        this.stop = false;
    }
    public void start()  {
        try{
            //check if port is availbale?
            ServerSocket serverSocket = new ServerSocket(Port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println((String.format("Server starter at: %s", serverSocket)));
            System.out.println((String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName())));
            System.out.println(("Server is waiting for clients"));
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            executor.execute(()->{
                while (!stop) {
                    try {
                        Socket ClientSocket = serverSocket.accept();
                        System.out.println(String.format("Client in: %s", ClientSocket));
                        executor.execute(() -> {
                            try {
                                System.out.println(String.format("handling client : %s", ClientSocket.toString()));
                                serverStrategy.serverStrategy(ClientSocket.getInputStream(), ClientSocket.getOutputStream());
                                ClientSocket.close();

                            } catch (IOException e) {
                                System.out.println("IOException" + e);
                            }
                        });
                    } catch (SocketTimeoutException e) {
                        System.out.println("Socket Timeout - No clients pending!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                executor.shutdown();

            });// im guessing the num of threds

        } catch (IOException e) {
            System.out.println("IOException"+ e);
        }
    }
    /**
    public void start() {
        new Th
    }
    public void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(Port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println(("Server is waiting for clients"));
            while(!stop){
                try {
                    Socket ClientSocket = serverSocket.accept(); //Accept the clients
                    System.out.println(String.format("Client in: %s", ClientSocket));
                    //TODO: add handleclient method ,the threadspool will handle each client
                    //TODO: add prints to get feedback from server/client
                    serverStrategy.serverStrategy(ClientSocket.getInputStream(), ClientSocket.getOutputStream());
                    ClientSocket.getInputStream().close();
                    ClientSocket.getOutputStream().close();
                    ClientSocket.close();
                }catch (SocketTimeoutException e) {
                    System.out.println("Socket Timeout - No clients pending!");
                }

            }
        }catch (IOException e) {
            System.out.println("IOException"+ e);
        }

    }*/
    public void stop(){
        System.out.println("Server Stopped");
        this.stop=true;
    }
}
