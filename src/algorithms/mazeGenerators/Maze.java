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
