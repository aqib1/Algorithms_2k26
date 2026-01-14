package com.algorithms.app.io.leetcode;

import java.util.stream.IntStream;

public class ArraySign {
    // time complexity O(n) and space complexity O(1)
    public int arraySignFast(int[] nums) {
        var negativeCount = 0;
        for(var num: nums) {
            if(num == 0) {
                // if any of the num is 0, the whole product will be 0
                // thus result will be 0.
                return 0;
            }

            if(num < 0)
                negativeCount++;
        }

        // if negativeCount is event it will make product positive
        return negativeCount % 2 == 0 ? 1 : -1;
    }

    public int arraySignSlowApproach(int[] nums) {
        return Integer.compare(
                IntStream.of(nums).reduce(1, (x, y) -> x * y),
                0
        );
    }
}
