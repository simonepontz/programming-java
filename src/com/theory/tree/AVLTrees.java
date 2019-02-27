package com.theory.tree;

import com.theory.tree.structure.AVL;
import com.theory.tree.structure.BinTreePrinter;

import java.util.stream.IntStream;

public class AVLTrees {
    public static void main(String[] args) {
        AVL<Integer> avl = new AVL<>();
        IntStream.range(0,9).forEach(avl::insert);
        boolean balanced = avl.isBalanced();
        System.out.println(balanced);


        avl.traversalLevelDeptAware(d -> System.out.print(String.format("%s \t", d)), x -> System.out.println());

        BinTreePrinter.printNode(avl.getRoot());

        System.out.println("Delete 5");
        avl.delete(5);
        avl.traversalLevelDeptAware(d -> System.out.print(String.format("%s \t", d)), x -> System.out.println());
        BinTreePrinter.printNode(avl.getRoot());
    }
}
