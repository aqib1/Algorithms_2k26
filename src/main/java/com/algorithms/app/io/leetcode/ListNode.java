package com.algorithms.app.io.leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val, ListNode node) {
        this.val = val;
        this.next = node;
    }

    ListNode(int val) {
        this(val, null);
    }

    @Override
    public String toString() {
        var crr = this;
        var b = new StringBuilder();
        while(crr != null) {
            b.append(crr.val).append(" ,");
            crr = crr.next;
        }
        return b.toString();
    }
}
