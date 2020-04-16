package Server;

import java.io.*;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    // ראיתי באיזה סרטון ששמים כאן synchronized בהקשר הthreads אבל אין לי מושג עדיין
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws FileNotFoundException {
        try{
            ObjectInputStream DimInputStream = new ObjectInputStream(inFromClient);
            ObjectOutputStream MazeOutputStream = new ObjectOutputStream(outToClient);
            int [] MazeDim = (int[])DimInputStream.readObject();
            //Generate maze
            IMazeGenerator mg = new MyMazeGenerator();
            Maze GenMaze = mg.generate(MazeDim[0]/*Rows*/,MazeDim[1]/*Cols*/);
            //declaring ByteOutputCompressStream as byte maze to send
            ByteArrayOutputStream ByteOutputCompressStream = new ByteArrayOutputStream();
            MyCompressorOutputStream compressed = new MyCompressorOutputStream(ByteOutputCompressStream);
            compressed.write(GenMaze.toByteArray());
            MazeOutputStream.writeObject(ByteOutputCompressStream.toByteArray());
            MazeOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }


}
}
