package com.practice.codibility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Ladder {

    public int solution(int[] A) {
        // write your code in Java SE 8
        HashMap<Integer, Integer> cache = new HashMap<>();
        Arrays.stream(A).forEach(e -> cache.compute(e, (k, v)-> Optional.ofNullable(v).map(Math::incrementExact).orElse(1)));
        for (Map.Entry<Integer, Integer> integerIntegerEntry : cache.entrySet()) {
            if(integerIntegerEntry.getValue() % 2 == 1)
                return integerIntegerEntry.getKey();

        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = {4, 4, 5, 1, 1};

        int solution = new Ladder().solution(a);
        System.out.println(solution);
    }
}
