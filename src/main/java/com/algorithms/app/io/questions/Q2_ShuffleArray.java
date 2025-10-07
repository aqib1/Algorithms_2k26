package com.algorithms.app.io.questions;

public class Q2_ShuffleArray {
    // Time O(n)
    // Space O(n)
    public int[] shuffle(int[] nums, int n) {
        int xi = 0, yi = n;
        var result = new int[nums.length];
        int i = 0;
        while(i < result.length) {
            result[i++] = nums[xi++];
            result[i++] = nums[yi++];
        }

        return result;
    }
}
