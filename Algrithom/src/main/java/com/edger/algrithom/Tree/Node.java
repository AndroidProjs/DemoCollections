package com.edger.algrithom.Tree;

public class Node<T extends Comparable<?>> {
    public Node<T> left, right;
    public T val;

    public Node(T val) {
        this.val = val;
    }
}
