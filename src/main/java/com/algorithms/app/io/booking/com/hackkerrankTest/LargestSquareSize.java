package com.algorithms.app.io.booking.com.hackkerrankTest;

import java.util.List;

public class LargestSquareSize {
    // Time complexity O(n x n)
    // Space complexity O(n x n)
    public static int findLargestSquareSize(
            List<List<Integer>> samples
    ) {
        // Write your code here
        var limit = samples.size();
        if(limit == 0) {
            // early return in case of empty samples
            return 0;
        }
        var sizeCalculation = new int[limit][limit];
        var largestSquareSize = 0;

        for(int row = 0; row < limit; row++) {
            for(int col = 0; col < limit; col++) {
                if(samples.get(row).get(col) == 1) {
                    if(row == 0 || col == 0) {
                        sizeCalculation[row][col] = 1;
                    } else {
                        sizeCalculation[row][col] = 1 + Math.min(
                                sizeCalculation[row - 1][col],
                                Math.min(sizeCalculation[row][col - 1], sizeCalculation[row - 1][col - 1])
                        );
                    }
                    largestSquareSize = Math.max(largestSquareSize, sizeCalculation[row][col]);
                }
            }
        }

        return largestSquareSize;
    }
}
