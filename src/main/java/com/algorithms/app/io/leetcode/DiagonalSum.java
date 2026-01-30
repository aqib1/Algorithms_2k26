package com.algorithms.app.io.leetcode;

public class DiagonalSum {
    static void main() {
        System.out.println(
                diagonalSum(
                        new int[][] {
                                {1, 2, 3},
                                {4, 5, 6},
                                {7, 8, 9}
                        }
                )
        );
    }

    // Time complexity O(n) and space O(1)
    public static int diagonalSum(int[][] mat) {
        int n = mat.length;
        boolean isOdd = n % 2 != 0;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            sum += mat[i][i];
        }

        int j = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(isOdd && i == n / 2) {
                j++;
                continue;
            }
            sum += mat[j++][i];
        }

        return sum;
    }
}
