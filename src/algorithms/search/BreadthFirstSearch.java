package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected int numberOfNodesEvaluated = 0;

    // Empty Constructor
    public BreadthFirstSearch() {
        super(BreadthFirstSearch.class.getSimpleName());
        queue = new LinkedList<>();
        visitedCells = new HashSet<>();
    }

    // name Based Constructor for Extend's classes
    public BreadthFirstSearch(String name) {
        super(name);
    }
    /**
     * This Function Solves The Maze according to the BFS Algorithms:
     1.We start from the Start Position and calls BFS procedure.
     2.We maintain a queue to store the coordinates of the Maze and initialize it with the Start cell.
     3.We also maintain a Hashset array of the visited cells.
     4.We LOOP till queue is not empty
         4.1. Dequeue front cell from the queue
         4.2. Return if the destination coordinates have reached.
         4.3 For each of its four adjacent cells, if thery are not visited yet, we enqueue it in the queue and also
             adding the, to the visited Cell Hashset.
     */
    @Override
    public Solution solve(ISearchable iSearchable) {
        if (iSearchable == null) return null;
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

        return new Solution();
    }
    /**
     * @return The Number of nodes the Alg Evaluated during the run
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }
}
