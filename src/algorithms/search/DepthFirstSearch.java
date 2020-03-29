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
        iSearchable.getStartState().setVisited(true);
        stack.add(iSearchable.getStartState());

        while (!stack.empty()){
            MazeState currMazeState = stack.pop();
            this.numberOfNodesEvaluated++;
            neighbors = iSearchable.getAllPossibleStates(currMazeState);
            for (MazeState a:neighbors){
                stack.push(a);
            }
            neighbors.clear();
        }

        System.out.println(String.format("numberOfNodesEvaluated: %d", this.numberOfNodesEvaluated));
        System.out.println(String.format("number of Whites: %d", iSearchable.getWhites()));
        return new Solution(iSearchable.getStartState(), iSearchable.getGoalState());
    }

    @Override
    public int getNumberOfNodesEvaluated() { return this.numberOfNodesEvaluated;}
}
