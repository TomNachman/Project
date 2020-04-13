package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Position Class: Represents the current position in a maze with x(row) and y(col) coordinates.
 */
public class Position implements Serializable {
    private int x;
    private int y;

    /**
     * Position Constructor
     * @param x - number of rows
     * @param y - number of columns
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**@return The Row index */
    public int getRowIndex() {
        return x;
    }

    /** @return The Column index */
    public int getColumnIndex() {
        return y;
    }

    /**
     * Override the toString method
     * @return String that represent the position
     */
    @Override
    public String toString() {
        return String.format("{%d,%d}",x,y);
    }

    /**
     * Override Equal function: Between two position based on the position coordinates
     * @param o - the other position to check equality with
     * @return boolean - true if in the same position, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * The Function returns a collection of all the walls surrounding a position
     * this function helps to implement stage 3.1.2 of Prim Algorithm
     * @param maze
     * @return The Walls Neighbors of the current position
     */
    public Collection<Position> getWallNeighbors(Maze maze){
        ArrayList<Position> myNeighbors = new ArrayList<Position>();
        if(maze.isWall(getRowIndex()+1, getColumnIndex())) myNeighbors.add(new Position(getRowIndex()+1, getColumnIndex()));
        if(maze.isWall(getRowIndex()-1, getColumnIndex())) myNeighbors.add(new Position(getRowIndex()-1, getColumnIndex()));
        if(maze.isWall(getRowIndex(), getColumnIndex()+1)) myNeighbors.add(new Position(getRowIndex(), getColumnIndex()+1));
        if(maze.isWall(getRowIndex(), getColumnIndex()-1)) myNeighbors.add(new Position(getRowIndex(), getColumnIndex()-1));
        return myNeighbors;
    }
}