package algorithms.search;

/**
 *  Searching Algorithm Interface
 *  Methods that need to be implemented:
 *      1. solve
 *      2. getName
 *      3. getNumberOfNodesEvaluated
 */

public interface ISearchingAlgorithm {
    Solution solve (ISearchable iSearchable);
    String getName();
    int getNumberOfNodesEvaluated();
}
