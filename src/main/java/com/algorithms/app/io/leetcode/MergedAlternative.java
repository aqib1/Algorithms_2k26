package com.algorithms.app.io.leetcode;

public class MergedAlternative {

    /**
     * Input: word1 = "abc", word2 = "pqr"
     * Output: "apbqcr"
     * Explanation: The merged string will be merged as so:
     * word1:  a   b   c
     * word2:    p   q   r
     * merged: a p b q c r
     * */

    public String mergeAlternately(String word1, String word2) {
        if(word1.isEmpty())
            return word2;

        if(word2.isEmpty())
            return word1;
        var builder = new StringBuilder();
        int i = 0, j = 0, even = 0;
        while (i < word1.length() && j < word2.length()) {
            if(even % 2 == 0) {
                builder.append(word1.charAt(i++));
            } else {
                builder.append(word2.charAt(j++));
            }
            even++;
        }

        if(i < word1.length()) {
            builder.append(word1.substring(i));
        }

        if(j < word2.length()) {
            builder.append(word2.substring(j));
        }

        return builder.toString();
    }
}
