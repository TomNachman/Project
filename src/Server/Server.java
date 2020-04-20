package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(Port);
        serverSocket.setSoTimeout(listeningIntervalMS);
        System.out.println(("Server is waiting for clients"));
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10); // im guessing the num of threds
        while (!stop) {
            Socket ClientSocket = serverSocket.accept();
            System.out.println(String.format("Client in: %s", ClientSocket));
            executor.execute(() -> {
                try {
                    serverStrategy.serverStrategy(ClientSocket.getInputStream(), ClientSocket.getOutputStream());
                    ClientSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverSocket.close();
        }
        executor.shutdown();
        serverSocket.close();
    }
    /**
    public void start() {
        //need todo here something with thread thats calls runServer method
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
        this.stop=true;
    }
    /** Example from Lab
     public void start() {
         try {
             ServerSocket serverSocket = new ServerSocket(port);
             serverSocket.setSoTimeout(listeningIntervalMS);
             LOG.info(String.format("Server starter at %s!", serverSocket));
             LOG.info(String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName()));
             LOG.info("Server is waiting for clients...");
             while (!stop) {
                 try {
                     Socket clientSocket = serverSocket.accept(); // Accepts client
                     LOG.info(String.format("Client excepted: %s", clientSocket));
                     try {
                         serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
                         clientSocket.close();
                     } catch (IOException e) {
                        LOG.error("IOException - Error handing client!", e);
                     }
                 } catch (SocketTimeoutException e) {
                    LOG.debug("Socket Timeout - No clients are waiting!");
                 }
             }
             serverSocket.close();
         } catch (IOException e) {
         LOG.error("IOException:", e);
         }
     }
     */
}
