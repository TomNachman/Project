package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int rows, int cols);

    /**
     * Measure the time it takes the Algorithm to generate a Maze
     * @param rows
     * @param cols
     * @return
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        if (rows <=0 || cols <=0 ) return 0;
        long startTime = System.currentTimeMillis();
        generate(rows,cols);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
