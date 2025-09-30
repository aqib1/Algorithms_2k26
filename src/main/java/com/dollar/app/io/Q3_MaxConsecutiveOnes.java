package com.dollar.app.io;

public class Q3_MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = Integer.MIN_VALUE;

        int countOnes = 0;
        for (int num : nums) {
            if (num == 1) {
                countOnes++;
            } else {
                max = Math.max(countOnes, max);
                countOnes = 0;
            }
        }

        return Math.max(countOnes, max);
    }
}
