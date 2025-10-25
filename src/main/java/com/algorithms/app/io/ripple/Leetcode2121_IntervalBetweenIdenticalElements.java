package com.algorithms.app.io.ripple;

import java.util.*;

public class Leetcode2121_IntervalBetweenIdenticalElements {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        getDistance(new int[] {
                                2, 1, 3, 1, 2, 3, 3
                        })
                )
        );
    }

    public static long[] getDistance(int[] arr) {
        var response = new long[arr.length];
        var numByIndexes = new HashMap<Integer, Set<Integer>>();

        for(int i = 0; i < arr.length; i++) {
            if(numByIndexes.containsKey(arr[i])) {
                numByIndexes.get(arr[i]).add(i);
            } else {
                numByIndexes.put(arr[i], new HashSet<>(Set.of(i)));
            }
        }

        for(int i = 0; i < arr.length; i++) {
            response[i] = getDistance(i, arr, numByIndexes);
        }
        return response;
    }

    public static long getDistance(int i, int[] arr, Map<Integer, Set<Integer>> numByIndexes) {
        var modSum = 0;
        var indexes = numByIndexes.get(arr[i]);
        for(var index: indexes) {
            if(i != index) {
                modSum += Math.abs(i - index);
            }
        }
        return modSum;
    }
}
