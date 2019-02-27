package com.practice.personal;

import java.util.*;

public class TopK {

    public static List<Integer> topK(int[] array, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.naturalOrder());
        for(int e : array) {
            if(queue.size() < k) {
                queue.add(e);
            } else if(queue.peek() < e) {
                queue.remove();
                queue.add(e);
            }
        }
        List<Integer> list = new ArrayList<>(k);
        list.addAll(queue);
        return list;
    }

    public static void main(String[] args) {
        int[] array = { 0, 0,4,3,7,4,8,9,4,2};
        topK(array, 5).forEach(e -> System.out.print(String.format("%s ",e)));
    }
}
