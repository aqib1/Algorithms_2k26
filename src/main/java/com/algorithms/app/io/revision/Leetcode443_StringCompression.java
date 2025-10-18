package com.algorithms.app.io.revision;

import java.util.Arrays;

public class Leetcode443_StringCompression {
    public static void main(String[] args) {
        var ch = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(compress(ch));

        System.out.println(Arrays.toString(ch));
    }

    // Time complexity O(n)
    // Space complexity O(1)
    public static int compress(char[] chars) {
        int ptr = 0;
        int count = 0;

        for (int i = 0; i < chars.length; ) {
            var current = chars[i];
            chars[ptr++] = current;

            while (i < chars.length && current == chars[i]) {
                count++;
                i++;
            }
            if (count > 1) {
                for (var ch : String.valueOf(count).toCharArray()) {
                    chars[ptr++] = ch;
                }
            }

            count = 0;
        }
        return ptr;
    }
}
