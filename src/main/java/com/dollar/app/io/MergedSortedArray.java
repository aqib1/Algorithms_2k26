package com.dollar.app.io;

import java.util.Arrays;

public class MergedSortedArray {
    public static void main(String[] args) {
        merge(
                new int[] {-1,0,0,3,3,3,0,0,0},
                6,
                new int[] {1,2,2},
                3
        );
    }
    public static void merge(int [] num1, int m, int [] num2, int n) {
            if(num2.length == 0)
                return;
            int j = 0, i = 0;
            while(i < m && j < n) {
                if(num2[j] < num1[i]) {
                    for(int l = m; l > i; l--) {
                        num1[l] = num1[l - 1];
                    }
                    num1[i] = num2[j];
                    m++;
                    j++;
                }
                i++;
            }

            while(j < n) {
                num1[i++] = num2[j++];
            }

        System.out.println(Arrays.toString(num1));
    }
}
