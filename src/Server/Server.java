package Server;

public class Server {
    private int Port;
    private int listeningIntervalMS;
    private IServerStrategy serverStrategy;

    public Server(int port, int listeningIntervalMS, IServerStrategy serverStrategy) {
        this.Port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.serverStrategy = serverStrategy;
    }
    public void start(){

    }
    public void stop(){

    }
}
