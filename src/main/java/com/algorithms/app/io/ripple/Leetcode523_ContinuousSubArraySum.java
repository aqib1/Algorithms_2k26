package com.algorithms.app.io.ripple;

import java.util.HashMap;

public class Leetcode523_ContinuousSubArraySum {
    public static void main(String[] args) {
        System.out.println(5 % 6);
    }

    // Time complexity O(n)
    // Space complexity O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        var prefixModMap = new HashMap<Integer, Integer>();
        var prefixMod = 0;
        prefixModMap.put(0, -1);

        for(int i = 0; i < nums.length; i++) {
            prefixMod = (nums[i] + prefixMod) % k;
            if(prefixModMap.containsKey(prefixMod)) {
                if(i - prefixModMap.get(prefixMod) > 1)
                    return true;
            } else {
                prefixModMap.put(prefixMod, i);
            }
        }
        return false;
    }
}
