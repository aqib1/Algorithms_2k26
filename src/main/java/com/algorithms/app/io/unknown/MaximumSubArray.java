package com.algorithms.app.io.unknown;

public class MaximumSubArray {
    // Time complexity O(n)
    // Space complexity O(1)
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);

            if(sum > max)
                max = sum;
        }

        return max;
    }
}
