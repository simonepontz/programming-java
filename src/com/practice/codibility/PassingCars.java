package com.practice.codibility;

public class PassingCars {
    public static void main(String[] args) {
        int[] a = {0,1,0,1,1};
        int solution = new PassingCars().solution(a);
        System.out.println(solution);
    }

    /**
     * 0  -  0  -  -
     * 1, 1, 2, 2, 2
     * 0, 1, 1, 2, 3
      * @param A
     * @return
     */

    public int solution(int[] A) {
        // write your code in Java SE 8
        int[] prefixSum = new int[A.length];
        prefixSum[0] = A[0];
        for(int i = 1; i < A.length; i++) {
            prefixSum[i] = prefixSum[i -1] + A[i];
        }

        long passingCarSum = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == 0) {
                passingCarSum += prefixSum[prefixSum.length -1] - prefixSum[i];
            }
            if(passingCarSum > 1000000000)
                return -1;
        }

        return (int)(long)passingCarSum;
    }
}
