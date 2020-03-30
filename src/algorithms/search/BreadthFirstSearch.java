package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected int numberOfNodesEvaluated = 0;

    // Empty Constructor
    public BreadthFirstSearch() {
        super(BreadthFirstSearch.class.getSimpleName());
    }

    // name Based Constructor for Extend's classes
    public BreadthFirstSearch(String name) {
        super(name);
    }


    @Override
    public Solution solve(ISearchable iSearchable) {
        Queue<MazeState> queue = new LinkedList<>();
        System.out.println("BFS");
        return SolutionHelper(iSearchable, queue);
    }

    protected Solution solve(ISearchable iSearchable, Queue<MazeState> queue) {
        System.out.println("Best-FS");
        return SolutionHelper(iSearchable, queue);
    }

    protected Solution SolutionHelper(ISearchable iSearchable,  Queue<MazeState> queue)
    {
        boolean flag = false;
        HashSet<String> visitedCells = new HashSet<>();
        queue.add(iSearchable.getStartState());
        visitedCells.add(iSearchable.getStartState().toString());
        iSearchable.getStartState().setPrev(iSearchable.getStartState());
        while(!queue.isEmpty()){
            MazeState curr = queue.remove();
            if(curr.getPosition().equals(iSearchable.getGoalState().getPosition())){
                return new Solution(iSearchable.getStartState(), curr);
            }
            for (MazeState a : iSearchable.getAllPossibleStates(curr)){
                if (!visitedCells.contains(a.toString())) {
                    visitedCells.add(a.toString());
                    queue.add(a);
                    this.numberOfNodesEvaluated++;
                }
                if (a.getPrev() == null) a.setPrev(curr);
            }
            if(!flag) {
                for (MazeState a:queue){
                    System.out.println(String.format("element: %s, Value:%d ",a.getPosition(), a.getVal()));
                }
                flag = true;
            }
        }
        return new Solution(iSearchable.getStartState(), iSearchable.getGoalState());
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }
}
