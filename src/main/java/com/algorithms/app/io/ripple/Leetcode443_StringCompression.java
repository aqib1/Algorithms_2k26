package com.algorithms.app.io.ripple;

import java.util.Arrays;

public class Leetcode443_StringCompression {

    /**
     * Given an array of characters chars, compress it using the following
     * algorithm:
     * <p>
     * Begin with an empty string s. For each group of consecutive repeating
     * characters in chars:
     * <p>
     * If the group's length is 1, append the character to s.
     * Otherwise, append the character followed by the group's length.
     * The compressed string s should not be returned separately, but instead,
     * be stored in the input character array chars. Note that group lengths
     * that are 10 or longer will be split into multiple characters in chars.
     * <p>
     * After you are done modifying the input array, return the new length of
     * the array.
     * <p>
     * You must write an algorithm that uses only constant extra space.
     * <p>
     * Note: The characters in the array beyond the returned length do not
     * matter and should be ignored.
     * <p>
     * Input: chars = ["a","a","b","b","c","c","c"]
     * Output: Return 6, and the first 6 characters of the input array should
     * be: ["a","2","b","2","c","3"]
     * Explanation: The groups are "aa", "bb", and "ccc". This compresses to
     * "a2b2c3".
     **/
    public static int compress(char[] chars) {
        int count = 0;
        int pointer = 0;

        for (int i = 0; i < chars.length;) {
            char current = chars[i];
            while (i < chars.length && chars[i] == current) {
                count++;
                i++;
            }
            chars[pointer++] = current;
            if(count > 1) {
                for (char ch : String.valueOf(count).toCharArray()) {
                    chars[pointer++] = ch;
                }
            }
            count = 0;
        }

        return pointer;
    }

    public static void main(String[] args) {
        var data = new char[]{'a', 'b', 'c'};
        compress(data);
        System.out.println(Arrays.toString(data));
    }
}
