package com.algorithms.app.io.catoNetworks;

import java.util.EmptyStackException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class Node {
    int val;
    Node pre;
    Node(int val, Node pre) {
        this.val = val;
        this.pre = pre;
    }
}
public class MinStack {
    private Node current;
    private Stack<Integer> minStack;

    public MinStack() {
        this.minStack = new Stack<>();
    }

    public void push(int value) {
        this.current = new Node(value, current);
        if(minStack.isEmpty() || value < minStack.peek()) {
            minStack.push(value);
        }
    }

    public int pop() {
        if(current == null)
            throw new EmptyStackException();
        int val = current.val;
        current = current.pre;
        if(!minStack.isEmpty() && val == minStack.peek())
            minStack.pop();

        return val;
    }

    public int getMin() {
        if(minStack.isEmpty())
            throw new EmptyStackException();
        return minStack.peek();
    }

    public int top() {
        if(current == null)
            throw new EmptyStackException();
        return current.val;
    }
}
