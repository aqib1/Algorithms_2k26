package com.algorithms.app.io.ripple.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagram {

    // Time complexity O(N * KLogK) where k is the length of a key
    // Space complexity O(NK)
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0)
            return List.of();
        var anagramMap = new HashMap<String, List<String>>();
        for(var str: strs) {
            var key = getSortedKey(str);
            if(anagramMap.containsKey(key)) {
                anagramMap.get(key).add(str);
            } else {
                anagramMap.put(key, new ArrayList<>(List.of(str)));
            }
        }
        return anagramMap.values().stream().toList();
    }

    private String getSortedKey(String str) {
        var ch = str.toCharArray();
        Arrays.sort(ch);
        return String.valueOf(ch);
    }
}
