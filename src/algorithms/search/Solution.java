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
        System.out.println("My Solution Path:");
        ArrayList<AState> mySolution = new ArrayList<AState>();
        AState tmp = this.goalState;
        System.out.println("Goal Val is:");
        System.out.println(tmp.getVal());
        while (tmp!=this.startState){
            System.out.println("While: ");
            System.out.println(tmp.getPosition());
            mySolution.add(tmp);
            System.out.println("Before: ");
            tmp = tmp.getPrev();
            System.out.println("After: ");
        }
        return mySolution;
    }
}
