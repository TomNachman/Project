package algorithms.search;
import java.util.HashSet;

/**
 * Abstract Class ASearchingAlgorithm: containing the data structures needed for the Algorithms
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private String name;
    protected HashSet<String> visitedCells;

    /** Name Constructor */
    public ASearchingAlgorithm(String name) {
        this.name = name;
    }

    /** @return the name of the Algorithm */
    @Override
    public String getName() {
        return this.name;
    }

}
