package com.theory.list;

import java.util.EmptyStackException;

public class Stack<T> {
    private class StackNode<Z> {
        Z data;
        StackNode<Z> next;

        public StackNode(Z data, StackNode<Z> next){
            this.data = data;
            this.next = next;
        }
    }

    private transient StackNode<T> top;

    public synchronized void push(T item) {
        top = new StackNode<>(item, top);
    }

    public synchronized T pop() {
        if(isEmpty()) throw new EmptyStackException();
        final T item = top.data;
        top = top.next;
        return item;
    }

    public synchronized T peek() {
        if(isEmpty()) throw new EmptyStackException();
        return top.data;
    }

    public Boolean isEmpty() {
        return top == null;
    }
}
