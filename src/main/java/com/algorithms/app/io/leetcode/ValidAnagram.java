package com.algorithms.app.io.leetcode;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        var ch = new char[26];
        for(int i = 0; i < s.length(); i++) {
            ch[s.charAt(i) - 'a']++;
            ch[t.charAt(i) - 'a']--;
        }
        for (int c : ch) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
