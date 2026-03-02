package com.algorithms.app.io.arrays;

import java.util.Arrays;

public class SmallerNumbersThanCurrent {
    static void main() {
        System.out.println(
                Arrays.toString(
                        smallerNumbersThanCurrentBruteForce(
                                new int[] {8, 1, 2, 2, 3}
                        )
                )
        );
    }
    public static int[] smallerNumbersThanCurrentBruteForce(int[] nums) {
        var result = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            var count = 0;
            for(int j = 0; j < nums.length; j++) {
                if(i != j && nums[i] > nums[j]) {
                    count++;
                }
            }
            result[i] = count;
        }

        return result;
    }
}
