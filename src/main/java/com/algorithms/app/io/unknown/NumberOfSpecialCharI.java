package com.algorithms.app.io.unknown;

public class NumberOfSpecialCharI {
    public int numberOfSpecialChars(String word) {
        int count = 0;
        boolean []upper = new boolean[26];
        boolean []lower = new boolean[26];

        for(char c : word.toCharArray()) {
            if(Character.isUpperCase(c))
                upper[c - 'A'] = true;
            else
                lower[c - 'a'] = true;
        }

        for(int i = 0; i < 26; i++) {
            if(upper[i] && lower[i]) {
                count++;
            }
        }

        return count;
    }
}
