package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<MazeState> getAllPossibleStates(MazeState state);
    MazeState getStartState();
    MazeState getGoalState();
    int getWhites();
}
