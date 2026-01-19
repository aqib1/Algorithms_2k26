package com.algorithms.app.io.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JudgeCircle {
    static void main() {
        System.out.println(judgeCircleSlowApproach("UURRDRDLLL"));
    }
    public static boolean judgeCircleSlowApproach(String moves) {
        var moveCount = new HashMap<Character, Integer>();
        var pairs = Map.of(
                'L', 'R',
                'U', 'D',
                'R', 'L',
                'D', 'U'
        );

        for(char ch: moves.toCharArray()) {
            moveCount.put(ch, moveCount.getOrDefault(ch, 0) + 1);
        }

        for(char key: moveCount.keySet()) {
            if(!Objects.equals(moveCount.get(key), moveCount.get(pairs.get(key))))
                return false;
        }

        return true;
    }
}
