package test;

import algorithms.mazeGenerators.*;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunMazeGenerator {
    public static void main(String[] args) {
        //testMazeGenerator(new EmptyMazeGenerator());
        //testMazeGenerator(new SimpleMazeGenerator());
        //testMazeGenerator(new MyMazeGenerator());
        System.out.println(258&(0xff));
        ByteBuffer bb = ByteBuffer.allocate(4);
        for (int i=240;i<260;i++){
            byte[] columns = ByteBuffer.allocate(4).putInt(i).array();
            System.out.println(ByteBuffer.wrap(columns).getInt());
        }

    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(100/*rows*/, 100/*columns*/);

        // prints the maze
        maze.print();

        // get the maze entrance
        Position startPosition = maze.getStartPosition();

        // print the position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"

        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}