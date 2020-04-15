package Server;
import sun.nio.ch.ThreadPool;

public class Server {
    private int Port;
    private int listeningIntervalMS;
    private IServerStrategy serverStrategy;
    private volatile boolean stop; //volatile - refreshes the value in all threads
    //private ThreadPool pool;

    public Server(int port, int listeningIntervalMS, IServerStrategy serverStrategy) {
        this.Port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.serverStrategy = serverStrategy;
        this.stop = false;
    }
    public void start(){
        //need todo here something with thread thats calls a function to start the server
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
    public void stop(){
        this.stop=true;
    }
}
