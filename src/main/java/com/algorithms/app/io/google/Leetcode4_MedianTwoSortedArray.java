package com.algorithms.app.io.google;

public class Leetcode4_MedianTwoSortedArray {

    public static void main(String[] args) {
        System.out.println(
                findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4})
        );
        System.out.println(
                findMedianFaster(new int[] {1, 2}, new int[] {3, 4})
        );
    }


    // Time complexity O(log min(n,m))
    // Space is O(1)
    public static double findMedianFaster(int[] num1, int[] num2) {
        if(num1.length > num2.length) {
            var tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        int minIndex = 0, maxIndex = num1.length;
        int x = 0, y = 0;
        double median = 0.0;
        boolean isOdd = (num1.length + num2.length) % 2 != 0;

        while(minIndex <= maxIndex) {
            x = (minIndex + maxIndex) / 2;
            y = ((num1.length + num2.length + 1) / 2) - x;

            if(x > 0 && y < num2.length && num1[x - 1] > num2[y]) {
                maxIndex = x - 1;
            } else if(y > 0 && x < num1.length && num2[y - 1] > num1[x]) {
                minIndex = x + 1;
            } else {
                if(x == 0) {
                    median = num2[y - 1];
                } else if(y == 0) {
                    median = num1[x - 1];
                } else {
                    median = Math.max(num1[x - 1], num2[y - 1]);
                }
                break;
            }
        }

        if(isOdd)
            return median;
        if(x == num1.length)
            return (median + num2[y]) / 2.0;
        if(y == num2.length)
            return (median + num1[x]) / 2.0;

        return (median + Math.min(num1[x], num2[y])) / 2.0;
    }

    // Brute force
    // Time complexity O(n + m)
    // Space complexity O(n + m)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
