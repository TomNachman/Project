package algorithms.search;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     BestFirstSearch Uses the BFS algorithm to solve the maze, the only difference is the here
     we are using Priority Queue based on value comparator instead of a normal queue to
     Implement the solve function
     */
    public BestFirstSearch(){
        super(BestFirstSearch.class.getSimpleName());
        visitedCells = new HashSet<>();
        queue = new PriorityQueue<MazeState>((Comparator.comparingInt(AState::getVal)));
    }

    @Override
    public Solution solve(ISearchable iSearchable) {return super.solve(iSearchable);
    }

    /**
     * @return The Number of nodes the Alg Evaluated during the run
     */
    @Override
    public int getNumberOfNodesEvaluated() {return numberOfNodesEvaluated;}
}
