package com.algorithms.app.io.ripple;

import java.util.Objects;

public class Leetcode53_MaxSubArray {
    /**
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
     * Example 2:
     *
     * Input: nums = [1]
     * Output: 1
     * Explanation: The subarray [1] has the largest sum 1.
     * Example 3:
     *
     * Input: nums = [5,4,-1,7,8]
     * Output: 23
     * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
     *
     * */

    // Time complexity O(n)
    // Space complexity O(1)
    public int maxSubArray(int[] nums) {
        if(Objects.isNull(nums) || nums.length == 0)
            return -1;
        int max = nums[0], sum = nums[0];

        for(int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);

            if(sum > max) {
                max = sum;
            }
        }

        return max;
    }
}
