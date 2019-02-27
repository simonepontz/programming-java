package com.theory.slicing;

import java.util.Arrays;

public class PrefixSlicing {
    final static int[] array = {5, -7, 3, 5, -2, 4, -1};

    public static int maxSliceQuadratic(int[] array) {
        int[] prefixSum = new int[array.length + 1];
        int sum = 0;
        prefixSum[0] = sum;
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
            prefixSum[i + 1] = sum;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            for(int j = 1; j < array.length +1; j++) {
                int sliceSum = prefixSum[j] - prefixSum[i];
                max = Math.max(sliceSum, max);
            }
        }
        return max;
    }

    public static int maxSliceOptima(int[] array) {
        int maxSum = 0;
        int currentSum = 0;
        for(int i = 0; i < array.length; i++) {
            currentSum += array[i];
            if(currentSum < 0)
                currentSum = 0;
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSliceQuadratic(array));
        System.out.println(maxSliceOptima(array));
    }
}
