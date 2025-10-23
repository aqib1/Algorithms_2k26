package com.algorithms.app.io.revision;

import java.util.HashMap;

public class Leetcode560_SubarraySumEqualsKII {

    public static void main(String[] args) {
        System.out.println(
                subarraySum(new int[] {1, 2, 3}, 3)
        );
    }

    // Time complexity O(n)
    // Space complexity O(n)
    public static int subarraySum(int[] nums, int k) {
        var prefixSum = 0;
        var count = 0;
        var prefixSumMap = new HashMap<Integer, Integer>();

        for(int n: nums) {
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
            prefixSum += n;

            if(prefixSumMap.containsKey(prefixSum - k)) {
                count += prefixSumMap.get(prefixSum - k);
            }
        }

        return count;
    }
}
