package com.theory.tree.structure;

public class Node<Z> {
    Integer height;
    Z data;
    Node<Z> left;
    Node<Z> right;

    public void setLeft(Node<Z> left) {
        this.left = left;
    }

    public Node<Z> getLeft() {
        return this.left;
    }

    public void setRight(Node<Z> right) {
        this.right = right;
    }

    public Node<Z> getRight() {
        return this.right;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return this.height;
    }

    public Node(Z data) {
        this.data = data;
        left = right = null;
        height = 0;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
