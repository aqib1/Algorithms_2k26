package com.algorithms.app.io.ripple;

import java.util.HashMap;

public class Leetcode560_SubarraySumEqualsK {

    // Time complexity O(n)
    // Space complexity O(n)
    public int subarraySum(int[] nums, int k) {
        int prefixSum = 0;
        var prefixSumMap = new HashMap<Integer, Integer>();
        int count = 0;

        for(int n: nums) {
            prefixSumMap.put(
                    prefixSum,
                    prefixSumMap.getOrDefault(prefixSum, 0) + 1
            );

            prefixSum += n;

            if(prefixSumMap.containsKey(prefixSum - k)) {
                count += prefixSumMap.get(prefixSum - k);
            }
        }

        return count;
    }
}
