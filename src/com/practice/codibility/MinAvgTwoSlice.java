package com.practice.codibility;

import java.util.Arrays;

public class MinAvgTwoSlice {
    public static void main(String[] args) {
        int[] a = {-1, 1};
        int solution = new MinAvgTwoSlice().solution(a);
        System.out.println(solution);
    }

    private int[] buildPrefix(int[] A) {
        int[] prefix = new int[A.length +1];
        for(int i = 0; i < A.length; i++) {
            prefix[i + 1] = prefix[i] + A[i];
        }
        System.out.println(Arrays.toString(prefix));
        return prefix;
    }
    class Position {
        private Integer position;
        private Double average;

        Position(Integer position, Double average) {
            this.position = position;
            this.average = average;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Double getAverage() {
            return average;
        }

        public void setAverage(Double average) {
            this.average = average;
        }
    }

    private Position swapMin(Position current, Position next) {
        if(current.average > next.average) {
            return next;
        }
        if(current.average == next.average) {
            if(current.position > next.position) {
                return next;
            }
        }
        return current;
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int[] prefix = buildPrefix(A);
        Position min = new Position(Integer.MAX_VALUE, Double.MAX_VALUE);

        for(int i = 0; i < A.length; i++) {
            for(int j = i; j < A.length; j ++) {
                double avg = (prefix[j+1] - (double)prefix[i]) / (j - i);
                min = swapMin(min, new Position(i, avg));
            }
        }
        return min.position;
    }

}
