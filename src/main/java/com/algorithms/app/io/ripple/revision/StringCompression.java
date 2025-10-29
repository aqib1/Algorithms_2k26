package com.algorithms.app.io.ripple.revision;

import java.util.Arrays;

public class StringCompression {
    public static void main(String[] args) {
        var data = new char[] {'a', 'a', 'b', 'b', 'b'};
        System.out.println(compress(data));
        System.out.println(Arrays.toString(data));
    }

    // Time complexity O(n)
    // Space complexity O(1)
    public static int compress(char[] data) {
        int ptr = 0;
        int count = 0;

        for(int i = 0; i < data.length;) {
            var ch = data[i];
            data[ptr++] = ch;
            while(i < data.length && ch == data[i]) {
                count++;
                i++;
            }
            if(count > 1) {
                for(var n: String.valueOf(count).toCharArray()) {
                    data[ptr++] = n;
                }
            }
            count = 0;
        }
        return ptr;
    }
}
