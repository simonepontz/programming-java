package com.theory.sorting;

import java.util.ArrayList;

public class CountSort {
    public static void main(String[] args) {
        int[] a = {5,4,2,3,1};
        int[] solution = countSort(a);
        System.out.println(solution);
    }

    public static int[] countSort(int[] a) {
        int max = Integer.MIN_VALUE;
        ArrayList<Integer> counters = new ArrayList<>();
        for (int element : a) {
            if(counters.size() -1 < element) {
                counters.set(element, 0);
            } else {
                Integer value = counters.get(element);
                counters.set(element, value + 1);
            }
        }

        int d = 0;
        for(int i = 0; i < counters.size(); i++){
            Integer value = counters.get(i);
            for(int j = 0; j < counters.size(); j++){
                a[d] = i;
                d ++;
            }
        }
        return a;
    }
}
