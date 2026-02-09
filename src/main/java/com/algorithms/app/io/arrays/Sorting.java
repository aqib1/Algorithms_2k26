package com.algorithms.app.io.arrays;

import java.util.Arrays;

public class Sorting {
    static void main() {
        var data = new int[] {2, -9, 1, 0, 3, -11, -8, 129, 1};
        insertionSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void insertionSort(int [] args) {
        for(int i = 1; i < args.length; i++) {
            var key = args[i];
            var j = i - 1;
            while(j >= 0 && key < args[j]) {
                args[j + 1] = args[j];
                j--;
            }
            args[j + 1] = key;
        }
    }
}
