package Server;

import java.io.*;
import java.util.Arrays;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient)  {
        try{
            ObjectInputStream DimInputStream = new ObjectInputStream(inFromClient);
            ObjectOutputStream MazeOutputStream = new ObjectOutputStream(outToClient);
            int [] MazeDim = (int[])DimInputStream.readObject(); //check input(nul...)
            //Generate maze
            IMazeGenerator mg = new MyMazeGenerator();
            Maze GenMaze = mg.generate(MazeDim[0]/*Rows*/,MazeDim[1]/*Cols*/);

            //declaring ByteOutputCompressStream as byte maze to send
            ByteArrayOutputStream ByteOutputCompressStream = new ByteArrayOutputStream();
            MyCompressorOutputStream compressed = new MyCompressorOutputStream(ByteOutputCompressStream);

            compressed.write(GenMaze.toByteArray());
            compressed.flush();
            compressed.close();

            MazeOutputStream.writeObject(ByteOutputCompressStream.toByteArray());
            MazeOutputStream.flush();

        } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }


}
}
