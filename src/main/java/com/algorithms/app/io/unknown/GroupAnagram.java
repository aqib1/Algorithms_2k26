package com.algorithms.app.io.unknown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagram {
    // Time complexity O(n*mlogm)
    // Space O(n*m)
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0)
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
        var data = str.toCharArray();
        Arrays.sort(data);
        return String.valueOf(data);
    }
}
