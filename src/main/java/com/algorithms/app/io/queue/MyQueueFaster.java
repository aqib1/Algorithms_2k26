package com.algorithms.app.io.queue;

import java.util.Stack;

public class MyQueueFaster {
    private final Stack<Integer> s1;
    private final Stack<Integer> s2;
    private int top = -1;


    public MyQueueFaster() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public void push(int x) {
        if(s1.isEmpty())
            top = x;
        s1.push(x);
    }

    public int pop() {
        if(s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }

        return s2.isEmpty() ? -1 : s2.pop();
    }

    public int peek() {
        if(!s2.isEmpty())
            return s2.peek();

        return top;
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    static void main() {
        var queue = new MyQueueFaster();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.peek());
        System.out.println(queue.pop());

        System.out.println(queue.peek());
        System.out.println(queue.pop());

        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
