package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable {
    private Maze maze;
    private List<MazeState> myStates = new ArrayList<MazeState>();

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        for(int i = 0; i<maze.getRows();i++){
            for(int j=0; j<maze.getCols();j++){
                if(maze.isPartOfThePath(i,j)) myStates.add(new MazeState(new Position(i,j)));
            }
        }
    }

    @Override
    public MazeState getStartState() {
        return myStates.stream().filter(ms->ms.getPosition().getRowIndex() == maze.getStartPosition().getRowIndex() &&
                                        ms.getPosition().getColumnIndex() == maze.getStartPosition().getColumnIndex()).findAny().orElse(null);
    }

    @Override
    public MazeState getGoalState() {
        return  myStates.stream().filter(ms->ms.getPosition().getRowIndex() == maze.getGoalPosition().getRowIndex() &&
                                        ms.getPosition().getColumnIndex() == maze.getGoalPosition().getColumnIndex()).findAny().orElse(null);
    }

    @Override
    public ArrayList<MazeState> getAllPossibleStates(MazeState state) {
        return GetAllNeighbors(state);

        //TODO: add diagonals!!!

        /**
        ArrayList<MazeState> myPossibleStates = GetAllNeighbors(state);
        return myPossibleStates;
        */
    }

    private ArrayList<MazeState> GetAllNeighbors(MazeState state){
        ArrayList<MazeState> myList = new ArrayList<MazeState>();
        System.out.print("My state is: ");
        System.out.println(state.getPosition());


        for(int i= state.getPosition().getRowIndex()-1; i <= state.getPosition().getRowIndex()+1; i++){
            for(int j= state.getPosition().getColumnIndex()-1; j <= state.getPosition().getColumnIndex()+1; j++) {
                Position pos = new Position(i,j);
                if(maze.isPartOfThePath(pos.getRowIndex(), pos.getColumnIndex()) &&
                        !(pos.getRowIndex() == state.getPosition().getRowIndex() && pos.getColumnIndex() == state.getPosition().getColumnIndex()))
                {
                    MazeState m = myStates.stream().filter(ms->ms.getPosition().getRowIndex() == pos.getRowIndex() &&
                                                            ms.getPosition().getColumnIndex() == pos.getColumnIndex()).findAny().orElse(null);
                    myList.add(m);
                }
            }
        }
        /**
        for (MazeState ms:myStates.stream().filter(a->Math.abs(a.getPosition().getRowIndex()-state.getPosition().getRowIndex()))){
            if((Math.sqrt(Math.pow(ms.getPosition().getRowIndex() - state.getPosition().getRowIndex(),2) +
                Math.pow(ms.getPosition().getColumnIndex() - state.getPosition().getColumnIndex(),2)) ==1) &&
                (maze.isPartOfThePath(ms.getPosition().getRowIndex(), ms.getPosition().getColumnIndex())) &&
                (!myList.contains(ms)) && (ms.getPrev()==null || !ms.getPrev().equals(state)))
            {
                System.out.print("My Neighbor is: ");
                System.out.println(ms.getPosition());
                myList.add(ms);
            }
        }
         */
        return myList;
    }
}
