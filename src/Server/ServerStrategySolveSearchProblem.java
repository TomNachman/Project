package Server;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private static ConcurrentHashMap<Maze, String> hashMap = new ConcurrentHashMap<>();;
    private String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    private int counter;

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream MazeInputStream = new ObjectInputStream(inFromClient);
            String sol = Server.Configurations.getSolutionMethod();
            ObjectOutputStream SolutionOutputStream = new ObjectOutputStream(outToClient);

            try {
                Class<?> SA = Class.forName("algorithms.search." + sol);
                ISearchingAlgorithm searchingAlgorithm = (ISearchingAlgorithm) SA.newInstance();

                Maze maze = (Maze)MazeInputStream.readObject();
                if(hashMap.containsKey(maze)){
                    Solution s = readSolutionFromFile(hashMap.get(maze));
                    SolutionOutputStream.writeObject(s);
                    SolutionOutputStream.flush();
                    //System.out.println("Tom you're Dick Faceeeeee!!!!!");
                }

                else{
                    SearchableMaze searchableMaze = new SearchableMaze(maze);
                    Solution s = searchingAlgorithm.solve(searchableMaze);
                    hashMap.put(maze, tempDirectoryPath + "Solution"+ counter);
                    writeSolutionToFile(s);
                    Solution newS = readSolutionFromFile(hashMap.get(maze));
                    SolutionOutputStream.writeObject(newS);
                    SolutionOutputStream.flush();
                }

            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

        } catch (IOException | InstantiationException | IllegalAccessException e){ e.printStackTrace();}
    }

    private Solution readSolutionFromFile(String file){
        try{
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Solution sol = (Solution)objectIn.readObject();
            objectIn.close();
            return sol;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeSolutionToFile(Solution s){
        try{
            FileOutputStream fileOut = new FileOutputStream(tempDirectoryPath + "Solution" + counter);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(s);
            objectOut.close();
            counter++;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
