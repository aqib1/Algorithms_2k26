package com.algorithms.app.io.leetcode;

public class MaxLenOfSubStr {

    static void main() {
        System.out.println(
                lengthOfLongestSubstring("abcadbcbb")
        );
    }

    // Time complexity O(n) and space O(256)
    public static int lengthOfLongestSubstring(String s) {
        var count = new char[256];
        int maxLen = 0;
        int i = 0, j = 0;

        while(j < s.length()) {
            if(count[s.charAt(j)] < 1) {
                count[s.charAt(j++)]++;
                maxLen = Math.max(maxLen, j - i);
            } else {
                count[s.charAt(i++)]--;
            }
        }

        return maxLen;
    }
}
