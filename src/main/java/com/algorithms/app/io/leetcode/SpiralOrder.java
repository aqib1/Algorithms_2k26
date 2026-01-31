package com.algorithms.app.io.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    // Time complexity O(n*m) & and space O(n)
    public List<Integer> spiralOrder(int[][] matrix) {
        var response = new ArrayList<Integer>();

        if(matrix.length == 0)
            return response;

        var rowStart = 0;
        var rowEnd = matrix.length - 1;
        var colStart = 0;
        var colEnd = matrix[rowStart].length - 1;

        while(rowStart <= rowEnd && colStart <= colEnd) {

            // right
            for(int i = colStart; i <= colEnd; i++) {
                response.add(matrix[rowStart][i]);
            }
            rowStart++;

            // down
            for(int i = rowStart; i <= rowEnd; i++) {
                response.add(matrix[i][colEnd]);
            }
            colEnd--;

            if(rowStart <= rowEnd) {
                // left
                for (int i = colEnd; i >= colStart; i--) {
                    response.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            if(colStart <= colEnd) {
                // up
                for (int i = rowEnd; i >= rowStart; i--) {
                    response.add(matrix[i][colStart]);
                }
            }
            colStart++;
        }

        return response;
    }
}
