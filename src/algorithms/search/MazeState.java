package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState(Position position) {
        super(position);
    }

    public MazeState(int row, int col){
        super(new Position(row, col));
    }

    public int getRowIndex(){
        return this.getPosition().getRowIndex();
    }

    public int getColIndex(){
        return this.getPosition().getColumnIndex();
    }


}
