package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState(Position position) {
        super(position);
    }

    public boolean isBetterPrev(AState state){
        if(this.getVal()==-1) return true;
        return (state.getVal()+1 <this.getVal());
    }
}
