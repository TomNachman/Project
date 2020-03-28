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
        this.goalPosition = null;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setStartPosition(Position s){ startPosition=s; }

    public void setGoalPosition(Position tryPos){
        if (validGoalPosition(tryPos)){
            if(GraterDistance(tryPos)) this.goalPosition = tryPos;
        }
    }

    private  boolean validGoalPosition(Position p){
        if((getStartPosition().getRowIndex()==0 && p.getRowIndex()==0) ||
           (getStartPosition().getRowIndex()==getRows()-1 && p.getRowIndex()==getRows()-1) ||
           (getStartPosition().getColumnIndex()==0 && p.getColumnIndex()==0) ||
           (getStartPosition().getColumnIndex()==getCols()-1 && p.getColumnIndex()==getCols()-1))
            {return false;}
        else return true;
    }

    public boolean GraterDistance(Position p1){
        if(goalPosition==null){return true;}
        double distP1 = Math.sqrt(Math.pow(p1.getRowIndex()-startPosition.getRowIndex(),2) + Math.pow(p1.getColumnIndex()-startPosition.getColumnIndex(),2));
        double distP2 = Math.sqrt(Math.pow(goalPosition.getRowIndex()-startPosition.getRowIndex(),2) + Math.pow(goalPosition.getColumnIndex()-startPosition.getColumnIndex(),2));
        if(distP1>distP2) return true;
        return false;
    }

    public boolean onEdges(Position pos){
        if( pos.getRowIndex()==0    || pos.getRowIndex()==getRows()-1 ||
            pos.getColumnIndex()==0 || pos.getColumnIndex()==getCols()-1){
            return true;
        }
        return false;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getMyMaze() {
        return myMaze;
    }


    /**
     * Return if the cell is in boundaries and if it is not a Wall.
     * @param row - Row number
     * @param col - Column number
     * @return True if the cell is valid. False if not.
     */
    private boolean isValidCell (int row , int col){
        if(row<this.rows && row>=0 && col<this.cols && col>=0)
            return true;
        return false;
    }

    public boolean isPartOfThePath (int row , int col){
        if(isValidCell(row,col))
            if(myMaze[row][col] == 0)
                return true;
        return false;
    }

    public boolean isWall(int row,int col){
        if(isValidCell(row,col)){
            if (myMaze[row][col]==1)
                return true ;
        }
        return false;
    }

    /**
     * Set the value of the given position to zero
     * @param pos
     */
    public void MakePath(Position pos){
        if(isValidCell(pos.getRowIndex(), pos.getColumnIndex())){
            myMaze[pos.getRowIndex()][pos.getColumnIndex()] =0 ;
        }
    }

    public void MakeWall(Position pos){
        if(isValidCell(pos.getRowIndex(), pos.getColumnIndex())){
            myMaze[pos.getRowIndex()][pos.getColumnIndex()] =1 ;
        }
    }

    public void MakeWall(int row, int col){
        if(isValidCell(row, col)){
            myMaze[row][col] = 1;
        }
    }


    public void print(){
        if(this.getGoalPosition()==null) {goalPosition = new Position(getRows()-1, getCols()-1);}
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
