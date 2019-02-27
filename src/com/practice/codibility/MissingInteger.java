package com.practice.codibility;

import java.util.HashSet;
import java.util.Set;

public class MissingInteger {

    public static void main(String[] args) {
        //int[] a = {1, 3, 6, 4, 1, 2};
        int[] a = {};
        int solution = new MissingInteger().solution(a);
        System.out.println(solution);
    }

    public int solution(int[] A) {
        Set<Integer> checker = new HashSet<>();
        for (int a : A) {
            checker.add(a);
        }

        int minMissing = Integer.MAX_VALUE;
        if(!checker.contains(1)) {
            return 1;
        } else {
            for(int i = 0; i < A.length; i++) {
                int exist = A[i];
                if(!checker.contains(exist + 1) && (exist + 1) > 0){
                    minMissing = Integer.min(minMissing, exist + 1);
                }
            }
        }
        return minMissing;
    }
}
