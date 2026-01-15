package com.algorithms.app.io.leetcode;

import java.util.Arrays;

public class CanMakeArithmeticProgression {
    static void main() {
        System.out.println(
                canMakeArithmeticProgression(new int[] {3, 5, 1})
        );
    }

    // Time complexity OnLog(n) and worst-case O(n2) but highly unlikely.
    // Space complexity OLog(n)
    public static boolean canMakeArithmeticProgression(int [] arr) {
        Arrays.sort(arr);
        int sum = arr[0] - arr[1];
        for(int i = 1; i < arr.length - 1; i++) {
            if(sum != arr[i] - arr[i+1]) {
                return false;
            }
        }
        return true;
    }
}
