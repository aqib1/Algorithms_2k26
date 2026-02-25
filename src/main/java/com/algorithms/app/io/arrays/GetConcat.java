package com.algorithms.app.io.arrays;

import java.util.Arrays;

public class GetConcat {
    static void main() {
        System.out.println(
                Arrays.toString(
                        getConcatenation(new int[] {1, 2, 1})
                )
        );
    }

    // Time complexity O(n) and Space complexity O(2n)
    public static int[] getConcatenation(int[] nums) {
        var response = new int[2 * nums.length];
        for(int i = 0; i < nums.length; i++) {
            response[i] = nums[i];
            response[i + nums.length] = nums[i];
        }
        return response;
    }
}
