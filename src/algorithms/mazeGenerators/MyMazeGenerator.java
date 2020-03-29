package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * MyMazeGenerator - Class of Maze Based on Smart Algorithm.
 * @author Asaf Salomon and Tom Nachman
 */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Generates a new Maze according to prim's algorithm
     * @param rows - Num of rows in the maze
     * @param cols - Num of columns in the maze
     * @return Complete Smart Maze
     */
    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        Maze myMaze = new Maze(rows, cols);
        myMaze = Prim(myMaze);
        return myMaze;
    }

    /**
     * Chose random position on the edges of a given maze boundaries
     * @param row - Num of rows in the maze
     * @param col - Num of columns in the maze
     * @return Valid Position on the edge
     */
    private Position GetRandomCellOnEdge(int row, int col){
        Random rand = new Random();
        int myRow;
        int myCol;
        while(true) {
            myRow = rand.nextInt(row); //0 to range-1
            myCol = ((int) Math.round(Math.random())) * (col - 1); // 0 *or* range-1
            if (myRow == 0 || myRow == row - 1) {
                myCol = rand.nextInt(col);
            }
            if(myRow!=row-1 || myCol!=col-1) break;
        }
        return new Position(myRow, myCol);
    }

    /**
     * This algorithm is a randomized version of Prim's algorithm:
     *   1. Start with a grid full of walls. (done)
     *   2. Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
     *   3. While there are walls in the list:
     *      3.1 Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
     *          3.1.1 Make the wall a passage and mark the unvisited cell as part of the maze.
     *          3.1.2 Add the neighboring walls of the cell to the wall list.
     *      3.2 Remove the wall from the list.
     * @return - Prim Based Maze
     */
    private Maze Prim(Maze myMaze){
        FillGridWithWalls(myMaze); // 1.
        myMaze.setStartPosition(GetRandomCellOnEdge(myMaze.getRows(), myMaze.getCols())); // 2.
        ArrayList<Position> walls = new ArrayList<>(myMaze.getStartPosition().getWallNeighbors(myMaze)); // 1. + 2.

        Position currentWall;
        Random rand = new Random();
        int changeGoal = 0;

        while (!walls.isEmpty()){ // 3.
            if(walls.size()==1) {currentWall = walls.remove(0);}
            else {currentWall = walls.remove(rand.nextInt(walls.size()));} //3.1
            if(OnlyOneNeighborVisited(myMaze,currentWall)){ // 3.1
                myMaze.MakePath(currentWall); //3.1.1
                walls.addAll(currentWall.getWallNeighbors(myMaze)); // 3.1.2
                if(myMaze.getChangeGoal() <=2 && myMaze.onEdges(currentWall)) {
                    System.out.println("i get in if");
                    myMaze.setGoalPosition(currentWall);
                }
            }
            walls.remove(currentWall);
        }
        return myMaze;
    }

    /**
     * Check if a given Position have exactly 1 visited neighbor
     * @param myMaze - the maze
     * @param PosToCheck - the position we want to check
     * @return true - if the given position have exactly 1 visited neighbor
     */
    private boolean OnlyOneNeighborVisited(Maze myMaze, Position PosToCheck) {
        int wallCounter=0;
        if (myMaze.isPartOfThePath(PosToCheck.getRowIndex(),PosToCheck.getColumnIndex()+1)) wallCounter++;
        if (myMaze.isPartOfThePath(PosToCheck.getRowIndex(),PosToCheck.getColumnIndex()-1)) wallCounter++;
        if (myMaze.isPartOfThePath(PosToCheck.getRowIndex()+1,PosToCheck.getColumnIndex())) wallCounter++;
        if (myMaze.isPartOfThePath(PosToCheck.getRowIndex()-1,PosToCheck.getColumnIndex())) wallCounter++;
        if (wallCounter > 1) return false;
        return true;
    }

    /**
     * filling the whole maze with walls (1's)
     * @param myMaze
     */
    private void FillGridWithWalls(Maze myMaze){
        for (int i = 0; i < myMaze.getRows(); i++) {
            for (int j = 0; j < myMaze.getCols(); j++) {
                myMaze.MakeWall(i,j);
            }
        }
    }
}
