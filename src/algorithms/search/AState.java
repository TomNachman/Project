package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class AState {
    private int val = -1;
    private AState prev;
    private Position myPos;
    private boolean visited = false;

    public AState(Position position) {
        this.myPos = position;
    }

    public Position getPosition() {
        return myPos;
    }

    public int getVal() { return val;}

    public AState getPrev() {
        return prev;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setPrev(AState newPrev) {
        this.prev = newPrev;
        this.val = prev.getVal()+1;
    }

    @Override
    public String toString() {
        return myPos.toString();
    }
}

