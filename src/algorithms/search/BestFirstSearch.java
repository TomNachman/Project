package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    private PriorityQueue<MazeState> pariorityQ;

    public BestFirstSearch(){
        super(BestFirstSearch.class.getSimpleName());
        pariorityQ = new PriorityQueue<MazeState>((o1, o2) -> (int)(o1.getVal()-o2.getVal()));
    }

    @Override
    public Solution solve(ISearchable iSearchable) {return super.solve(iSearchable, pariorityQ);
    }

    @Override
    public int getNumberOfNodesEvaluated() {return numberOfNodesEvaluated;}
}
