package com.algorithms.app.io;

public class Leetcode4_MedianTwoSortedArray {

    // Time complexity O(n)
    // Space complexity O(n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        var merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;

        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        while(i < nums1.length) {
            merged[k++] = nums1[i++];
        }

        while(j < nums2.length) {
            merged[k++] = nums2[j++];
        }

        int mid = merged.length / 2;

        if(merged.length % 2 == 0)
            return (merged[mid - 1] + merged[mid]) / 2.0;
        else
            return merged[mid];
    }
}
