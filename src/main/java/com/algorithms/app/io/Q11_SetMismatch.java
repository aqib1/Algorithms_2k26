package com.algorithms.app.io;


public class Q11_SetMismatch {

    // Time complexity O(n)
    // Space complexity O(n)
    public int[] findErrorNums(int[] nums) {
        var found = new boolean[nums.length + 1];

        int a = 0, b = 0;
        for (int num : nums) {
            if (found[num]) {
                a = num;
            }

            found[num] = true;
        }

        for (int i = 1; i < found.length; i++) {
            if (!found[i]) {
                b = i;
            }
        }

        return new int[]{a, b};
    }
}
