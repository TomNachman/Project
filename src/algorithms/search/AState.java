package algorithms.search;
import algorithms.mazeGenerators.Position;

/**
 * Abstract Class AState: Represent a State of a Position (his value, prevCell, Position)
 */
public abstract class AState {
    protected int val;
    private AState prev;
    private Position myPos;

    /** Parameter Constructor */
    public AState(Position position) {
        this.myPos = position;
    }

    /**@return the Value of the Position */
    public int getVal() { return val;}

    /** Set the value of the current position */
    public void setVal(int val) { this.val = val; }

    /** @return the Previous Positions of the current position (if not exist - null) */
    public AState getPrev() {return prev; }

    /** Set a Previous AState to the current AState */
    public void setPrev(AState newPrev) { this.prev = newPrev; }

    /** @return the Position of the current AState */
    public Position getPosition() { return myPos; }

    /** Override the toString method
     * @return a String Represent the position of the State */
    @Override
    public String toString() {return myPos.toString();}

    /** abstract function, need to be implemented by the implemented class */
    public abstract boolean equals(Object o);
}

