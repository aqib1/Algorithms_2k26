package com.algorithms.app.io.revision;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Space O(n)
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

    // O(1)
    public int get(int key) {
        var value = -1;

        if (map.containsKey(key)) {
            var node = map.get(key);
            value = node.value;
            removeNode(node);
            addNode(node);
        }

        return value;
    }

    // O(1)
    private void addNode(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    // O(1)
    private void removeNode(Node node) {
        var pre = node.prev;
        pre.next = node.next;
        node.next.prev = pre;
    }

    // O(1)
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            removeNode(node);
            node.value = value;
            addNode(node);
        } else {
            if (map.size() == capacity) {
                var next = head.next;
                removeNode(next);
                map.remove(next.key);
            }
            var node = new Node(key, value);
            addNode(node);
            map.put(key, node);
        }
    }

    static class Node {
        int value;
        int key;
        Node next;
        Node prev;

        Node() {

        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
