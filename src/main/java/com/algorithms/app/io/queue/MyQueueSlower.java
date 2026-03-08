package com.algorithms.app.io.queue;

import java.util.Stack;

public class MyQueueSlower {
    private final Stack<Integer> s1;
    private final Stack<Integer> s2;

    public MyQueueSlower() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    // O(1)
    public void push(int x) {
        s1.push(x);
    }

    // O(n)
    public int pop() {
        if(s1.isEmpty())
            return -1;
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        var pop = s2.pop();
        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return pop;
    }

    // O(n)
    public int peek() {
        if(s1.isEmpty())
            return -1;
        while(!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        var peek = s2.peek();
        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return peek;
    }

    // O(1)
    public boolean empty() {
        return s1.isEmpty();
    }

    static void main() {
        var queue = new MyQueueSlower();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
