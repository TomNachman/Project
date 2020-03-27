package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        Maze myMaze = new Maze(rows, cols);
        myMaze = Prim(myMaze);
        return myMaze;
    }

    private Position GetRandomCellOnEdge(int row, int col){
        Random rand = new Random();
        int myRow = rand.nextInt(row-1);
        int myCol = (int)Math.round(Math.random())*col;
        if (myRow==0 || myRow==row-1){
            myCol = rand.nextInt(row-1);
        }
        return new Position(myRow, myCol);
    }

    /**
    This algorithm is a randomized version of Prim's algorithm:
    1. Start with a grid full of walls.
    2. Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
    3. While there are walls in the list:
        3.1 Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
            3.1.1 Make the wall a passage and mark the unvisited cell as part of the maze.
            3.1.2 Add the neighboring walls of the cell to the wall list.
        3.2 Remove the wall from the list.
    */
    private Maze Prim(Maze myMaze){
        for (int i = 0; i < myMaze.getRows(); i++) {
            for (int j = 0; j < myMaze.getCols(); j++) {
                myMaze.setValue(i,j,1);
            }
        }

        ArrayList<Position> walls = new ArrayList<>();
        ArrayList<Position> cellMaze = new ArrayList<>();
        Position firstCell = GetRandomCellOnEdge(myMaze.getRows(), myMaze.getCols());
        myMaze.setValue(firstCell, 0);
        // TODO: getNeighbors() Function
        walls.addAll(firstCell.getNeighbors());
        Random rand = new Random();
        while (!walls.isEmpty()){
            walls.get(rand.nextInt(walls.size()));
        }

        return null;
    }
}
