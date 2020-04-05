package algorithms.search;

import algorithms.mazeGenerators.Position;


public abstract class AState {
    protected int val = 10;
    private AState prev;
    private Position myPos;

    /**
    Parameter Constructor
     */
    public AState(Position position) {
        this.myPos = position;
    }

    /**
     * @return the Position of the current AState
     */
    public Position getPosition() {
        return myPos;
    }
    /**
     * @return the Value of the Position
     */
    public int getVal() { return val;}
    /**
     * @return the Previous Positions of the current position
     * if the current positions is not set the function will return null
     */
    public AState getPrev() {return prev; }
    /**
     * Setting the value of the current position
     */
    public void setVal(int val) { this.val = val; }
    /**
     * Setting a Previous AState to the current AState
     */
    public void setPrev(AState newPrev) { this.prev = newPrev; }

    @Override
    public String toString() { return myPos.toString(); }
}

