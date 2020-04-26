package Server;

import java.io.*;

public class ServerStrategyStringReverser implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
        PrintWriter toClient = new PrintWriter(outToClient);
        String phrase;
        try{
            String reversedPhrase;
            phrase = fromClient.readLine();
            reversedPhrase = new StringBuilder(phrase).reverse().toString();
            toClient.write(reversedPhrase+"\r\n");
            toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
