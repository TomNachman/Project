package algorithms.mazeGenerators;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        return new Maze(rows, cols);
    }
}
