package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Comparator;

public abstract class AState {
    protected int val = 0;
    private AState prev;
    private Position myPos;

    public AState(Position position) {
        this.myPos = position;
    }

    public Position getPosition() {
        return myPos;
    }

    public int getVal() { return val;}

    public AState getPrev() {return prev; }

    public void setVal(int val) { this.val = val; }

    public void setPrev(AState newPrev) { this.prev = newPrev; }

    @Override
    public String toString() { return myPos.toString(); }
}

