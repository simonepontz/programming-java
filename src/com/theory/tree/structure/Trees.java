package com.theory.tree.structure;


public class Trees {

    private static <T extends Comparable<T>> boolean isBst(Node<T> node, T min, T max) {
        if(node == null) return true;

        if(min != null && node.data.compareTo(min) < 0) {
            return false;
        }

        if(max != null && node.data.compareTo(max) > 0) {
            return false;
        }

        return isBst(node.right, node.data, max) && isBst(node.left, min, node.data);
    }

    public static <T extends Comparable<T>> boolean isBst(BinTree<T> tree) {
        return isBst(tree.root, null, null);
    }
}
