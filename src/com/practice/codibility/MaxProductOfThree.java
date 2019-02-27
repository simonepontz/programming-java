package com.practice.codibility;

import java.util.Arrays;

public class MaxProductOfThree {

    /**
     * The function wil return the maximum sum of a triplet.
     * @param A is the array that contains the triplet
     * @return return the maximum sum.
     */
    public int solution(int[] A) {
        Arrays.sort(A);
        return A[A.length - 1] * A[A.length - 2] * A[A.length - 3];
    }

    public static void main(String[] args) {
        int[] array = {-3, 1, 2, -2, 5, 6};
        System.out.println(new MaxProductOfThree().solution(array));
    }
}
