package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {


    public BestFirstSearch(){
        super(BestFirstSearch.class.getSimpleName());
        queue = new PriorityQueue<MazeState>((Comparator.comparingInt(o -> o.getVal()*-1)));
        /**queue = new PriorityQueue<MazeState>((Comparator<AState>) (o1, o2) -> {
            if(o1.getVal()> o2.getVal())
                return 1;
            if(o1.getVal() < o2.getVal())
                return -1;
            else return 0;
        }); */
    }

    @Override
    public Solution solve(ISearchable iSearchable) {return super.solve(iSearchable);
    }

    @Override
    public int getNumberOfNodesEvaluated() {return numberOfNodesEvaluated;}
}
