package com.algorithms.app.io.leetcode;

import java.util.Stack;

/**
 *
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * Example 2:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 *
**/
public class AddTwoNumberII {
    static void main() {
        var node1 = new ListNode(7);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);
        node1.next.next.next = new ListNode(3);

        var node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        System.out.println(
                addTwoNumbers(node1, node2)
        );
    }

    // O(2n) + O(n)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var s1 = new Stack<Integer>();
        var s2 = new Stack<Integer>();
        var sum = new ListNode();
        var ptr = sum;
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        var carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            var add = carry;

            if(!s1.isEmpty()) add += s1.pop();
            if(!s2.isEmpty()) add += s2.pop();

            sum.next = new ListNode(add % 10);
            carry = add / 10;
            sum = sum.next;
        }

        return previous(ptr.next);
    }

    public static ListNode previous(ListNode node) {
        ListNode curr = node, prev = null;
        while(curr != null) {
            var next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}

