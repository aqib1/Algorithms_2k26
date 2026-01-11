package com.algorithms.app.io.leetcode;

import java.util.ArrayList;

public class PlusOne {
    static void main() {
        plusOne(new int[] {1, 9, 9});
        System.out.println(

        );
    }

    public static int[] plusOneFaster(int[] digits) {
        return null;
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
