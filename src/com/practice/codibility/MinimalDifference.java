package com.practice.codibility;

public class MinimalDifference {
    public int solution(int[] A) {
        int minDifference = Integer.MAX_VALUE;

        int sumL = 0;
        for(int i = 0; i < A.length; i++){
            sumL += A[i];
        }
        int sumR = 0;



        for(int p = 0;  p < A.length - 1; p ++) {
            sumL -= A[p];
            sumR += A[p];
            minDifference = Integer.min(minDifference, Math.abs(sumR - sumL));
        }
        return minDifference;
    }

    public static void main(String[] args) {
        int[] A = {3, 1, 4};
        int solution = new MinimalDifference().solution(A);
        System.out.println(solution);
    }
}
