package com.practice.codibility;

import com.theory.heap.structure.Heap;

import java.util.Arrays;

public class MergeKArray {
    private static Integer[][] arrays = {
            {1,2,4,6},
            {0,3,5,7},
            {8,9,10,11},
            {0,3,5,7}
    };

    public static void main(String[] args) {
        Integer[] finalArray = new Integer[arrays.length * arrays[0].length];
        int finalIdx = 0;
        for(int i = 0; i < arrays[0].length*arrays.length; i++) {
            Heap<Integer> kHeap = new Heap<>(arrays.length);
            for(int j = 0; j < arrays.length; j++) {
                kHeap.insert(arrays[j][i]);
            }
            for(int j = 0; j < arrays.length; j++) {
                Integer integer = kHeap.removeMin();
                finalArray[finalIdx++] = integer;
            }
        }
        System.out.println(Arrays.toString(finalArray));
    }
}
