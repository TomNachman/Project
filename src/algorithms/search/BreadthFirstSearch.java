package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected int numberOfNodesEvaluated = 0;
    protected Queue<MazeState> queue;

    // Empty Constructor
    public BreadthFirstSearch() {
        super(BreadthFirstSearch.class.getSimpleName());
        queue = new LinkedList<>();
    }

    // name Based Constructor for Extend's classes
    public BreadthFirstSearch(String name) {
        super(name);
    }
    @Override
    public Solution solve(ISearchable iSearchable) {
        if (iSearchable == null) return null;
        HashSet<String> visitedCells = new HashSet<>();
        queue.add(iSearchable.getStartState());
        visitedCells.add(iSearchable.getStartState().toString());
        iSearchable.getStartState().setPrev(iSearchable.getStartState());
        while(!queue.isEmpty()){
            MazeState curr = queue.poll();
            if(curr.getPosition().equals(iSearchable.getGoalState().getPosition())){
                return new Solution(iSearchable.getStartState(), curr);
            }
            for (MazeState a : iSearchable.getAllPossibleStates(curr)){
                if (!visitedCells.contains(a.toString())) {
                    visitedCells.add(a.toString());
                    a.setVal(a.getVal()+curr.getVal());
                    queue.add(a);
                    this.numberOfNodesEvaluated++;
                }
                if (a.getPrev() == null) a.setPrev(curr);
            }
        }

        //return new Solution(iSearchable.getStartState(), iSearchable.getGoalState());
        return new Solution();
        // TODO: return empty Solution (Maybe Exception)
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }
}
