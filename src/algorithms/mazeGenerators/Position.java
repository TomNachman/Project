package algorithms.mazeGenerators;

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

    public int getRowIndex() {
        return x;
    }

    public int getColumnIndex() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%d,%d}",x,y);
    }
}