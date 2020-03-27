package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int cols) {
        if(rows<=0 || cols <=0) return null;
        Maze mySimpleMaze = new Maze(rows, cols);
        for(int i=0;i<mySimpleMaze.getRows();i++){
            for(int j=0;j<mySimpleMaze.getCols();j++){
                mySimpleMaze.getMyMaze()[i][j] = (int)Math.round(Math.random());
            }
        }
        return mySimpleMaze;
    }

    /**
    @Override
    public Maze generate(int rows, int cols) {
        if (rows<=0 || cols <=0 ) return null;
        Maze mymaze = new Maze(rows,cols);
        int start = (int)(Math.random()*rows)
        mymaze.setStartPostion(start,0) // חושב שככה?
        int end = (int)(Math.random()*rows);
        mymaze.setGoalPosition(end,cols-1);
        for(int i=0; i<rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i != start) {
                    mymaze.getMyMaze()[i][j] = (int)Math.round(Math.random());
                }
            }
        }

        return mymaze;
    }*/
}
