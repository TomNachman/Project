package Server;

import java.io.*;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws FileNotFoundException {
        try{
            int [] MazeDim = (int[])inFromClient.read(); //need to solve
            IMazeGenerator mg = new MyMazeGenerator();
            Maze GenMaze = mg.generate(MazeDim[0]/*Rows*/,MazeDim[1]/*Cols*/);
            ByteArrayOutputStream ByteOutputCompressStream = new ByteArrayOutputStream();
            MyCompressorOutputStream compreesed = new MyCompressorOutputStream(ByteOutputCompressStream);
            compreesed.write(GenMaze.toByteArray());
            outToClient.write(ByteOutputCompressStream.toByteArray());//i think???

        } catch (IOException e) {
        e.printStackTrace();
    }


}
}
