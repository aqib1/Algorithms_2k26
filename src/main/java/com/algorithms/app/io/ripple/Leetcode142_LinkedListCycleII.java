package com.algorithms.app.io.ripple;

import com.algorithms.app.io.common.ListNode;

public class Leetcode142_LinkedListCycleII {
    // Time complexity O(n)
    // Space complexity O(1)
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        var slow = head;
        var fast = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        } while (fast != null && fast.next != null);

        if (slow != fast)
            return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }
}
