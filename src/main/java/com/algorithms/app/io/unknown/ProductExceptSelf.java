package com.algorithms.app.io.unknown;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int [] result = new int[nums.length];
        int pretfix = 1;
        for(int i = 0; i < nums.length; i++) {
            result[i] = pretfix;
            pretfix *= nums[i];
        }

        int suffix = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }
}
