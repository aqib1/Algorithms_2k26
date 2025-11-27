package com.algorithms.app.io.leetcode;

public class MergedTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var ptr = new ListNode();
        var head = ptr;
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                ptr.next = list1;
                list1 = list1.next;
            } else {
                ptr.next = list2;
                list2 = list2.next;
            }
            ptr = ptr.next;
        }

        if(list1 != null) {
            ptr.next = list1;
        }

        if(list2 != null) {
            ptr.next = list2;
        }

        return head.next;
    }
}
