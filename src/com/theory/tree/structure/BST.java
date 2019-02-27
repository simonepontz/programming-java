package com.theory.tree.structure;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public class BST<T extends Comparable<T>> extends BinTree<T> {

    private Node<T> findInsertPosition(Node<T> root, T data) {
        if(root.data.compareTo(data) < 0){
            if(root.right == null) {
                root.right = new Node<>(data);
                return root.right;
            }
            return findInsertPosition(root.right, data);
        } else if(root.data.compareTo(data) > 0){
            if(root.left == null) {
                root.left = new Node<>(data);
                return root.left;
            }
            return findInsertPosition(root.left, data);
        } else {
            return root;
        }
    }

    @SuppressWarnings("unchecked")
    private Node<T> insertSorted(final Object[] array, final int from, final int to) {
        if(from > to) return null;

        int middle = (to - from) / 2 + from;

        Node<T> root = new Node<>((T) array[middle]);

        if(from == to) return root;

        root.right = insertSorted(array, middle + 1, to);
        root.left = insertSorted(array, from, middle - 1);

        return root;
    }

    public Optional<T> traversalBinary(Function<T, Integer> router) {
        Node<T> current = this.root;
        while (current != null) {
            Integer direction = router.apply(current.data);
            if(direction > 0){
                current = current.right;
            } else if(direction < 0) {
                current = current.left;
            } else {
                return Optional.of(current.data);
            }
        }
        return Optional.empty();
    }

    public Optional<T> search(final T value) {
        return this.traversalBinary(value::compareTo);
    }

    public BST() {
        super();
    }

    @SuppressWarnings("unchecked")
    public BST(Collection<? extends T> items){
        this();
        Object[] itemArray = items.toArray();
        Arrays.sort(itemArray);
        this.root = insertSorted(itemArray, 0, itemArray.length - 1);
    }

    private Integer modifiedHeight(Node<T> root) {
        if(root == null) return 0;
        Integer hL = modifiedHeight(root.left);
        Integer hR = modifiedHeight(root.right);
        if(hL == null || hR == null)
            return null;
        if(Math.abs(hL - hR) > 1)
            return null;
        return Math.max(hL, hR) + 1;
    }

    Node<T> minValueNode(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public boolean isBalanced() {
        Integer height = modifiedHeight(root);
        return height != null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(T data) {
        if(isEmpty())
            root = new Node<>(data);

        findInsertPosition(this.root, data);
    }
}
