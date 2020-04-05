package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    /**
     * Parameter Constructor with a given Position
     */
    public MazeState(Position position) {
        super(position);
    }
    /**
     * Parameter Constructor with a Given Coordinates(generates a new Position)
     */
    public MazeState(int row, int col){
        super(new Position(row, col));
    }
    /**
     * Returns The Row Index of the current Position
     */
    public int getRowIndex(){
        return this.getPosition().getRowIndex();
    }
    /**
     * Returns The Column Index of the current Position
     */
    public int getColIndex(){
        return this.getPosition().getColumnIndex();
    }


}
