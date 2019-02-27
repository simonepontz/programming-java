package com.theory.tree;

import com.theory.tree.structure.*;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreeBase {

    private static Consumer<Integer> printValues(String tag) {
      return data -> System.out.print(String.format("(%s) Value: %s \t", tag,data));
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        IntStream.range(0, 9).forEach(tree::insert);

        tree.isBalanced();
        System.out.println();
        tree.traversalInOrder(printValues("in"));
        System.out.println();
        tree.traversalPreOrder(printValues("pre"));

        Collection<Integer> values = IntStream.range(0, 9).boxed().collect(Collectors.toList());
        System.out.println();
        System.out.println(String.format("height of the tree %s", tree.height()));

        tree = new BST<>(values);

        AVL<Integer> avl = new AVL<>();
        IntStream.range(0, 9).forEach(avl::insert);
        System.out.println();
        System.out.println(String.format("height of the tree %s", avl.height()));

        System.out.println();
        tree.traversalInOrder(printValues("in"));
        System.out.println();
        tree.traversalPreOrder(printValues("pre"));
        System.out.println();
        tree.traversalPostOrder(printValues("post"));
        System.out.println();
        tree.traversalLevel(printValues("level"));
        System.out.println();
        tree.traversalLevelDeptAware(printValues("level"), l -> System.out.println(String.format("Level %s", l)));

        System.out.println();
        System.out.println(String.format("height of the tree %s", tree.height()));
        System.out.println();
        System.out.println(String.format("Max Width of tree %s", tree.maxWidth()));

        Optional<Integer> search = tree.search(7);
        search.ifPresent(d -> System.out.println(String.format("Found %s",d)));

        boolean bst = Trees.isBst(tree);

        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node7 = new Node<>(7);

        node5.setLeft(node3);
        node3.setRight(node7);

        BinTree<Integer> notBst = new BinTree<>(node5);

        boolean bst1 = Trees.isBst(notBst);
    }
}
