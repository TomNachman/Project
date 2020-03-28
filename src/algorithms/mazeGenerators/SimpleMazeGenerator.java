package algorithms.mazeGenerators;

import java.util.Random;

/**
 * SimpleMazeGenerator - Class of Maze Based on Simple Random distribution.
 * @author Asaf Salomon and Tom Nachman
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * Generates a new Maze according simple random distribution
     * @param rows - Num of rows in the maze
     * @param cols - Num of columns in the maze
     * @return Complete Simple Maze
     */
    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        Maze mySimpleMaze = new Maze(rows, cols);
        if(rows != 1 && cols !=1){
            Random random = new Random();
            for(int i=1;i<mySimpleMaze.getRows();i++){
                mySimpleMaze.MakeWall(i,random.nextInt(cols-1));
            }
        }
        mySimpleMaze.setStartPosition(new Position(0,0));
        mySimpleMaze.setGoalPosition(new Position(rows-1,cols-1));
        return  mySimpleMaze;
    }
}
