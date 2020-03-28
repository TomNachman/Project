package algorithms.mazeGenerators;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        Maze myEmptyMaze =  new Maze(rows, cols);
        myEmptyMaze.setStartPosition(new Position(0,0));
        myEmptyMaze.setGoalPosition(new Position(rows-1,cols-1));
        return  myEmptyMaze;
    }
}
