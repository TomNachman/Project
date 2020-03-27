package algorithms.mazeGenerators;

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

    public Collection<Position> getNeighbors(){
        Collection<Position> myNeighbors = null;
        return myNeighbors;
    }
}