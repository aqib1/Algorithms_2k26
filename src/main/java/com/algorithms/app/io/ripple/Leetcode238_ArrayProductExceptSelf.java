package com.algorithms.app.io.ripple;

import java.util.Arrays;

public class Leetcode238_ArrayProductExceptSelf {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(
                new int[]{1, 2, 3, 4}
        )));
    }

    // Time complexity O(n)
    // Space complexity O(n)
    public static int[] productExceptSelf(int[] nums) {
        var result = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            result[j] *= suffix;
            suffix *= nums[j];
        }

        return result;
    }
}
