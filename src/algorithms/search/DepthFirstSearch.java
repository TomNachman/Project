package algorithms.search;

import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    private int numberOfNodesEvaluated = 0;

    public DepthFirstSearch() {
        super(DepthFirstSearch.class.getSimpleName());
        visitedCells = new HashSet<>();
        stack = new Stack<>();
    }

    @Override
    public Solution solve(ISearchable iSearchable) {
        if (iSearchable == null) return null;
        // first StartState
        visitedCells.add(iSearchable.getStartState().toString());
        iSearchable.getStartState().setPrev(iSearchable.getStartState());
        stack.add(iSearchable.getStartState());
        System.out.println(stack.getClass());

        while (!stack.empty()) {
            MazeState currMazeState = stack.pop();

            if (currMazeState.getPosition().equals(iSearchable.getGoalState().getPosition())) {
                return new Solution(iSearchable.getStartState(), currMazeState);
            }

            for (MazeState a : iSearchable.getAllPossibleStates(currMazeState)) {

                if (!visitedCells.contains(a.toString())){
                    visitedCells.add(currMazeState.toString());
                    this.numberOfNodesEvaluated++;
                    stack.push(a);
                }

                if (a.getPrev() == null) a.setPrev(currMazeState);
            }
        }
        return new Solution();
    }

    /**
     * @return The Number of nodes the Alg Evaluated during the run
     */
    @Override
    public int getNumberOfNodesEvaluated() { return this.numberOfNodesEvaluated;}
}
