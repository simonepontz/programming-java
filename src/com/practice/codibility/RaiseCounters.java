package com.practice.codibility;

import java.util.Arrays;

public class RaiseCounters {
    public static void main(String[] args) {
        int x = 5;
        int[] operations = {3, 4, 4, 6, 1, 4, 4};
        int[] counters = new RaiseCounters().solution(x, operations);
        System.out.println(Arrays.toString(counters));
    }

    private int incX(int X, int[] counters, int max) {
        if(counters[X] < max) {
            counters[X] = max;
        }
        counters[X] += 1;
        return counters[X];
    }

    private int[] incRow(int[] counters, int max) {
        for(int i = 0; i < counters.length; i ++) {
            if(counters[i] < max) {
                counters[i] = max;
            }
        }
        return counters;
    }


    public int[] solution(int N, int[] A) {
        int[] counters = new int[N];
        int maxReached = Integer.MIN_VALUE;
        int maxToReach = Integer.MIN_VALUE;
        for (int operation : A) {
            if(operation == N +1){
                maxReached = maxToReach;
                System.out.print("InR ");
            } else {
                System.out.print("InS ");
                maxToReach = Integer.max(maxToReach, incX(operation - 1 , counters, maxReached));
            }
            System.out.println(Arrays.toString(counters));
        }
        return incRow(counters, maxReached);
    }
}
