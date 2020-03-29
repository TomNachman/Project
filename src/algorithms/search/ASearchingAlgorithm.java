package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private String name;
    private int numberOfNodesEvaluated;

    public ASearchingAlgorithm(String name) {
        this.name = name;
        this.numberOfNodesEvaluated = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return this.numberOfNodesEvaluated;
    }
}
