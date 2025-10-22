package com.algorithms.app.io.revision;

import java.util.Arrays;

public class Leetcode238_ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        var data = new int[] {1,2,3,4};
        System.out.println(
                Arrays.toString(productExceptSelf(data))
        );

    }

    // Time complexity O(n)
    // Space complexity O(n)
    public static int[] productExceptSelf(int[] nums) {
        var result = new int[nums.length];
        var prefix = 1;
        for(int i = 0; i < nums.length; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }
        int suffix = 1;
        for(int i = nums.length - 1; i >=0 ; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }
        return result;
    }
}
