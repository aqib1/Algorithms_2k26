package com.algorithms.app.io.leetcode;

public class IsMonotonic {
    static void main() {
        System.out.println(isMonotonic(
                new int[] {1, 2, 2, 5}
        ));
    }
    public static boolean isMonotonic(int [] nums) {
        var increasing = false; var decreasing = false;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                increasing = true;
            } else if (nums[i] < nums[i - 1]) {
                decreasing = true;
            }

            if(increasing && decreasing)
                return false;
        }
        return true;
    }
}
