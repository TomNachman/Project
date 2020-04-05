package algorithms.search;
import algorithms.mazeGenerators.Maze;
import java.util.ArrayList;

/**
 * SearchableMaze Class: Make a Maze Searchable
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;

    /** Searchable Maze single Constructor */
    public SearchableMaze(Maze maze) { this.maze = maze; }

    /** Returns The Start State of the Maze */
    @Override
    public AState getStartState() { return new MazeState(maze.getStartPosition()); }

    /** Returns The Goal State of the Maze */
    @Override
    public AState getGoalState() { return new MazeState(maze.getGoalPosition()); }

    /**
     * The Function receives a state and returns a ArrayList of all Neighbors states (including diagonals).
     * @param state - The Current State We Want To Check
     * @return All The Neighbors States
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> myList = new ArrayList<AState>();
        int row = state.getPosition().getRowIndex();
        int col = state.getPosition().getColumnIndex();
        boolean boolUp = false, boolDown = false, boolRight = false, boolLeft = false;

        // Up
        if(maze.isPartOfThePath(row-1, col)) {
            boolUp = true;
            myList.add(new MazeState(row - 1, col));
        }

        // Right
        if(maze.isPartOfThePath(row, col+1)) {
            boolRight = true;
            myList.add(new MazeState(row, col+1));
        }

        // Down
        if(maze.isPartOfThePath(row+1, col)) {
            boolDown = true;
            myList.add(new MazeState(row+1, col));
        }

        // Left
        if(maze.isPartOfThePath(row, col-1)) {
            boolLeft = true;
            myList.add(new MazeState(row, col-1));
        }

        // UpRight
        if(maze.isPartOfThePath(row-1, col+1) && (boolUp||boolRight)) {
            MazeState tmp = new MazeState(row-1, col+1);
            tmp.setVal(15);
            myList.add(tmp);
        }

        // DownRight
        if(maze.isPartOfThePath(row+1, col+1) && (boolDown||boolRight)) {
            MazeState tmp = new MazeState(row+1, col+1);
            tmp.setVal(15);
            myList.add(tmp);
        }

        // DownLeft
        if(maze.isPartOfThePath(row+1, col-1) && (boolDown||boolLeft)) {
            MazeState tmp = new MazeState(row+1, col-1);
            tmp.setVal(15);
            myList.add(tmp);
        }

        // UpLeft
        if(maze.isPartOfThePath(row-1, col-1) && (boolUp||boolLeft)) {
            MazeState tmp = new MazeState(row-1, col-1);
            tmp.setVal(15);
            myList.add(tmp);
        }
        return myList;
    }
}