package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;


public class MyMazeGenerator extends AMazeGenerator{
    /**
     * generates a new Maze according to prim's algorithm
     *
     * @param rows - Num of rows in the maze
     * @param cols -Num of coulmns in the maze
     * @return Maze
     */

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
    1. Start with a grid full of walls. (done)
    2. Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
    3. While there are walls in the list:
        3.1 Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
            3.1.1 Make the wall a passage and mark the unvisited cell as part of the maze.
            3.1.2 Add the neighboring walls of the cell to the wall list.
        3.2 Remove the wall from the list.
    */
    private Maze Prim(Maze myMaze){
        FillGridWithWalls(myMaze);
        ArrayList<Position> walls = new ArrayList<>();
        //ArrayList<Position> cellMaze = new ArrayList<>();
        //Position firstCell = GetRandomCellOnEdge(myMaze.getRows(), myMaze.getCols());
        //myMaze.setValue(firstCell, 0);
        // TODO: getNeighbors() Function
        //walls.addAll(firstCell.getNeighbors());
        Random rand = new Random();
        myMaze.setStartPosition(new Position(0,rand.nextInt(myMaze.getCols())));
        walls.add(myMaze.getStartPosition());
        Position currentPos = new Position(0,0);

        //
        while (!walls.isEmpty()){

            if (walls.size() == 1) currentPos = walls.remove(0); //3.2
            else currentPos = walls.remove(rand.nextInt(walls.size()-1)); //3.2

            while(!HaveOneWall(myMaze,currentPos)){

                if (walls.size()>1) currentPos = walls.remove(rand.nextInt(walls.size()-1)); //3.2
                else {
                    if (walls.size() != 0) currentPos = walls.remove(0); //3.2
                    else break;
                }
            }
            myMaze.MakePath(currentPos); //3.1.1
            AddNeighborsOfPos(myMaze,currentPos,walls); //3.1.2
        }
        // TODO: need to set goal position and then return the maze

        return myMaze;
    }

    /**
     * Adds Neighbors Walls of the current Position to walls ArrayList
     * @param myMaze
     * @param currentPos
     * @param walls
     */

    private void AddNeighborsOfPos(Maze myMaze, Position currentPos, ArrayList<Position> walls) {
        if (walls!=null && !walls.isEmpty()){
            //Check if Up is wall
            if(myMaze.isWall(currentPos.getRowIndex()+1,currentPos.getColumnIndex())){
                Position Up = new Position(currentPos.getRowIndex()+1,currentPos.getColumnIndex());
                if(walls.contains(Up))walls.add(Up);
            }
            //Check if Down is wall
            if(myMaze.isWall(currentPos.getRowIndex()-1,currentPos.getColumnIndex())){
                Position Down = new Position(currentPos.getRowIndex()-1,currentPos.getColumnIndex());
                if(walls.contains(Down))walls.add(Down);
            }
            //Check if Right is wall
            if(myMaze.isWall(currentPos.getRowIndex(),currentPos.getColumnIndex()+1)){
                Position Right = new Position(currentPos.getRowIndex()+1,currentPos.getColumnIndex()+1);
                if(walls.contains(Right))walls.add(Right);
            }
            //Check if Left is wall
            if(myMaze.isWall(currentPos.getRowIndex(),currentPos.getColumnIndex()-1)){
                Position Left = new Position(currentPos.getRowIndex()+1,currentPos.getColumnIndex()-1);
                if(walls.contains(Left))walls.add(Left);
            }
        }
    }


    private boolean HaveOneWall(Maze myMaze, Position PosToCheck) {
        int wallCounter=0;
        if (myMaze.isValidCell(PosToCheck.getRowIndex(),PosToCheck.getColumnIndex()+1)) wallCounter++;
        if (myMaze.isValidCell(PosToCheck.getRowIndex(),PosToCheck.getColumnIndex()-1)) wallCounter++;
        if (myMaze.isValidCell(PosToCheck.getRowIndex()+1,PosToCheck.getColumnIndex())) wallCounter++;
        if (myMaze.isValidCell(PosToCheck.getRowIndex()-1,PosToCheck.getColumnIndex())) wallCounter++;
        if (wallCounter > 1) return false;
        return true;
    }

    /**
     * building walls(one's) all over the maze
     * @param myMaze
     */
    private void FillGridWithWalls(Maze myMaze){
        for (int i = 0; i < myMaze.getRows(); i++) {
            for (int j = 0; j < myMaze.getCols(); j++) {
                myMaze.setValue(i,j,1);
            }
        }
    }
}
