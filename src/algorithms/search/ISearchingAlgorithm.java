package algorithms.search;

import java.util.ArrayList;

public interface ISearchingAlgorithm {
    Solution solve (ISearchable iSearchable);
    String getName();
    int getNumberOfNodesEvaluated();
}
