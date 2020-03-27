package algorithms.mazeGenerators;

public interface IMazeGenerator {
    public Maze generate (int rows, int cols);
    public long measureAlgorithmTimeMillis (int rows, int cols);
}
