package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Properties;


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
        Configurations.SetPropertys();
    }
    public void start()  {
        try{
            //TODO:check if port is availbale?
            ServerSocket serverSocket = new ServerSocket(Port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println((String.format("Server starter at: %s", serverSocket)));
            System.out.println((String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName())));
            System.out.println(("Server is waiting for clients..."));
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Configurations.GetNumOfThreads());
            executor.execute(()->{ //Server Thread
                while (!stop) {
                    try {
                        Socket ClientSocket = serverSocket.accept();
                        System.out.println(String.format("Client in: %s", ClientSocket));
                        executor.execute(new Thread(()->ClientHandler(ClientSocket))); //Client Threads
                    } catch (SocketTimeoutException e) {
                        System.out.println("Socket Timeout - No clients pending!");
                    } catch (IOException e) {
                        System.out.println("IOException" + e);
                    }
                }
                executor.shutdown();

            });// im guessing the num of threds

        } catch (IOException e) {
            System.out.println("IOException"+ e);
        }
    }
    public void ClientHandler(Socket ClientSocket){
        try {
            System.out.println(String.format("handling client : %s", ClientSocket.toString()));
            serverStrategy.serverStrategy(ClientSocket.getInputStream(), ClientSocket.getOutputStream());
            ClientSocket.close();

        } catch (IOException e) {
            System.out.println("IOException" + e);
        }

    }
    public void stop(){
        System.out.println("Server Stopped");
        this.stop=true;
    }
    //Anonymous Class
    public static class Configurations{
        private static Properties prop;

        /** loading the configuration from config file into properties*/
        public static void SetPropertys () {
            try (InputStream input = new FileInputStream("resources/config.properties")) {
                prop = new Properties();
                // load a properties file
                prop.load(input);
                System.out.println(String.format("NumOfThreads is: %s",prop.getProperty("NumOfThreads")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        /** Returns the number of threads in the configuration file */
        public static int GetNumOfThreads(){
            try{
                int num = Integer.parseInt(prop.getProperty("NumOfThreads"));
                if (num>0) return num;
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
            return 2;
        }

    }
}
