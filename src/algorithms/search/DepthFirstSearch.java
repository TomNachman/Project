package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    private int numberOfNodesEvaluated = 0;

    public DepthFirstSearch() {
        super(DepthFirstSearch.class.getSimpleName());
    }

    @Override
    public Solution solve(ISearchable iSearchable) {
        Stack<MazeState> stack = new Stack<>();
        HashSet<String> visitedCells = new HashSet<>();
        ArrayList<MazeState> neighbors;

        // first StartState
        visitedCells.add(iSearchable.getStartState().toString());
        iSearchable.getStartState().setPrev(iSearchable.getStartState());
        stack.add(iSearchable.getStartState());

        while (!stack.empty() && visitedCells.size() < iSearchable.getWhites()) {
            MazeState currMazeState = stack.pop();
            /**
             if(currMazeState.getPosition() == null) System.out.println(String.format(
             "currMazeState is Fuck us, his prev is: %s",currMazeState.getPrev().getPosition()));
             if(iSearchable.getGoalState() == null) System.out.println(String.format(
             "GoalPosition is Fuck us, start Position: %s", iSearchable.getStartState().getPosition()));
             if(iSearchable.getGoalState().getPosition() == null) System.out.println(
             "GoalPosition.getPosition is Fuck us, his prev is: ");
             */
            if (currMazeState.getPosition().equals(iSearchable.getGoalState().getPosition())) {
                return new Solution(iSearchable.getStartState(), currMazeState);
            }
            if (!visitedCells.contains(currMazeState.toString())) {
                visitedCells.add(currMazeState.toString());
                this.numberOfNodesEvaluated++;
            }
            neighbors = iSearchable.getAllPossibleStates(currMazeState);
            for (MazeState a : neighbors) {
                if (!visitedCells.contains(a.toString()))
                    stack.push(a);

                if (a.getPrev() == null) {
                    a.setPrev(currMazeState);
                }
            }
        }
        // TODO:  we need to Throw an Exception here - There is no solution to the maze!
        return new Solution(iSearchable.getStartState(), iSearchable.getGoalState());
    }

    @Override
    public int getNumberOfNodesEvaluated() { return this.numberOfNodesEvaluated;}
}
