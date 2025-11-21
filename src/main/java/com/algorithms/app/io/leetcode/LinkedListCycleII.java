package com.algorithms.app.io.leetcode;

public class LinkedListCycleII {
    static void main() {
        var node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);
        node.next.next.next = new ListNode(-4);
        node.next.next.next.next = node.next;

        var res = detectCycle(node);
        System.out.println(res.val);
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        var slow = head;
        var fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                var ptr = head;

                while (slow != null && ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }

                return slow;
            }
        }
        return null;
    }
}
