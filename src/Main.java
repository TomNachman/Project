import algorithms.mazeGenerators.*;
import algorithms.search.MazeState;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Position tmp = new Position(3,2);
        Position tmp1 = new Position(3,2);
        Position tmp2 = new Position(4,5);
        if(tmp.equals(tmp1)) System.out.println("it is true");
        if(tmp==tmp2) System.out.println("false");
        PriorityQueue<MazeState> PQCopy = new PriorityQueue<MazeState>((Comparator.comparingInt(o -> o.getVal()*-1)));
        MazeState m1 = new MazeState(3,2);
        MazeState m2 = new MazeState(4,5);
        MazeState m3 = new MazeState(2,3);
        m1.setVal(1);
        m2.setVal(2);
        m3.setVal(3);
        PQCopy.add(m1);
        PQCopy.add(m3);
        PQCopy.add(m2);
        MazeState tmp3;
        while (!PQCopy.isEmpty()) {
            tmp3 = PQCopy.remove();
            System.out.println(String.format("Pos :%s , val: %d", tmp3.getPosition().toString(),tmp3.getVal()));
        }




    }
}
