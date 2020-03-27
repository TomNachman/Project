import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator myMaze = new SimpleMazeGenerator();
        Maze simple = myMaze.generate(10, 10);
        simple.print();
        System.out.println(String.format("Time To Generate: %d ",myMaze.measureAlgorithmTimeMillis(3,4)));
    }
}
