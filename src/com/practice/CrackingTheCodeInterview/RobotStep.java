package com.practice.CrackingTheCodeInterview;

import java.util.ArrayList;
import java.util.List;

public class RobotStep {
    private class Point {
        int x, y = 0;
        public Point(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }

    public List<Point> path(boolean[][] maze) {
        boolean[][] visited = new boolean[maze.length][maze[maze.length -1].length];
        List<Point> path = new ArrayList <>();
        if(path(maze, 0,0, path, visited)) {
            return path;
        }
        return null;
    }

    private boolean path(boolean[][] maze, int x, int y, List<Point> path, boolean[][] visited) {
        boolean isEnd = (x == maze.length -1) && (y == maze[x].length -1);

        if(x < maze.length && y < maze[x].length && visited[x][y]) {
            System.out.println(String.format("Point(%s, %s) has failed", x, y));
        }
        if(x < maze.length && y < maze[x].length && maze[x][y] && !visited[x][y]) {
            if(isEnd || path(maze, x +1, y, path, visited) || path(maze, x, y + 1, path, visited)) {
                path.add(new Point(x, y));
                return true;
            }

        }
        if(x < maze.length && y < maze[x].length ) {
            visited[x][y] = true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean[][] maze = {
                {true, true, false},
                {true, true, true},
                {true, false, true}
        };
        List<Point> path = new RobotStep().path(maze);
        if(path == null)
            System.out.println("no path");
        else
            for(Point p : path) {
                System.out.println(String.format("Point(%s, %s)", p.x, p.y));
            }
    }
}
