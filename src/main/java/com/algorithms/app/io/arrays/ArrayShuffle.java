package com.algorithms.app.io.arrays;

import java.util.Arrays;

public class ArrayShuffle {
    static void main() {
        System.out.println(
                Arrays.toString(
                        shuffle(
                                new int[]{2, 5, 1, 3, 4, 7},
                                3
                        )
                )
        );
    }

    // Time complexity O(n) and space complexity O(n)
    public static int[] shuffle(int[] nums, int n) {
        var result = new int[nums.length];
        var a = 0;
        var b = n;
        for (int i = 0; i < nums.length; i += 2) {
            result[i] = nums[a++];
            result[i + 1] = nums[b++];
        }
        return result;
    }
}
