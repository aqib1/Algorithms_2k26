package com.algorithms.app.io;

import java.util.Arrays;

public class Leetcode88_MergeSortedArray {
    public static void main(String[] args) {
        merge_brute_force(
                new int[] {1, 2, 3, 0, 0, 0},
                3,
                new int[] {2, 5, 6},
                3
        );
    }

    // Time complexity O(n2)
    // Space complexity O(1)
    public static void merge_brute_force(int[] num1, int m, int[] num2, int n) {
        if (num2.length == 0)
            return;

        int i = 0, j = 0;
        while (i < m && j < n) {
            if (num2[j] < num1[i]) {
                for (int l = m; l > i; l--) {
                    num1[l] = num1[l - 1];
                }

                num1[i] = num2[j];
                m++;
                j++;
            }
            i++;
        }

        while(j < n) {
            num1[i++] = num2[j++];
        }

        System.out.println(Arrays.toString(num1));
    }
}
