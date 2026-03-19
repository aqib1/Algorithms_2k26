package com.algorithms.app.io.arrays;

import java.util.Arrays;

/**
 *
 * Imagine that we have an image represented as a two-dimensional array containing only zeros and ones.
 * <p>
 * The image has a background consisting of ones, and there is exactly one rectangular region consisting of zeros.
 * <p>
 * Your task is to write a function called findRectangle that identifies this rectangle and returns four values:
 * <p>
 * * row: the row index of the rectangle’s top-left corner.
 * * column: the column index of the rectangle’s top-left corner.
 * * width: the number of columns occupied by the rectangle.
 * * height: the number of rows occupied by the rectangle.
 * <p>
 * For example, suppose the image looks like this:
 *
 *
 */
public class FindRectangle {
    static void main() {
        int[][] image1 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}

        };

        int[][] image2 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0, 0}
        };
        IO.println(
                Arrays.toString(
                        findRectangle(
                               image1
                        )
                )
        );
        IO.println(
                Arrays.toString(
                        findRectangle(
                                image2
                        )
                )
        );
    }
    public static int[] findRectangle(int[][] image) {
        var minRow = image.length;
        var minCol = image[0].length;
        var maxRow = -1;
        var maxCol = -1;

        for(int row = 0; row < image.length; row++) {
            for (int column = 0; column < image[0].length; column++) {
                if(image[row][column] == 0) {
                    minRow = Math.min(minRow, row);
                    minCol = Math.min(minCol, column);
                    maxRow = Math.max(maxRow, row);
                    maxCol = Math.max(maxCol, column);
                }
            }
        }

        var width = (maxCol - minCol) + 1;
        var height = (maxRow - minRow) + 1;
        return new int[]{minRow, minCol, width, height};
    }
}
