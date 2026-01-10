package com.algorithms.app.io.leetcode;

import java.util.Arrays;

public class LongestPrefixString {
    static void main() {
        System.out.println("abab -> " + repeatedSubstringPattern("abab")); // true
        System.out.println("aba -> " + repeatedSubstringPattern("aba"));   // false
        System.out.println("abac -> " + repeatedSubstringPattern("abac")); // false
        System.out.println("abcabcabcabc -> " + repeatedSubstringPattern("abcabcabcabc")); // true
        System.out.println("a -> " + repeatedSubstringPattern("a")); // false
    }

    public static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] lpsArray = lps(s);
        int lastLPS = lpsArray[n - 1];
        int patternLength = n - lastLPS;
        return lastLPS > 0 && n % patternLength == 0;
    }
    public static int[] lps(String str) {
        var chars = str.toCharArray();
        var lps = new int[str.length()];
        var len = 0; var i = 1;

        while(i < chars.length) {
            if(chars[len] == chars[i]) {
                lps[i] = ++len;
                i++;
            } else {
                if(len != 0)
                    len = lps[len-1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
