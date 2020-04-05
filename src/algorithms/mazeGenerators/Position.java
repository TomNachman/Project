package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    /**
     * Returns The Row index
     * @return
     */
    public int getRowIndex() {
        return x;
    }
    /**
     * Returns The Column index
     * @return
     */
    public int getColumnIndex() {
        return y;
    }
    /**
     * Set The Row index
     * @return
     */
    public void setRow(int x) {
        this.x = x;
    }
    /**
     *Set The Column index
     * @return
     */
    public void setCol(int y) {
        this.y = y;
    }

    /**
     * The Function returns a collection of al the walls surrounding a position
     * this function helps to implement stage 3.1.2 of Prim Algorithm
     * @param maze
     * @return
     */
    public Collection<Position> getWallNeighbors(Maze maze){
        ArrayList<Position> myNeighbors = new ArrayList<Position>();
        if(maze.isWall(getRowIndex()+1, getColumnIndex())) myNeighbors.add(new Position(getRowIndex()+1, getColumnIndex()));
        if(maze.isWall(getRowIndex()-1, getColumnIndex())) myNeighbors.add(new Position(getRowIndex()-1, getColumnIndex()));
        if(maze.isWall(getRowIndex(), getColumnIndex()+1)) myNeighbors.add(new Position(getRowIndex(), getColumnIndex()+1));
        if(maze.isWall(getRowIndex(), getColumnIndex()-1)) myNeighbors.add(new Position(getRowIndex(), getColumnIndex()-1));
        return myNeighbors;
    }

    /**
     * equal function between two position based on the position coordinates
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
}