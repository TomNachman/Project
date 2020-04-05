package algorithms.search;
import java.util.ArrayList;

/**
 *  Searchable Interface
 *  Methods that need to be implemented:
 *      1. getAllPossibleStates
 *      2. getStartState
 *      3. getGoalState
 */
public interface ISearchable {
    ArrayList<AState> getAllPossibleStates(AState state);
    AState getStartState();
    AState getGoalState();
}
