package algorithms.search;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  BreadthFirstSearch Class: This class uses the 'BFS algorithm' to solve the problem.
 *                            It Implements the algorithm data structure as a Queue.
 */

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected int numberOfNodesEvaluated = 0;
    protected Queue<AState> queue;

    /** Empty Constructor */
    public BreadthFirstSearch() {
        this(BreadthFirstSearch.class.getSimpleName());
        queue = new LinkedList<>();
    }

    /** Name based Constructor for Extend's classes */
    public BreadthFirstSearch(String name) {
        super(name);
        visitedCells = new HashSet<>();
    }

    /**
     * This Function Solves The Problem according to the BFS Algorithms:
     1. Start from the start Position and calls BFS procedure.
     2. Maintain a Queue to store the states and initialize it with the StartPosition.
     3. Maintain a Hash-set of the visited States.
     4. Loop till queue is not empty
        4.1. Dequeue front state from the queue.
            4.1.1 Return if the goal state have reached.
        4.2  For each neighbors of the state:
            4.2.1 if they are not visited yet:
                   - Enqueue it in the queue.
                   - Add the State to visited Hash-set.
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        // Check null input
        if (iSearchable == null) return null;

        // First State
        queue.add(iSearchable.getStartState());
        visitedCells.add(iSearchable.getStartState().toString());

        // Queue Checking
        while(!queue.isEmpty()){
            AState currState = queue.poll();
            if(currState.equals(iSearchable.getGoalState())){
                visitedCells.clear();
                return new Solution(iSearchable.getStartState(), currState);
            }
            for (AState a : iSearchable.getAllPossibleStates(currState)){
                if (!visitedCells.contains(a.toString())) {
                    visitedCells.add(a.toString());
                    this.numberOfNodesEvaluated++;
                    a.setVal(a.getVal()+currState.getVal());
                    queue.add(a);
                }
                if (a.getPrev() == null) a.setPrev(currState);
            }
        }
        visitedCells.clear();
        return new Solution();
    }

    /** @return The Number of nodes the Algorithm evaluated during running */
    @Override
    public int getNumberOfNodesEvaluated() { return this.numberOfNodesEvaluated; }
}
