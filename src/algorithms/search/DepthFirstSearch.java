package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    private int numberOfNodesEvaluated = 0;

    public DepthFirstSearch() {
        super(DepthFirstSearch.class.getSimpleName());
    }

    @Override
    public Solution solve(ISearchable iSearchable) {
        Stack <MazeState> stack = new Stack<>();
        ArrayList<MazeState> neighbors;

        // first StartState
        iSearchable.getStartState().setVal(0);
        stack.add(iSearchable.getStartState());

        while (!stack.empty()){
            MazeState currMazeState = stack.pop();
            neighbors = iSearchable.getAllPossibleStates(currMazeState);
            if(neighbors.size()>=1) numberOfNodesEvaluated++;
            for (MazeState a:neighbors){
                if(a.isBetterPrev(currMazeState)) {
                    a.setPrev(currMazeState);
                }
                if(!a.isVisited()) {
                    stack.push(a);
                    a.setVisited(true);
                }
            }
            neighbors.clear();
        }
        return new Solution(iSearchable.getStartState(), iSearchable.getGoalState());
    }
}
