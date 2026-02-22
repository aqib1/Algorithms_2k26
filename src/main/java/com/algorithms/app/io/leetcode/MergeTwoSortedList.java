package com.algorithms.app.io.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeTwoSortedList {
    static void main() {
        var l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        var l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.println(mergeTwoLists(l1, l2));


    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return mergeLists(new ListNode[] { list1, list2 });
    }

    // time complexity Onlog(n) and space complexity O(n)
    public static ListNode mergeLists(ListNode[] lists) {
        var minHeap = new PriorityQueue<ListNode>(Comparator.comparingInt(l -> l.val));

        for(ListNode node: lists)
            if(node != null)
                minHeap.offer(node);

        var response = new ListNode();
        var ptr = response;

        while(!minHeap.isEmpty()) {
            var curr = minHeap.poll();
            if(curr.next != null) {
                minHeap.offer(curr.next);
            }
            ptr.next = curr;
            ptr = ptr.next;
        }

        return response.next;
    }
}
