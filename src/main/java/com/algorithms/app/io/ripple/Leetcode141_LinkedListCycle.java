package com.algorithms.app.io.ripple;

import com.algorithms.app.io.common.ListNode;

public class Leetcode141_LinkedListCycle {

    // Time complexity O(n) and space O(1)
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;

        var slow = head; var fast = head;

        do {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;

        } while(fast != null && fast.next != null);

        return false;
    }
}
