import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMazeGenerator;

public class Main {

    public static void main(String[] args) {
        Position tmp = new Position(3,2);
        Position tmp1 = new Position(3,2);
        Position tmp2 = new Position(4,5);
        if(tmp.equals(tmp1)) System.out.println("it is true");
        if(tmp==tmp2) System.out.println("false");
    }
}
