package com.algorithms.app.io.leetcode;

public class FindDifference {
    static void main() {
        System.out.println(
                findTheDifference("abc", "abce")
        );
    }
    public static char findTheDifference(String s, String t) {
        var s1 = 0; var t1 = 0;

        for(char c: s.toCharArray()) {
            s1 += c;
        }

        for(char c: t.toCharArray()) {
            t1 += c;
        }


        return (char)(t1 - s1);
    }
}
