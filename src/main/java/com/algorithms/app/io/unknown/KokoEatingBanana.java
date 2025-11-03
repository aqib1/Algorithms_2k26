package com.algorithms.app.io.unknown;

import java.util.stream.IntStream;

public class KokoEatingBanana {
    // Time complexity O(log max(p)) * O(p) = Onlogn
    // Space complexity O(1)
    public int minEatingSpeed(int[] piles, int h) {
        var low = 1;
        var high = IntStream.of(piles).max().orElse(1);

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
