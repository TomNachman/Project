import algorithms.mazeGenerators.*;
import algorithms.search.MazeState;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        /**
        Position tmp = new Position(3,2);
        Position tmp1 = new Position(3,2);
        Position tmp2 = new Position(4,5);
        if(tmp.equals(tmp1)) System.out.println("it is true");
        if(tmp==tmp2) System.out.println("false");
        */

        Queue<MazeState> queue = new LinkedList<>();
        PriorityQueue<MazeState> priorityQueue = new PriorityQueue<MazeState>((Comparator.comparingInt(o -> o.getVal())));
        MazeState m1 = new MazeState(3,2);
        MazeState m2 = new MazeState(4,5);
        MazeState m3 = new MazeState(2,3);
        m1.setVal(1);
        m2.setVal(2);
        m3.setVal(3);

        priorityQueue.add(m1);
        priorityQueue.add(m3);
        priorityQueue.add(m2);

        queue.add(m1);
        queue.add(m3);
        queue.add(m2);

        MazeState tmp;
        System.out.println("Priority Queue");
        while (!priorityQueue.isEmpty()) {
            tmp = priorityQueue.poll();
            System.out.println(String.format("Pos :%s , val: %d", tmp.getPosition().toString(),tmp.getVal()));
        }

        System.out.println("Regular Queue");
        while (!queue.isEmpty()) {
            tmp = queue.remove();
            System.out.println(String.format("Pos :%s , val: %d", tmp.getPosition().toString(),tmp.getVal()));
        }
    }
}
