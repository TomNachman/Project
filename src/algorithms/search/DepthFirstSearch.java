package algorithms.search;
import java.util.HashSet;
import java.util.Stack;

/**
 * DepthFirstSearch Class: This class uses the 'DFS algorithm' to solve the problem.
 *                         It Implements the algorithm data structure as a Stack.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    private int numberOfNodesEvaluated = 0;
    protected Stack<AState> stack;

    /** DepthFirstSearch constructor */
    public DepthFirstSearch() {
        super(DepthFirstSearch.class.getSimpleName());
        visitedCells = new HashSet<>();
        stack = new Stack<>();
    }

    /**
     * This Function Solves The Problem according to the DFS Algorithms:
     1. Start from the start Position and calls DFS procedure.
     2. Maintain a Stack to store the states and initialize it with the StartPosition.
     3. Maintain a Hash-set of the visited States.
     4. Loop till Stack is not empty
        4.1. Pop state from the Stack.
            4.1.1 Return if the goal state have reached.
        4.2  For each neighbors of the state:
            4.2.1 if they are not visited yet:
                - Enqueue it in the Stack.
                - Add the State to visited Hash-set.
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        // Check null input
        if (iSearchable == null) return null;

        // First State
        visitedCells.add(iSearchable.getStartState().toString());
        stack.add(iSearchable.getStartState());

        // Stack Checking
        while (!stack.empty()) {
            AState currState = stack.pop();
            if(currState.equals(iSearchable.getGoalState())){
                visitedCells.clear();
                return new Solution(iSearchable.getStartState(), currState);
            }
            for (AState a : iSearchable.getAllPossibleStates(currState)) {
                if (!visitedCells.contains(a.toString())){
                    visitedCells.add(currState.toString());
                    this.numberOfNodesEvaluated++;
                    stack.push(a);
                }
                if (a.getPrev() == null) a.setPrev(currState);
            }
        }
        visitedCells.clear();
        return new Solution();
    }

    /** @return The Number of nodes the Algorithm Evaluated during running */
    @Override
    public int getNumberOfNodesEvaluated() { return this.numberOfNodesEvaluated;}
}
