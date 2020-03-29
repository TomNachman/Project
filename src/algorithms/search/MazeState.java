package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState(Position position) {
        super(position);
    }

    public boolean isBetterPrev(AState state){
        if(getPrev()==null) return true;
        return (state.getVal()+1 < this.getVal());
    }
}
