package com.mex.mazeescape.algorithm;

import com.mex.mazeescape.model.MazeIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DfsBacktrackingMazeEscapeAlgorithm {

    /* A utility function to check if x, y is valid
        index for N*N maze */
    boolean isSafe(String[][] maze, int x, int y, int width, int height, List<MazeIndex> solution) {
        // if (x, y outside maze) return false
        return (x >= 0 && x < width && y >= 0 && y < height && maze[x][y] == " " && !solution.contains(new MazeIndex(x, y)));
    }

    public List<MazeIndex>  solveMaze(String[][] maze, int x, int y) {
        List<MazeIndex> solution = new ArrayList<>();
        int sol[][] = new int[maze.length][maze[0].length];

        if (solveMazeUtil(maze, x, y, solution) == false) {
            System.out.print("Solution doesn't exist");
            return Collections.emptyList();
        }
        return solution;
    }

    /* A recursive utility function to solve Maze
       problem */
    boolean solveMazeUtil(String[][] maze, int x, int y, List<MazeIndex> solution) {
        // if (x, y is goal) return true
        if (x == maze.length - 1 && maze[x][y].equals(" ")) {
            solution.add(new MazeIndex(x,y));
            return true;
        }
        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y, maze.length, maze[0].length, solution) == true) {
            // mark x, y as part of solution path
            solution.add(new MazeIndex(x,y));
            if (solveMazeUtil(maze, x, y + 1, solution)) {
                return true;
            }
            if (solveMazeUtil(maze, x + 1, y, solution)) {
                return true;
            }
            if (solveMazeUtil(maze, x, y - 1, solution)) {
                return true;
            }
            if (solveMazeUtil(maze, x - 1, y, solution)) {
                return true;
            }

            /* If none of the above movements works then
               BACKTRACK: unmark x, y as part of solution
               path */
            solution.remove(solution.size() - 1);
            return false;
        }
        return false;
    }
}
