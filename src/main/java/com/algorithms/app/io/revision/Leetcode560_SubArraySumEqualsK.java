package com.algorithms.app.io.revision;

import java.util.HashMap;

public class Leetcode560_SubArraySumEqualsK {

    // Time complexity O(n)
    // Space complexity O(n)
    public int solution(int[] arr, int k) {
        int count = 0;
        int prefixSum = 0;
        var prefixSumCount = new HashMap<Integer, Integer>();

        for(int i: arr) {
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
            prefixSum += i;

            if(prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
        }

        return count;
    }
}
