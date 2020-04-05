package JUnit;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class JUnitTestingBestFirstSearch {
    //Checking Null input
    @Test
    public void NullTest(){
        ISearchingAlgorithm Best = new BestFirstSearch();
        Assertions.assertNull(Best.solve(null));
    }
    //Checking getName method
    @Test
    public void NameTest(){
        ISearchingAlgorithm Best = new BestFirstSearch();
        Assertions.assertEquals("BestFirstSearch",Best.getName());
    }
    //Checking BestFS on empty Maze(should be always the diagonal)
    @Test
    public void EmptyMazeTest(){
        ISearchingAlgorithm Best = new BestFirstSearch();
        IMazeGenerator mg = new EmptyMazeGenerator();
        Maze maze;
        int count = 0;
        for (int i=10;i<=100;i+=10){
            maze = mg.generate(i, i);
            maze.setStartPosition(new Position(0,0));
            maze.setGoalPosition(new Position(i-1,i-1));
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            Solution solution = Best.solve(searchableMaze);
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            if (solutionPath.size() == i) count++;
        }
        Assertions.assertEquals(10,count);
    }
    //Checking BestFS for a Maze Without a Solution
    @Test
    public void NoSolutionTest() {
        ISearchingAlgorithm Best = new BestFirstSearch();
        IMazeGenerator mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(5,5);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(4,4));
        maze.MakeWall(4,3); //Blocking the End
        maze.MakeWall(3,4); //Blocking the End
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = Best.solve(searchableMaze);
        Solution EmptySolution = new Solution();
        Assertions.assertEquals(EmptySolution.getSolutionPath().isEmpty(),solution.getSolutionPath().isEmpty());
    }
}