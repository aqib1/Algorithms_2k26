package com.algorithms.app.io.leetcode;

public class LinkedListCycle {
    static void main() {
        var node = new ListNode(1);
        node.next = new ListNode(2);

        System.out.println(hasCycle(node));
    }
    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;

        var slow = head; var fast = head.next;
        while(fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }
}
