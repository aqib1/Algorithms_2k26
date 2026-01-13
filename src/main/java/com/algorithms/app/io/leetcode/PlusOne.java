package com.algorithms.app.io.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class PlusOne {
    static void main() {
        System.out.println(
                Arrays.toString(plusOneFaster(new int[] {1, 2, 3}))
        );
    }

    // Time complexity O(n), space O(n)
    public static int[] plusOneFaster(int[] digits) {
        for(var i=digits.length - 1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        var sum = new int[digits.length + 1];
        sum[0] = 1;
        return sum;
    }

    public static int[] plusOne(int[] digits) {
        var count = new ArrayList<Integer>();
        var add = 1;
        for(int i = digits.length - 1; i >= 0; i--) {
            var sum = digits[i] + add;
            add = sum / 10;
            count.addFirst(sum % 10);
        }

        if(add > 0) {
            count.addFirst(add);
        }

        return count.stream().mapToInt(Integer::intValue).toArray();
    }
}
