package com.algorithms.app.io.ripple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode49_GroupAnagram {

    public static void main(String[] args) {
        System.out.println(
                groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})
        );
    }

    // Time complexity O(n*KLogK) where n are strs and k are character in each string.
    // Space complexity O(n)
    public static List<List<String>> groupAnagrams(String[] strs) {
        var anagramMap = new HashMap<String, List<String>>();
        for (var st : strs) {
            var key = getSortedKey(st);
            if (anagramMap.containsKey(key)) {
                anagramMap.get(key).add(st);
            } else {
                anagramMap.put(key, new ArrayList<>(List.of(st)));
            }
        }
        return anagramMap.values().stream().toList();
    }

    private static String getSortedKey(String st) {
        var key = st.toCharArray();
        Arrays.sort(key);
        return new String(key);
    }

    public boolean isAnagram(String w1, String w2) {
        if (w1.length() != w2.length())
            return false;

        var charCount = new int[26];
        for (int i = 0; i < w1.length(); i++) {
            charCount[w1.charAt(i) - 'a']++;
            charCount[w2.charAt(i) - 'a']--;
        }

        for (int count : charCount)
            if (count != 0)
                return false;

        return true;
    }
}
