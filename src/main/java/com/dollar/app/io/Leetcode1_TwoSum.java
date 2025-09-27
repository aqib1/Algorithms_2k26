package com.dollar.app.io;

import java.util.Arrays;
import java.util.HashMap;

public class Leetcode1_TwoSum {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        twoSum(
                                new int[] {2, 7, 11, 15},
                                9
                        )
                )
        );
    }

    // Time complexity O(n)
    // Space complexity O(n)
    public static int[] twoSum(int []nums, int target) {
        var countMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(countMap.containsKey(complement)) {
                return new int[] { countMap.get(complement), i };
            }
            countMap.put(nums[i], i);
        }
        return new int[] {};
    }
}
