package com.theory.leaderFiltering;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeaderLinear {

    public static int findLeaderRecursive(int[] array) {
        //HashMap<Integer, Integer> counter = new HashMap<>();
        //for(int e : array)
        //    counter.put(e, counter.getOrDefault(e, 0) + 1);

        Map<Integer, Long> counter = Arrays.stream(array).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counter.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .filter(e -> e.getValue() > array.length /2).map(Map.Entry::getKey).orElse(-1);
    }

    public static int findLeader(int[] array) {
        int topOfStack = -1;
        int stackSize = 0;
        for(int i = 0; i < array.length; i++) {
            if(stackSize != 0 && topOfStack != array[i]) {
                stackSize--;
            }
            if(stackSize == 0) {
                stackSize++;
                topOfStack = array[i];
            }
        }
        if(stackSize == 0)
            return -1;
        int leaderCount = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == topOfStack)
                leaderCount++;
        }
        return (leaderCount > (array.length / 2))
                ? topOfStack
                : -1;
    }

    public static void main(String[] args) {
        int[] arrays = {1,5,4,3,5,6,5,5,5,8,9,5,5};
        System.out.println(findLeaderRecursive(arrays));

    }
}
