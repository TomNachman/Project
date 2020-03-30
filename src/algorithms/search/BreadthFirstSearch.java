package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    private int numberOfNodesEvaluated = 0;


    public BreadthFirstSearch() {
        super(BreadthFirstSearch.class.getSimpleName());
    }

    @Override
    public Solution solve(ISearchable iSearchable) {
        Queue<MazeState> queue = new LinkedList<>();
        HashSet<String> visitedCells = new HashSet<>();
        queue.add(iSearchable.getStartState());
        visitedCells.add(iSearchable.getStartState().toString());
        iSearchable.getStartState().setPrev(iSearchable.getStartState());
        while(!queue.isEmpty()){
            MazeState curr = queue.remove();
            if(curr.getPosition().equals(iSearchable.getGoalState().getPosition())){
                return new Solution(iSearchable.getStartState(), curr);
            }
            /**if (!visitedCells.contains(curr.toString())) {
                visitedCells.add(curr.toString());
                this.numberOfNodesEvaluated++;
            }*/
            for (MazeState a : iSearchable.getAllPossibleStates(curr)){
                if (!visitedCells.contains(a.toString())) {
                    visitedCells.add(a.toString());
                    queue.add(a);
                    this.numberOfNodesEvaluated++;
                }
                if (a.getPrev() == null) a.setPrev(curr);
            }
        }
        return new Solution(iSearchable.getStartState(), iSearchable.getGoalState());
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }
}
