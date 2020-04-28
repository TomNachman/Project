package Server;

import java.io.*;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    public String generate;
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient)  {
        try{
            ObjectInputStream DimInputStream = new ObjectInputStream(inFromClient);
            this.generate = Server.Configurations.getGenerateMethod();
            ObjectOutputStream MazeOutputStream = new ObjectOutputStream(outToClient);
            int [] MazeDim = (int[])DimInputStream.readObject(); //check input(nul...)

            //Generate maze
            try{
                Class<?>mg2 = Class.forName("algorithms.mazeGenerators." +generate);
                IMazeGenerator mg = (IMazeGenerator)mg2.newInstance();
                Maze GenMaze = mg.generate(MazeDim[0]/*Rows*/,MazeDim[1]/*Cols*/);

                //declaring ByteOutputCompressStream as byte maze to send
                ByteArrayOutputStream ByteOutputCompressStream = new ByteArrayOutputStream();
                MyCompressorOutputStream compressed = new MyCompressorOutputStream(ByteOutputCompressStream);

                compressed.write(GenMaze.toByteArray());
                compressed.flush();
                compressed.close();

                MazeOutputStream.writeObject(ByteOutputCompressStream.toByteArray());
                MazeOutputStream.flush();
                MazeOutputStream.close();
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }

        } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        }
    }
}
