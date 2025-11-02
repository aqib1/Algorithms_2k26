package com.algorithms.app.io.ripple;

import java.util.Arrays;

public class Leetcode875_KokoEatingBanana {
    public static void main(String[] args) {
        System.out.println(
                minEatingSpeedUsingBinarySearch(new int[] {3,6,7,11}, 8)
        );
    }

    // Time complexity O(log(max(p))) * O(p) = O(nlogn)
    // Space O(1)
    public static int minEatingSpeedUsingBinarySearch(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().orElse(-1);

        while(low < high) {
            var mid = low + (high - low) / 2;
            var time = 0;
            for(var pile: piles) {
                time += (pile + mid - 1) / mid;
            }
            if(time <= h) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
