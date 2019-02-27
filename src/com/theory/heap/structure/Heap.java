package com.theory.heap.structure;

import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
    private Object[] nodes;
    private int size = 0;

    public Heap(int capacity) {
        this.nodes = new Object[capacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    @SuppressWarnings("unchecked")
    public T getMin() {
        if(isEmpty())
            throw new NoSuchElementException();
        return (T)nodes[0];
    }

    private int getParentIdx(int i) {
        return (i-1) / 2;
    }

    private int getLeftIdx(int i) {
        return i*2 +1;
    }

    private int getRightIdx(int i) {
        return i*2 +2;
    }

    private void swap(int idx1, int idx2) {
        Object tmp = this.nodes[idx1];
        this.nodes[idx1] = this.nodes[idx2];
        this.nodes[idx2] = tmp;
    }

    public void insert(T data) {
        this.nodes[size++] = data;
        heapyfyUp(data, size - 1);
    }

    @SuppressWarnings("unchecked")
    public T removeMin() {
        if(isEmpty())
            throw new NoSuchElementException();
        Object tmp = this.nodes[0];
        if(size == 1) {
            size --;
            return (T)tmp;
        }

        this.nodes[0] = this.nodes[--size];
        heapyfyDown(0);
        return (T)tmp;
    }

    public void decreaseKey(int idx, T newValue) {
        this.nodes[idx] = newValue;
        while(idx > 0 && ((T)this.nodes[idx]).compareTo((T)this.nodes[getParentIdx(idx)]) < 0) {
            swap(idx, getParentIdx(idx));
            idx = getParentIdx(idx);
        }
    }

    private void heapyfyDown(int currentIdx) {
        int smallest = currentIdx;
        int lIdx = getLeftIdx(currentIdx);
        int rIdx = getRightIdx(currentIdx);

        if(lIdx < size && ((T)this.nodes[lIdx]).compareTo((T)this.nodes[smallest]) < 0) {
            smallest = lIdx;
        }
        if(rIdx < size && ((T)this.nodes[rIdx]).compareTo((T)this.nodes[smallest]) < 0) {
            smallest = rIdx;
        }
        if(smallest != currentIdx) {
            swap(smallest, currentIdx);
            heapyfyDown(smallest);
        }
    }

    @SuppressWarnings("unchecked")
    private void heapyfyUp(T data, int idx) {
        int currentIdx = idx;
        while(currentIdx > 0 && data.compareTo((T)this.nodes[getParentIdx(currentIdx)]) < 0) {
            swap(currentIdx, getParentIdx(currentIdx));
            currentIdx = getParentIdx(currentIdx);
        }
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Object o : this.nodes) {
            builder.append(o.toString() + " ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(5);
        heap.insert("Z");
        heap.insert("D");
        heap.insert("C");
        heap.insert("B");
        heap.insert("A");
        System.out.println(heap.toString());

        for(int i = 0; i < 5; i++) {
            System.out.println(heap.removeMin());
        }
    }
}
