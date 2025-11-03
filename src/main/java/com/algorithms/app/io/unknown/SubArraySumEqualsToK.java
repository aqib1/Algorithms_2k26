package com.algorithms.app.io.unknown;

import java.util.HashMap;

public class SubArraySumEqualsToK {
    public int subarraySum(int[] nums, int k) {
        int prefixSum = 0;
        int count = 0;
        var prefixMap = new HashMap<Integer, Integer>();

        for (int num : nums) {
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
            prefixSum += num;
            if (prefixMap.containsKey(prefixSum - k)) {
                count += prefixMap.get(prefixSum - k);
            }
        }

        return count;
    }
}
