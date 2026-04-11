package com.algorithms.app.io.expedia;

import java.util.stream.IntStream;

public class SmallestDivisor {
    public int smallestDivisor(int[] nums, int threshold) {
        int min = 1;
        int max = IntStream.of(nums).max().orElse(Integer.MAX_VALUE);
        int ans = 0;
        while(min <= max) {
            int mid = min + (max - min) / 2;
            int res = divSum(nums, mid);

            if(res <= threshold) {
                ans = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return ans;
    }

    private int divSum(int[] nums, int mid) {
        int sum = 0;

        for(int n: nums) {
            sum += (int) Math.ceil((double) n / mid);
        }

        return sum;
    }
}
