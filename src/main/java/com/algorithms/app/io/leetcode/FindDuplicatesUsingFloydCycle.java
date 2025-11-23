package com.algorithms.app.io.leetcode;

public class FindDuplicatesUsingFloydCycle {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        var fast = nums[0]; var slow = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        slow = nums[0];

        while(fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
