package algorithms.search;
import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public MazeState getStartState() {
        //return myStates.stream().filter(ms->ms.getPosition().equals(maze.getStartPosition())).findAny().orElse(null);
        return new MazeState(maze.getStartPosition());
     }

    @Override
    public MazeState getGoalState() {
        //return myStates.stream().filter(ms->ms.getPosition().equals(maze.getGoalPosition())).findAny().orElse(null);
        return new MazeState(maze.getGoalPosition());
    }

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
            myList.add(new MazeState(row-1, col));
        }

        // Right
        if(maze.isPartOfThePath(row, col+1)) {
            boolRight = true;
            myList.add(new MazeState(row, col +1));
        }

        // UpRight
        if(maze.isPartOfThePath(row-1, col+1) && (boolUp||boolRight)) {
            MazeState tmp = new MazeState(row-1, col+1);
            //tmp.setVal(1);
            myList.add(new MazeState(row-1, col+1));
        }

        // Down
        if(maze.isPartOfThePath(row+1, col)) {
            boolDown = true;
            myList.add(new MazeState(row + 1, col));
        }

        // DownRight
        if(maze.isPartOfThePath(row+1, col+1) && (boolDown||boolRight)) {
            myList.add(new MazeState(row+1, col+1));
        }

        // Left
        if(maze.isPartOfThePath(row, col-1)) {
            boolLeft = true;
            myList.add(new MazeState(row , col-1));
        }

        // DownLeft
        if(maze.isPartOfThePath(row+1, col-1) && (boolDown||boolLeft)) {
            myList.add(new MazeState(row+1, col-1));
        }

        // UpLeft
        if(maze.isPartOfThePath(row-1, col-1) && (boolUp||boolLeft)) {
            myList.add(new MazeState(row-1, col-1));
        }

        return myList;
    }
}