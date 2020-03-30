package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    private PriorityQueue<AState> pariorityQ;

    public BestFirstSearch(){
        super(BestFirstSearch.class.getSimpleName());
        pariorityQ = new PriorityQueue<AState>(new Comparator<AState>() {
            @Override
            public int compare(AState o1, AState o2) {
                return (int)(o1.getVal()-o2.getVal());
            }
        });
    }

    @Override
    public Solution solve(ISearchable iSearchable) {return super.solve(iSearchable);
    }

    @Override
    public int getNumberOfNodesEvaluated() {return numberOfNodesEvaluated;}
}
