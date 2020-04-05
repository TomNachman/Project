package algorithms.search;

import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private String name;
    // All data structures needed for the Algorithms
    public Queue<MazeState> queue;
    public HashSet<String> visitedCells;
    public Stack<MazeState> stack;

    public ASearchingAlgorithm(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
