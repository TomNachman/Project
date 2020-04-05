package algorithms.mazeGenerators;
import java.util.Random;

/**
 * SimpleMazeGenerator - Class of Simple Maze Based Generator
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * Generates a new Maze according simple random distribution
     * @param rows - Num of rows in the maze
     * @param cols - Num of columns in the maze
     * @return Completed Simple Maze
     */
    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        Maze mySimpleMaze = new Maze(rows, cols);

        Random random = new Random();
        if(rows != 1 && cols !=1){
            for(int i=1;i<mySimpleMaze.getRows();i++){
                mySimpleMaze.MakeWall(i,random.nextInt(cols-1));
            }
            mySimpleMaze.setStartPosition(new Position(0, random.nextInt(cols-1)));
            mySimpleMaze.setGoalPosition(new Position(random.nextInt(rows-1) +1 , cols-1));
        }
        else
        {
            mySimpleMaze.setStartPosition(new Position(0, random.nextInt(cols)));
            mySimpleMaze.setGoalPosition(new Position(random.nextInt(rows) , cols-1));
        }

        return mySimpleMaze;
    }
}
