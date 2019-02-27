package com.practice.codibility;

import java.util.Arrays;

public class EarlyJump {
    public static void main(String[] args) {
        int[] a = {1, 3, 1, 4, 2, 3, 5, 4};
        int x = 5;
        int solution = new EarlyJump().solution(x, a);
        System.out.println(solution);
    }

    public int solution(int X, int[] A) {
        int earliest = 0;
        int[] checker = new int[X];
        Arrays.fill(checker, -1);

        for(int time = 0; time < A.length; time++) {
            int position = A[time] - 1;
            if (checker[position] == -1) {
                checker[position] = time;
            } else {
                checker[position] = Integer.min(checker[position], time);
            }
        }
        for (int time : checker) {
            if(time == -1)
                return -1;
            earliest = Integer.max(earliest, time);
        }
        return earliest;
        // write your code in Java SE 8
    }
}
