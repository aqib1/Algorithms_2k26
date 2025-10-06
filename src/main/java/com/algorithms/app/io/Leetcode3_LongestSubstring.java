package com.algorithms.app.io;

public class Leetcode3_LongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    // Time complexity O(n) & Space complexity O(256)
    public static int lengthOfLongestSubstring(String s) {
        var charCount = new int[256];
        int maxLen = 0;
        int i = 0, j = 0;

        while(j < s.length()) {
            if(charCount[s.charAt(j)] < 1) {
                maxLen = Math.max((j - i) + 1, maxLen);
                charCount[s.charAt(j++)]++;
            } else {
                charCount[s.charAt(i++)]--;
            }
        }

        return maxLen;
    }
}
