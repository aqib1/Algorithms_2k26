package com.algorithms.app.io.ripple;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        int value = -1;
        if(map.containsKey(key)) {
            var node = map.get(key);
            value = node.value;
            removeNode(node);
            addNode(node);
        }
        return value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            removeNode(node);
            node.value = value;
            addNode(node);
        } else {
            if (map.size() == capacity) {
                map.remove(head.next.key);
                removeNode(head.next);
            }
            var node = new Node(key, value);
            this.map.put(key, node);
            addNode(node);
        }
    }

    private void removeNode(Node node) {
        var prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
    }

    private void addNode(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
    }

    static class Node {
        int value;
        int key;
        Node next;
        Node prev;

        public Node() {

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
