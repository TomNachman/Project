package algorithms.search;
import java.util.ArrayList;

public class Solution {
    private AState goalState;
    private AState startState;

    //Parameter Constructor
    public Solution(AState startState, AState goalState) {
        this.startState = startState;
        this.goalState = goalState;
    }

    //Empty Constructor
    public Solution() {}

    /**
     * This Function returns a ArrayList of the solution path
     * such that each node is a AState Element
     * The First element is the start position and the last is he Goal position
     * @return
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
