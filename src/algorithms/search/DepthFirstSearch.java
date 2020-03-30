package algorithms.search;

import java.util.HashSet;
import java.util.Stack;
import java.util.ArrayList;

public class DepthFirstSearch extends ASearchingAlgorithm {

    private int numberOfNodesEvaluated = 0;

    public DepthFirstSearch() {
        super(DepthFirstSearch.class.getSimpleName());
    }

    @Override
    public Solution solve(ISearchable iSearchable) {
        Stack<MazeState> stack = new Stack<>();
        HashSet<String> visitedCells = new HashSet<>();

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
        // TODO:  we need to Throw an Exception here - There is no solution to the maze!
        return new Solution(iSearchable.getStartState(), iSearchable.getGoalState());
    }

    @Override
    public int getNumberOfNodesEvaluated() { return this.numberOfNodesEvaluated;}
}
