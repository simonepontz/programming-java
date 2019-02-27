package com.theory.tree.structure;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Consumer;

public class BinTree<T> {

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    Node<T> root;

    public void traversalInOrder(Consumer<T> consume) {
        Stack<Node<T>> stack = new Stack<>();

        Node<T> current = this.root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            consume.accept(current.data);
            current = current.right;
        }
    }

    public void traversalPreOrder(Consumer<T> consume) {
        Stack<Node<T>> stack = new Stack<>();

        Node<T> current = this.root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                consume.accept(current.data);
                current = current.left;
            }
            current = stack.pop();
            current = current.right;
        }
    }

    public void traversalLevel(Consumer<T> consume) {
        LinkedList<Node<T>> queue = new LinkedList<>();
        queue.addFirst(this.root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.removeLast();
            consume.accept(current.data);
            if(current.left != null)
                queue.addFirst(current.left);
            if(current.right != null)
                queue.addFirst(current.right);
        }
    }

    public void traversalLevelDeptAware(Consumer<T> consume, Consumer<Integer> deptCounter) {
        LinkedList<Optional<Node<T>>> queue = new LinkedList<>();
        queue.addFirst(Optional.of(this.root));
        queue.addFirst(Optional.empty());

        Integer levelCounter = 0;
        while (!queue.isEmpty()) {
            if (!queue.peekLast().isPresent()) {
                queue.removeLast();
                if(!queue.isEmpty())
                    queue.addFirst(Optional.empty());
                deptCounter.accept(levelCounter++);
            } else {
                Node<T> current = queue.removeLast().get();
                consume.accept(current.data);
                if (current.left != null)
                    queue.addFirst(Optional.of(current.left));
                if (current.right != null)
                    queue.addFirst(Optional.of(current.right));

            }
        }
    }

    public Integer maxWidth() {
        // Realy orrible example for closure creation
        class Counter {
            private Integer counter = 0;
            private Integer maxCounter = 0;
            void inc() {
                counter++;
            }

            void setMax() {
                maxCounter = Math.max(counter, maxCounter);
                counter = 0;
            }

        }

        final Counter c = new Counter();

        this.traversalLevelDeptAware(d -> c.inc(), d -> c.setMax());
        return c.maxCounter;
    }

    public void traversalPostOrder(Consumer<T> consume) {
        Stack<Node<T>> stack = new Stack<>();

        Node<T> current;
        Node<T> prev = null;

        stack.push(this.root);

        while (!stack.isEmpty()) {
            current = stack.peek();

            if(prev == null || prev.left == current || prev.right == current) {
                if(current.left != null) {
                    stack.push(current.left);
                } else if(current.right != null) {
                    stack.push(current.right);
                } else {
                    Node<T> popped = stack.pop();
                    consume.accept(popped.data);
                }
            } else if(current.left == prev) {
                if(current.right != null) {
                    stack.push(current.right);
                } else {
                    Node<T> node = stack.pop();
                    consume.accept(node.data);
                }
            } else if(current.right == prev) {
                Node<T> node = stack.pop();
                consume.accept(node.data);
            }
            prev = current;
        }
    }

    private Integer heightIterative() {
        class Pair {
            private Node<T> node;
            private Integer height;

            public Pair(Node<T> node, Integer height) {
                this.node = node;
                this.height = height;
            }
        }

        Node<T> current = this.root;
        int currentDept = -1;
        int maxDept = -1;

        Stack<Pair> stack = new Stack<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(new Pair(current, currentDept));
                current = current.left;
                currentDept++;
            }

            maxDept = Math.max(currentDept, maxDept);
            Pair frame = stack.pop();
            current = frame.node.right;
            currentDept = frame.height + 1;
        }
        return maxDept;
    }

    public Integer height(Node<T> root) {
        if(root == null) return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public Integer height() {
        return height(this.root);
    }

    public BinTree() {

    }
    public BinTree(Node<T> node) {
        this.root = node;
    }

}
