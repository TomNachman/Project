package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {

    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        maze.print();

        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher) {
        long startTime = System.currentTimeMillis();
        //Solve a searching problem with a searcher 
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
                //Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        System.out.println(String.format("Time in Seconds: %d", (endTime - startTime)/1000));
    }

}