package com.theory.tree.structure;

public class AVL <T extends Comparable<T>> extends BST<T> {

    /**
     *         r
     *       /  \
     *      y   t4
     *    /  \
     *   t2  t3
     *
     *
     *
     * @param r
     * @return
     */
    protected Node<T> rightRotate(Node<T> r) {
        Node<T> y = r.left;
        Node<T> t3 = y.right;
        y.right = r;
        r.left = t3;
        r.height = Math.max(height(r.left), height(r.right)) +1;
        y.height = Math.max(height(y.left), height(y.right)) +1;
        return y;
    }

    /**
     *              r
     *            /   \
     *           t2   y
     *              /  \
     *             t3  t4
     *
     * @param r
     * @return
     */
    protected Node<T> leftRotate(Node<T> r) {
        Node<T> y = r.right;
        Node<T> t3 = y.left;
        y.left = r;
        r.right = t3;
        r.height = Math.max(height(r.left), height(r.right)) +1;
        y.height = Math.max(height(y.left), height(y.right)) +1;
        return y;
    }

    private int getBalanceFactor(Node<T> root) {
        if(root == null)
            return 0;
        return height(root.left) - height(root.right);
    }

    private Node<T> insert(Node<T> node, T data) {
        if(node == null)
            return new Node<>(data);

        if(data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else if(data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else {
            return node;
        }

        node.height = Math.max(height(node.right), height(node.left)) + 1;

        int balanceFactor = getBalanceFactor(node);

        if(balanceFactor > 1 && data.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }

        if(balanceFactor < -1 && data.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balanceFactor > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balanceFactor < -1 && data.compareTo(node.left.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


    private Node<T> delete(Node<T> node, T data) {
        // no node to delete found
        if(node == null)
            return null;

        if(data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else if(data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else {
            if(node.left == null || node.right == null) {
                Node tmp = null;
                if(node.right != null) {
                    tmp = node.right;
                } else {
                    tmp = node.left;
                }
                if(tmp == null)
                    return null;

                node = tmp;

            } else {
                Node<T> tmp = minValueNode(node.right);
                node.data = tmp.data;
                node.right = delete(node.right, tmp.data);
            }
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balanceFactor = getBalanceFactor(node);

        if(balanceFactor > 1 && data.compareTo(node.left.data) >= 0) {
            return rightRotate(node);
        }

        if(balanceFactor < -1 && data.compareTo(node.right.data) < 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balanceFactor > 1 && data.compareTo(node.left.data) <= 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balanceFactor < -1 && data.compareTo(node.left.data) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public void insert(T data) {
        this.root = insert(this.root, data);
    }

    public void delete(T data) {
        this.root = delete(this.root, data);
    }

    @Override
    public Integer height(Node<T> node) {
        if(node == null)
            return -1;
        return node.height;
    }
}
