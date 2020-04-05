package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * BestFirstSearch Class: This class uses the 'BFS algorithm' to solve the problem.
 *                        It Implements the algorithm data structure as a Priority Queue based on value comparator
 *                        (the lower the value is -> the highest priority it's get).
 */
public class BestFirstSearch extends BreadthFirstSearch {

    /** BestFirstSearch Constructor */
    public BestFirstSearch(){
        super(BestFirstSearch.class.getSimpleName());
        queue = new PriorityQueue<AState>((Comparator.comparingInt(AState::getVal)));
    }

    /** @return the solution of the maze */
    @Override
    public Solution solve(ISearchable iSearchable) {
        return super.solve(iSearchable);
    }
}
