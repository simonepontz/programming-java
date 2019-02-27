package com.practice.geek4geek;


import java.util.*;

public class RottenOranges {

    static final int ROTTEN = 1;
    static final int FRESH = 2;
    static final int EMPTY = 0;

    //static int[][] m = {
    //        {1,2,1,1,2,1,2,1,1,1,0,0,2,1,0,0,1,0,1,0,2,0,2,0,0,0,1,2,0,0,1,1,2,0,1,1,2,1,0},
    //        {1,2,1,2,1,0,0,2,1,0,0,1,0,0,2,2,1,2,1,1,0,2,1,0,2,1,1,1,1,0,0,0,2,1,0,1,2,0,1},
    //        {2,2,2,1,0,0,0,2,2,0,2,1,1,1,2,1,1,2,0,1,0,0,1,1,0,0,2,2,2,2,0,1,2,0,0,0,1,1,0},
    //        {1,0,2,2,1,2,0,0,0,2,1,2,0,2,0,2,2,1,1,1,1,1,2,0,1,0,1,1,2,0,1,0,1,2,0,0,2,1,0},
    //        {0,0,1,0,1,0,0,0,0,2,2,2,1,1,1,2,2,2,1,0,1,2,2,1,0,2,0,0,1,1,1,2,0,0,2,2,1,0,1}
    //};

    static int[][] m = {
            {1,2,2,1}
    };

    static final class Position {
        int x, y = 0;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Optional<Position> rotOrange(final int[][] oranges, int x, int y) {
        if(x < 0 || y < 0 || x >= oranges.length || y >= oranges[x].length)
            return Optional.empty();

        if(oranges[x][y] == FRESH) {
            oranges[x][y] = ROTTEN;
            return Optional.of(new Position(x, y));
        } else {
            return Optional.empty();
        }
    }

    private static class CounterWrapper {
        int x = 0;
        public void dec() {
            x--;
        }

        public void inc() {
            x++;
        }
    }

    public int rottenOrange(final int[][] m) {

        CounterWrapper freshCounter = new CounterWrapper();
        int time = -1;

        final int[][] clonedM = m.clone();
        Queue<Position> rottenOrangeToProcess = new LinkedList<>();

        for(int r = 0; r < m.length; r++) {
            for(int c = 0; c < m[r].length; c++) {
                if(m[r][c] == ROTTEN)
                    rottenOrangeToProcess.add(new Position(r, c));

                if(m[r][c] == FRESH)
                    freshCounter.inc();

            }
        }

        while (!rottenOrangeToProcess.isEmpty()) {
            time ++;
            List<Position> currentEpochOrange = new ArrayList<>();
            while (!rottenOrangeToProcess.isEmpty()) {
                currentEpochOrange.add(rottenOrangeToProcess.poll());
            }
            currentEpochOrange.forEach(currentRottenOrange -> {
                rotOrange(clonedM, currentRottenOrange.x - 1, currentRottenOrange.y).ifPresent(orange -> {
                    freshCounter.dec();
                    rottenOrangeToProcess.add(new Position(orange.x, orange.y));
                });
                rotOrange(clonedM, currentRottenOrange.x, currentRottenOrange.y - 1).ifPresent(orange -> {
                    freshCounter.dec();
                    rottenOrangeToProcess.add(new Position(orange.x, orange.y));
                });
                rotOrange(clonedM, currentRottenOrange.x + 1, currentRottenOrange.y).ifPresent(orange -> {
                    freshCounter.dec();
                    rottenOrangeToProcess.add(new Position(orange.x, orange.y));
                });
                rotOrange(clonedM, currentRottenOrange.x, currentRottenOrange.y + 1).ifPresent(orange -> {
                    freshCounter.dec();
                    rottenOrangeToProcess.add(new Position(orange.x, orange.y));
                });
            });
        }
        return freshCounter.x > 0 ? -1 : time;
    }

    public static void main(String[] args) {
        int freshRemaining = new RottenOranges().rottenOrange(m);
        System.out.println(freshRemaining);
    }
}
