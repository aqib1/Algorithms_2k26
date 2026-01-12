package com.algorithms.app.io.leetcode;

import java.util.Arrays;

public class MoveZeros {
    static void main() {
        var data = new int[] {0, 1, 0, 0, 2, 3, 0 , 12, 0};
        moveZerosWithoutExtraSpaceButSlow(data);
        System.out.println(Arrays.toString(data));
    }

    // Time O(n) & Space O(n)
    public void moveZeroes(int[] nums) {
        var newNum = new int[nums.length];
        int i = 0;
        for(var n: nums) {
            if(n != 0)
                newNum[i++] = n;
        }
        System.arraycopy(newNum, 0, nums, 0, nums.length);
    }
    // Time O(n * m) & Space O(1)
    public static void moveZerosWithoutExtraSpaceButSlow(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                for(int j = i + 1; j < nums.length; j++) {
                    if(nums[j] != 0) {
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }
        }
    }

    // Time O(n) & Space O(1)
    public static void moveZerosWithoutExtraSpaceFast(int[] nums) {
        var prev = 0; var curr = 0;
        while(curr < nums.length) {
            if(nums[curr] == 0) {
                curr++;
            } else {
                var tmp = nums[curr];
                nums[curr] = nums[prev];
                nums[prev] = tmp;

                curr++;
                prev++;
            }
        }
    }
}
