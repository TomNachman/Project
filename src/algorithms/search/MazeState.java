package algorithms.search;
import algorithms.mazeGenerators.Position;

/**
 *  MazeState Class: Extend AState and change the value of all maze state to be 10 (in the beginning)
 *  In that way we stay abstract in the AState class.
 */
public class MazeState extends AState{

    /** Parameter Constructor with a given Position */
    public MazeState(Position position) {
        super(position);
        val = 10;
    }

    /** Parameter Constructor with a Given Coordinates(generates a new Position) */
    public MazeState(int row, int col){
        super(new Position(row, col));
    }


    /**
     * Override Equal function: Between two Maze-States based on the position
     * @param o - the other mazeState to check equality with
     * @return boolean - true if have the same position, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeState mazeState = (MazeState) o;
        return getPosition().equals(mazeState.getPosition());
    }
}
