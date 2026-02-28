package com.algorithms.app.io.arrays;

public class FindMaxConsecutiveOnes {
    static void main() {
        System.out.println(
                findMaxConsecutiveOnesWithTwoPointer(
                        new int[] {1, 1, 1, 1, 0, 1, 1 ,1}
                )
        );
    }

    // Using two pointers time O(n), space O(1)
    public static int findMaxConsecutiveOnesWithTwoPointer(int[] nums) {
        int max = 0;
        int i = 0, j = 0;
        while(j < nums.length) {
            if(nums[j] == 1) {
                max = Integer.max(max, (j - i) + 1);
                j++;
            } else {
                j++;
                i = j;
            }
        }
        return max;
    }


    // Using two pointers time O(n), space O(1)
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int countOnes = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1)
                countOnes++;
            else countOnes = 0;

            max = Math.max(max, countOnes);
        }
        return max;
    }
}
