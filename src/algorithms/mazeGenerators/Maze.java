package algorithms.mazeGenerators;

public class Maze {
    private int [][] myMaze;
    private int rows;
    private int cols;
    private Position startPosition;
    private Position goalPosition;


    public Maze(int rows, int cols) {
        this.cols = cols;
        this.rows = rows;
        this.myMaze = new int[rows][cols];
        this.startPosition = new Position(0,0);
        this.goalPosition = new Position(rows-1,cols-1);
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setStartPosition(Position s){ startPosition=s; }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getMyMaze() {
        return myMaze;
    }

    public void setValue(int row, int col, int val){
        myMaze[row][col] = val;
    }

    public void setValue(Position position, int val){
        myMaze[position.getRowIndex()][position.getColumnIndex()] = val;
    }
    /**
     * Return if the cell is in boundaries and if it is not a Wall.
     * @param i - Row number
     * @param j - Column number
     * @return True if the cell is valid. False if not.
     */
    public boolean isValidCell (int i , int j){
        if(i<rows && i>=0 && j<cols && j>=0)
            if(myMaze[i][j] == 0)
                return true;
        return false;
    }

    /**
     * Set the value of the given position to zero
     * @param pos
     */
    public void MakePath(Position pos){
        if(pos.getRowIndex() < rows && pos.getRowIndex() >=0 && pos.getColumnIndex()<cols && pos.getColumnIndex()>=0){
            myMaze[pos.getRowIndex()][pos.getColumnIndex()] =0 ;
        }
    }
    public boolean isWall(int row,int col){
        if(row < rows && row >=0 && col<cols && col>=0){
            if (myMaze[col][row]==1)return true ;
        }
        return false;
    }

    public void print(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if(i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()){
                    System.out.print('S');
                }
                else if(i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()){
                    System.out.print('E');
                }
                else
                    System.out.print(myMaze[i][j]+"");
                if(j == this.rows-1){
                    System.out.println("");
                }
            }
        }
    }
}
