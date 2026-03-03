package com.algorithms.app.io.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SmallerNumbersThanCurrent {
    static void main() {
        System.out.println(
                Arrays.toString(
                        smallerNumbersThanCurrent(
                                new int[] {8, 1, 2, 2, 3}
                        )
                )
        );
    }

    // Time complexity O(n2) and space O(n)
    public static int[] smallerNumbersThanCurrentBruteForce(int[] nums) {
        var result = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            var count = 0;
            for(int j = 0; j < nums.length; j++) {
                if(i != j && nums[i] > nums[j]) {
                    count++;
                }
            }
            result[i] = count;
        }

        return result;
    }

    // Time complexity O(nlogn) and space O(n)
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        var nIndexMap = new HashMap<Integer, List<Integer>>();
        var result = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            var indexes = nIndexMap.getOrDefault(nums[i], new ArrayList<>());
            indexes.add(i);
            nIndexMap.put(nums[i], indexes);
        }

        Arrays.sort(nums);

        int count = 0;
        for(int i = 0; i < nums.length; ) {
            var indexes = nIndexMap.get(nums[i]);
            if(indexes.size() == 1) {
                result[indexes.getFirst()] = count;
                count++;
                i++;
            } else {
                var c = count;
                for(int index: indexes) {
                    result[index] = c;
                    i++;
                    count++;
                }
            }

        }

        return result;
    }
}
