package com.algorithms.app.io;

public class Leetcode2_AddingTwoNumbers {

    public static void main(String[] args) {
        var l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        var l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println(addTwoNumbers(l1, l2));
    }

    // Time complexity is O(n + m) where n is number of nodes of l1 and m is number of nodes of l2
    // Space complexity is O(n + m)
    // Where n + m = n, so O(n)
    private static ListNode addTwoNumbers(
            ListNode l1,
            ListNode l2
    ) {
        ListNode sum = new ListNode(0);
        ListNode pointer = sum;
        int carry = 0;

        while(l1 != null || l2 != null) {
            int compound = carry;
            if(l1 != null) {
                compound += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                compound += l2.val;
                l2 = l2.next;
            }

            sum.next = new ListNode(compound % 10);
            carry = compound / 10;
            sum = sum.next;
        }

        if(carry != 0) sum.next = new ListNode(carry);
        return pointer.next;
    }
}
