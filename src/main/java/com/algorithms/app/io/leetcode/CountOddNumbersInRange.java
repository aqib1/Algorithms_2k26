package com.algorithms.app.io.leetcode;

public class CountOddNumbersInRange {

    // Time complexity O(1) and space O(1)
    public int countOdds(int low, int high) {
        int totalNumbers = (high - low) + 1;
        if(low % 2 != 0 && high % 2 != 0)
            return (totalNumbers / 2) + 1;

        return totalNumbers / 2;
    }
}
