package com.algorithms.app.io.leetcode;

import java.util.stream.IntStream;

public class MaximumWealth {
    public int maximumWealth(int[][] accounts) {
        var max = Integer.MIN_VALUE;
        for (int[] account : accounts) {
            max = Math.max(max, IntStream.of(account).sum());
        }
        return max;
    }
}
