package algorithms.search;
import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }
    /**
     * Returns The Start State of the Maze
     */
    @Override
    public MazeState getStartState() {
        return new MazeState(maze.getStartPosition());
     }
    /**
     * Returns The Goal State of the Maze
     */
    @Override
    public MazeState getGoalState() {
        return new MazeState(maze.getGoalPosition());
    }

    /**
     * The Function receives a given state and returns a ArrayList of all possible states around the
     * given state(every direction including diagonal)
     * @param state
     * @return
     */
    @Override
    public ArrayList<MazeState> getAllPossibleStates(MazeState state) {
        ArrayList<MazeState> myList = new ArrayList<MazeState>();
        int row = state.getRowIndex();
        int col = state.getColIndex();

        boolean boolUp = false;
        boolean boolDown = false;
        boolean boolRight = false;
        boolean boolLeft = false;

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