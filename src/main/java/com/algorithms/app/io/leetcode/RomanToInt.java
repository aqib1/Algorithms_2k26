package com.algorithms.app.io.leetcode;

import java.util.Map;
import java.util.Set;

public class RomanToInt {
    static void main() {
        System.out.println(romanToInt("MCMXCIV"));
    }
    public static int romanToInt(String s) {
        var romainPair = Map.of(
                'I', Set.of('V', 'X'),
                'X', Set.of('L', 'C'),
                'C', Set.of('D', 'M')
        );
        var romanMap = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );
        var val = 0;
        var i = 0;
        while(i < s.length()) {
            var ch = s.charAt(i);
            if(i + 1 < s.length() && romainPair.containsKey(ch)
                    && romainPair.get(ch).contains(s.charAt(i + 1))) {
                val += romanMap.get(s.charAt(i + 1)) - romanMap.get(ch);
                i += 2;
            } else {
                val += romanMap.get(ch);
                i++;
            }
        }
        return val;
    }
}
