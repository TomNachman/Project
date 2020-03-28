package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve (ISearchable iSearchable);
    String getName();
    int getNumberOfNodesEvaluated();
}
