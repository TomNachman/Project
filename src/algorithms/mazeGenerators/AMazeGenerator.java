package algorithms.mazeGenerators;

/**
 * Abstract Class AMazeGenerator
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int rows, int cols);

    /**
     * Measure the time it takes the Algorithm to generate a Maze
     * @param rows - number of rows in the maze
     * @param cols - number of columns in the maze
     * @return time to generate the maze in milliseconds
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        if (rows <=0 || cols <=0 ) return 0;
        long startTime = System.currentTimeMillis();
        generate(rows,cols);
        return System.currentTimeMillis()- startTime;
    }
}
