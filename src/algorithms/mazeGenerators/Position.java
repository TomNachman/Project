package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class that represents the current position in a maze with x(row) and y(col) coordinates.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("{%d,%d}",x,y);
    }

    public int getRowIndex() {
        return x;
    }

    public int getColumnIndex() {
        return y;
    }

    public Collection<Position> getWallNeighbors(Maze maze){
        ArrayList<Position> myNeighbors = new ArrayList<Position>();
        if(maze.isWall(getRowIndex()+1, getColumnIndex())) myNeighbors.add(new Position(getRowIndex()+1, getColumnIndex()));
        if(maze.isWall(getRowIndex()-1, getColumnIndex())) myNeighbors.add(new Position(getRowIndex()-1, getColumnIndex()));
        if(maze.isWall(getRowIndex(), getColumnIndex()+1)) myNeighbors.add(new Position(getRowIndex(), getColumnIndex()+1));
        if(maze.isWall(getRowIndex(), getColumnIndex()-1)) myNeighbors.add(new Position(getRowIndex(), getColumnIndex()-1));
        return myNeighbors;
    }
}