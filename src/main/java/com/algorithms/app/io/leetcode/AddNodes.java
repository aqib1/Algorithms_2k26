package com.algorithms.app.io.leetcode;

public class AddNodes {

    static void main() {
        System.out.println(32 % 10);
    }

    // Time complexity O(n), Space complexity O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var result = new ListNode(-1);
        var ptr = result;
        var carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            if(l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }

            if(l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            ptr.next = new ListNode(carry % 10);
            ptr = ptr.next;
            carry /= 10;
        }

        return result.next;
    }
}
