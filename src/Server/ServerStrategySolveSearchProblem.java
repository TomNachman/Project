package Server;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    // hashmap - holds the maze as key and the solution path in tmpdir as value
    private static ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
    private String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    private int counter;

    @Override
    //synchronized because the counter variable
    public synchronized void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream MazeInputStream = new ObjectInputStream(inFromClient);
            ObjectOutputStream SolutionOutputStream = new ObjectOutputStream(outToClient);
            SolutionOutputStream.flush();
            try {
                Class<?> SA = Class.forName("algorithms.search." + Server.Configurations.getSolutionMethod());
                ISearchingAlgorithm searchingAlgorithm = (ISearchingAlgorithm) SA.newInstance();
                Maze maze = (Maze)MazeInputStream.readObject();
                if(hashMap.containsKey(Arrays.toString(maze.toByteArray()))){
                    Solution s = readSolutionFromFile(hashMap.get(Arrays.toString(maze.toByteArray())));
                    SolutionOutputStream.writeObject(s);
                }

                else{
                    SearchableMaze searchableMaze = new SearchableMaze(maze);
                    Solution s = searchingAlgorithm.solve(searchableMaze);
                    hashMap.put(Arrays.toString(maze.toByteArray()), tempDirectoryPath + "/Solution"+ counter);
                    writeSolutionToFile(s);
                    Solution newS = readSolutionFromFile(hashMap.get(Arrays.toString(maze.toByteArray())));
                    SolutionOutputStream.writeObject(newS);
                }
                SolutionOutputStream.flush();

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
            FileOutputStream fileOut = new FileOutputStream(tempDirectoryPath + "/Solution" + counter);
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
