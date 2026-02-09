package com.algorithms.app.io.leetcode;

import java.util.Arrays;

public class SetZeros {
    static void main() {
        var matrix = new int[][] {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZerosSlow(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    // Time complexity O(n)2 and Space O(n)2
    public static void setZerosSlow(int[][] matrix) {
        var visited = new boolean [matrix.length][matrix[0].length];
        var rowLimit = matrix.length;
        var colLimit = matrix[0].length;
        for(int i = 0; i < rowLimit; i++) {
            for(int j = 0; j < colLimit; j++) {
                if(matrix[i][j] == 0 && !visited[i][j]) {
                    // right move
                    for(int rightMove = j; rightMove < colLimit; rightMove++) {
                        if(matrix[i][rightMove] != 0 && !visited[i][rightMove]) {
                            matrix[i][rightMove] = 0;
                            visited[i][rightMove] = true;
                        }
                    }
                    // left move
                    for(int leftMove = j; leftMove >= 0; leftMove--) {
                        if(matrix[i][leftMove] != 0 && !visited[i][leftMove]) {
                            matrix[i][leftMove] = 0;
                            visited[i][leftMove] = true;
                        }
                    }

                    // up move
                    for(int upMove = i; upMove >= 0; upMove--) {
                        if(matrix[upMove][j] != 0 && !visited[upMove][j]) {
                            matrix[upMove][j] = 0;
                            visited[upMove][j] = true;
                        }
                    }

                    // down move
                    for(int downMove = i; downMove < rowLimit; downMove++) {
                        if(matrix[downMove][j] != 0 && !visited[downMove][j]) {
                            matrix[downMove][j] = 0;
                            visited[downMove][j] = true;
                        }
                    }
                }
            }
        }
    }
}
