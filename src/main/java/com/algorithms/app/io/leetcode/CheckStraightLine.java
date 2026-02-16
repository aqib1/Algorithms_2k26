package com.algorithms.app.io.leetcode;

public class CheckStraightLine {
    static void main() {
        System.out.println(checkStraightLine(
                new int[][]{
                        {1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}
                }
        ));
    }


    // Time complexity O(n) and space O(1)
    public static boolean checkStraightLine(int[][] coordinates) {
        var dx = coordinates[1][0] - coordinates[0][0];
        var dy = coordinates[1][1] - coordinates[0][1];

        for(int i = 1; i < coordinates.length; i++) {
            var xi = coordinates[i][0] - coordinates[i - 1][0];
            var yi = coordinates[i][1] - coordinates[i - 1][1];

            if(dx * yi != dy * xi)
                return false;
        }

        return true;
    }
}
