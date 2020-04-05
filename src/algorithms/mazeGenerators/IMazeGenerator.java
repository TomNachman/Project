package algorithms.mazeGenerators;

/**
 *  Maze Generator Interface
 *  Methods that need to be implemented:
 *      1. generate
 *      2. measureAlgorithmTimeMillis
 */

public interface IMazeGenerator {
    Maze generate (int rows, int cols);
    long measureAlgorithmTimeMillis (int rows, int cols);
}
