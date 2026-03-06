package com.algorithms.app.io.stack;

class Node {
    int value;
    Node prev;

    Node(int value, Node prev) {
        this.value = value;
        this.prev = prev;
    }
}
public class StackImpl {
    private Node current;

    public StackImpl() {
    }

    public void push(int x) {
        current = new Node(x, current);
    }

    public int pop() {
        if(current == null)
            return -1;
        var value = current.value;
        current = current.prev;
        return value;
    }

    public int top() {
        if(current == null)
            return -1;
        return current.value;
    }

    public boolean empty() {
        return current == null;
    }

    static void main() {
        var stack = new StackImpl();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.empty());
    }
}
