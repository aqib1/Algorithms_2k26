package com.algorithms.app.io.booking.com.hackkerrankTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeanderingArray {
    // Time complexity O(n log n)
    // Space complexity O(n)
    public static List<Integer> meanderingArray(List<Integer> unsorted) {
        Collections.sort(unsorted);
        var n = unsorted.size();
        var result = new ArrayList<Integer>(n); // make it fill to avoid dynamic expenstion which will increse time complexity
        var left = 0;
        var right = n - 1;
        while(left <= right) {
            if(left != right) {
                result.add(unsorted.get(right--));
                result.add(unsorted.get(left++));
            } else {
                result.add(unsorted.get(right--));
            }
        }
        return result;
    }
}
