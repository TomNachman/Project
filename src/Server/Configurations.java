package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Configurations {
    public static void main(String[] args){
        try (OutputStream output = new FileOutputStream("resources/config.properties")) {

            Properties prop = new Properties();
            // set the properties value
            prop.setProperty("NumOfThreads", "12");
            prop.setProperty("GenerateAlgorithm", "MyMazeGenerator");
            prop.setProperty("SolvingAlgorithm", "BestFirstSearch");

            // save properties to project root folder
            prop.store(output,"Asi Shtuk kvar");

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
