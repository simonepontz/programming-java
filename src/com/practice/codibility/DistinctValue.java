package com.practice.codibility;

import java.util.Arrays;

public class DistinctValue {
    public static void main(String[] args) {
        int[] a = {2,1,1,2,3,1};
        int solution = new DistinctValue().solution(a);
        System.out.println(solution);
    }

    /**
     * The solution will take advantages from sorting the array and counting the distinct values.
     * Another approach could be to count the element in the array and filter for the one that have count = 1.
     * One is n*log(n) and the other is simply n.
     * @param A
     * @return
     */
    public int solution(int[] A) {
        Arrays.sort(A);
        int distinct = 1;
        for(int i = 0; i < A.length - 1; i++) {
            if(A[i] != A[i+1]) distinct += 1;
        }
        return distinct;
    }

}
