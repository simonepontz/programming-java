package com.practice.geek4geek;

import java.util.Stack;

public class LinkedListSum {
    private static class Node {
        Integer value;
        Node next = null;
    }

    static Node list1 ;
    static Node list2;

    public static Node sumList(Node l1, Node l2) {
        Stack<Integer> reverPile1 = new Stack<>();
        Stack<Integer> revertPile2 = new Stack<>();

        fillPile(reverPile1, l1);
        fillPile(revertPile2, l2);

        Stack<Integer> lowPile = reverPile1.size() >= revertPile2.size() ? revertPile2: reverPile1;
        Stack<Integer> highPile = reverPile1.size() <= revertPile2.size()  ? reverPile1: revertPile2;

        int curry = 0;
        Node resultList = null;
        while (!lowPile.isEmpty()) {
            Integer l = lowPile.pop();
            Integer h = highPile.pop();
            Integer sum = l + h + curry;
            curry = sum / 10;
            resultList = addNode(resultList, sum);
        }

        while (! highPile.isEmpty()) {
            Integer v = highPile.pop();
            Integer sum = v + curry;
            curry = sum / 10;
            resultList = addNode(resultList, sum);
        }
        return resultList;
    }

    private static Node addNode(Node resultList, Integer sum) {
        Node newNode = new Node();
        newNode.value = sum % 10;
        newNode.next = resultList;
        resultList = newNode;
        return resultList;
    }

    private static void fillPile(Stack<Integer> reverPile1, Node current) {
        while(current != null) {
            reverPile1.push(current.value);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        list1 = addNode(list1, 3);
        list1 = addNode(list1, 1);

        list2 = addNode(list2, 7);
        list2 = addNode(list2, 1);

        Node current = sumList(list1, list2);
        while (current!=null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
