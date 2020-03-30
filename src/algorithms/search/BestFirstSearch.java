package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {


    public BestFirstSearch(){
        super(BestFirstSearch.class.getSimpleName());
        queue = new PriorityQueue<MazeState>((Comparator.comparingInt(o -> o.val)));
    }

    @Override
    public Solution solve(ISearchable iSearchable) {return super.solve(iSearchable);
    }

    @Override
    public int getNumberOfNodesEvaluated() {return numberOfNodesEvaluated;}
}
