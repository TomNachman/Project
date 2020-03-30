package algorithms.search;
import java.util.ArrayList;

public class Solution {
    private AState goalState;
    private AState startState;

    public Solution(AState startState, AState goalState) {
        this.startState = startState;
        this.goalState = goalState;
    }

    public ArrayList<AState> getSolutionPath(){
        ArrayList<AState> mySolution = new ArrayList<AState>();
        AState tmp = this.goalState;
        while (tmp.getPosition()!=this.startState.getPosition()){
            mySolution.add(0,tmp);
            tmp = tmp.getPrev();
        }
        mySolution.add(0, startState);
        return mySolution;
    }
}
