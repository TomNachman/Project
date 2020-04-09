package algorithms.mazeGenerators;

import java.nio.ByteBuffer;

/**
 *  Class Maze: Represent the Maze itself - A Two Dimensional Array
 *              Contain: start position, goal position, paths(0's) and walls (1's)
 */
public class Maze{
    private int [][] myMaze;
    private int rows;
    private int cols;
    private Position startPosition;
    private Position goalPosition;


    /**
     * Maze Constructor: initiate the rows, cols, myMaze.
     * @param rows - num of rows to initiate in the maze
     * @param cols - num of cols to initiate in the maze
     */
    public Maze(int rows, int cols) {
        this.cols = cols;
        this.rows = rows;
        this.myMaze = new int[rows][cols];
        this.startPosition = null;
        this.goalPosition = null;
    }
    /**
     * Maze Constructor: Gets a uncompressed byte Array that represent the Maze
     * and creates a maze from the byte array
     * @param byteMaze
     */
    public Maze(byte [] byteMaze){
        byte [] Bcols = new byte[4];
        int pivot=0;
        for(int i=0 ; i < Bcols.length; i++){
            Bcols[i] = byteMaze[i];
            pivot++;
        }
        this.cols = ByteBuffer.wrap(Bcols).getInt();
        this.rows = (byteMaze.length-20)/this.cols;
    }

    /**
     * converting the maze to uncompressed byte array with all its data : size,content,start and goal
     * @return
     */
    public byte[] toByteArray(){
        int size = rows*cols +20;
        byte [] Bcols = ByteBuffer.allocate(4).putInt(cols).array();
        byte[] startY = ByteBuffer.allocate(4).putInt(startPosition.getColumnIndex()).array();
        byte[] startX = ByteBuffer.allocate(4).putInt(startPosition.getRowIndex()).array();
        byte[] goalY = ByteBuffer.allocate(4).putInt(goalPosition.getColumnIndex()).array();
        byte[] goalX = ByteBuffer.allocate(4).putInt(goalPosition.getRowIndex()).array();

        byte [] byteArray = new byte [size] ;
        int pivot=0;

        for (byte bcol : Bcols) {
            byteArray[pivot] = bcol;
            pivot++;
        }
        for (byte b : startY) {
            byteArray[pivot] = b;
            pivot++;
        }
        for (byte x : startX) {
            byteArray[pivot] = x;
            pivot++;
        }
        for (byte b : goalY) {
            byteArray[pivot] = b;
            pivot++;
        }
        for (byte x : goalX) {
            byteArray[pivot] = x;
            pivot++;
        }

        for(int i=pivot;i<byteArray.length;i++){ //copy all maze cells to the byteArray
            for (int j=0;j<rows;j++)
                for (int k=0;k<cols;k++) {
                    byteArray[i] = (byte) myMaze[j][k];
                    i++;
                }
        }
        return byteArray;

    }

    /** @return num of rows */
    public int getRows() {
        return rows;
    }

    /** @return num of columns */
    public int getCols() {
        return cols;
    }

    /** @return Position - the startPosition in the maze */
    public Position getStartPosition() {
        return startPosition;
    }

    /** @return Position - the GoalPosition in the maze */
    public Position getGoalPosition() {
        return goalPosition;
    }

    /** @param start - startPosition to set */
    public void setStartPosition(Position start){
        startPosition = start;
        myMaze[start.getRowIndex()][start.getColumnIndex()] = 0;
    }

    /** @param goal - goalPosition to set (only if all condition are true) */
    public void setGoalPosition(Position goal){
        if (validGoalPosition(goal) && GraterDistance(goal)) {
            this.goalPosition = goal;
        }
    }

    /**
     * Checks if a given Position 'p' valid - not in the same edge as the startPosition
     * @param p - the position we want to check if is valid
     * @return true - if the position is fix to be a goalPosition
     */
    private boolean validGoalPosition(Position p){
        if (rows==1 || cols ==1) return true;
        return !((getStartPosition().getRowIndex()==0 && p.getRowIndex()==0) ||
                (getStartPosition().getRowIndex()==getRows()-1 && p.getRowIndex()==getRows()-1) ||
                (getStartPosition().getColumnIndex()==0 && p.getColumnIndex()==0) ||
                (getStartPosition().getColumnIndex()==getCols()-1 && p.getColumnIndex()==getCols()-1));
    }

    /**
     * Check if the given position (p) is in a grater distance from the startPosition then the current goalPosition
     * @param p1 - the new position to check
     * @return true - if the new position grater then the currPosition (goalPosition)
     */
    private  boolean GraterDistance(Position p1){
        if(goalPosition==null){return true;}
        double distP1 = Math.sqrt(Math.pow(p1.getRowIndex()-startPosition.getRowIndex(),2) + Math.pow(p1.getColumnIndex()-startPosition.getColumnIndex(),2));
        double distP2 = Math.sqrt(Math.pow(goalPosition.getRowIndex()-startPosition.getRowIndex(),2) + Math.pow(goalPosition.getColumnIndex()-startPosition.getColumnIndex(),2));
        return (distP1>distP2);
    }

    /**
     * Check if a given position is on the edge of the Maze
     * @param pos - the position to check
     * @return true - if the pos on the edges
     */
    public boolean onEdges(Position pos){
        return ( pos.getRowIndex()==0    || pos.getRowIndex()==getRows()-1 ||
                pos.getColumnIndex()==0 || pos.getColumnIndex()==getCols()-1);
    }

    /**
     * Return if the cell is in boundaries of the Maze
     * @param row - Row number
     * @param col - Column number
     * @return True if the cell is valid.
     */
    public boolean isValidCell (int row , int col){
        return (row<this.rows && row>=0 && col<this.cols && col>=0);
    }

    /**
     * Return if the cell is part of the Path (white cell)
     * @param row - Row number
     * @param col - Column number
     * @return true - if the cell is part of the Path
     */
    public boolean isPartOfThePath (int row , int col){
        return (isValidCell(row,col) && myMaze[row][col] == 0);
    }


    /**
     * Return if the cell is part of the walls (black cell)
     * @param row - Row number
     * @param col - column number
     * @return true if the cell is a wall
     */
    public boolean isWall(int row,int col){
        return (isValidCell(row,col) && myMaze[row][col]==1);
    }

    /**
     * Set the value of the given position to a path (0)
     * @param pos - the position to change
     */
    public void MakePath(Position pos){
        if(isValidCell(pos.getRowIndex(), pos.getColumnIndex())){
            myMaze[pos.getRowIndex()][pos.getColumnIndex()] =0 ;
        }
    }

    /**
     *  Set the value of the given cell to a wall (1)
     * @param row - Row number
     * @param col - Column number
     */
    public void MakeWall(int row, int col){
        if(isValidCell(row, col)){
            myMaze[row][col] = 1;
        }
    }

    /** Print the Maze to the command */
    public void print(){
        if(this.getGoalPosition()==null) {
            goalPosition = new Position(getRows()-1, getCols()-1);
        }
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if(i == startPosition.getRowIndex()&& j == startPosition.getColumnIndex()){
                    System.out.print('S');
                }
                else if(i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()){
                    System.out.print('E');
                }
                else
                    System.out.print(myMaze[i][j]+"");
                if(j == this.cols-1){
                    System.out.println("");
                }
            }
        }
    }
}
