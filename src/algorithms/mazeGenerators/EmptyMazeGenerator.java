package algorithms.mazeGenerators;
import java.util.Random;

/**
 *  EmptyMazeGenerator - Class of Empty Maze Based Generator
 */
public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * Generates a new Empty Maze (without walls) with random start and end points
     * @param rows - Num of rows in the maze
     * @param cols - Num of columns in the maze
     * @return Completed empty Maze
     */
    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        Maze myEmptyMaze =  new Maze(rows, cols);
        Random rand = new Random();
        myEmptyMaze.setStartPosition(new Position(rand.nextInt(rows),rand.nextInt(cols)));
        myEmptyMaze.setGoalPosition(new Position(rand.nextInt(rows),rand.nextInt(cols)));
        return myEmptyMaze;
    }
}
