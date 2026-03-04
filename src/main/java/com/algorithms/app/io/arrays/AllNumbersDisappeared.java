package com.algorithms.app.io.arrays;

import java.util.ArrayList;
import java.util.List;

public class AllNumbersDisappeared {

    static void main() {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    // time complexity O(n) and space O(n)
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        var response = new ArrayList<Integer>();
        var visited = new boolean[nums.length + 1];
        for (int n : nums)
            visited[n] = true;

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                response.add(i);
            }
        }

        return response;
    }
}
