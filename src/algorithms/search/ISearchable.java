package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<MazeState> getAllPossibleStates(MazeState state);
    MazeState getStartState();
    MazeState getGoalState();
}
