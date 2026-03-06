package com.algorithms.app.io.stack;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    // 2, 3, 4
    // 3, 4, 2
    // 4, 2, 3
    private final Queue<Integer> fifo;

    // Space O(n)
    public MyStack() {
        this.fifo = new LinkedList<>();
    }

    // Time O(n)
    public void push(int x) {
        fifo.add(x);

        for(int i = 0; i < fifo.size() - 1; i++) {
            fifo.add(fifo.remove());
        }
    }

    // Time O(1)
    public int pop() {
        if(fifo.isEmpty())
            return -1;
        return fifo.poll();
    }

    // Time O(1)
    public int top() {
        if(fifo.isEmpty())
            return -1;
        return fifo.peek();
    }

    // Time O(1)
    public boolean empty() {
        return fifo.isEmpty();
    }
}
