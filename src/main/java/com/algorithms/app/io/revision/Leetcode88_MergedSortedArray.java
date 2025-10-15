package com.algorithms.app.io.revision;

import java.util.Arrays;

public class Leetcode88_MergedSortedArray {
    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] num2 = new int[]{2, 5, 6};

        merge(num1, 3, num2, 3);

        System.out.println(Arrays.toString(num1));
    }

    // Time Complexity O(n + m) and space O(1)
    public static void merge(int[] num1, int m, int[] num2, int n) {
        if (num2.length == 0)
            return;
        int i = m - 1;
        int j = n - 1;
        int k = (m + n) - 1;

        while(j >= 0) {
            if(i >= 0 && num1[i] >= num2[j]) {
                num1[k--] = num1[i--];
            } else {
                num1[k--] = num2[j--];
            }
        }
    }
}
