package com.algorithms.app.io.leetcode;

import java.util.HashMap;

public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            var complimentByIndex = new HashMap<Integer, Integer>();
            for(int i = 0; i < nums.length; i++) {
                int compliment = target - nums[i];
                if(complimentByIndex.containsKey(compliment)) {
                    return new int[] {complimentByIndex.get(compliment), i};
                }
                complimentByIndex.put(nums[i], i);
            }
            return null;
        }
    }
}
