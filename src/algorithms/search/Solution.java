package algorithms.search;
import java.util.ArrayList;

/**
 *  Solution Class: contain the solution of a problem (posses the Goal and Start States and can restore the path.
 *                  if the is no solution will posses empty solution
 */
public class Solution {
    private AState goalState;
    private AState startState;

    /** Parameters Constructor */
    public Solution(AState startState, AState goalState) {
        this.startState = startState;
        this.goalState = goalState;
    }

    /** Empty Constructor - for 'No Solution' */
    public Solution() {}

    /**
     * This Function returns a ArrayList of the solution path, each node is a AState Element
     * The First element is the start position and the last is the Goal position
     * @return Solution Path - ArrayList Of The AStates.
     */
    public ArrayList<AState> getSolutionPath(){
        ArrayList<AState> mySolution = new ArrayList<AState>();
        AState tmp = this.goalState;
        if (this.goalState==null || this.startState==null) return new ArrayList<AState>();
        while (tmp.getPosition()!=this.startState.getPosition()){
            mySolution.add(0,tmp);
            tmp = tmp.getPrev();
        }
        mySolution.add(0, startState);
        return mySolution;
    }
}
