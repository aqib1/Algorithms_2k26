package com.algorithms.app.io.catoNetworks;

import java.util.Map;
import java.util.Stack;

public class ValidParenthesis {
    public boolean isValid(String s) {
        var key = Map.of(
                '}', '{',
                ']', '[',
                ')', '('
        );
       var stack = new Stack<Character>();

       for(char ch: s.toCharArray()) {
           if(key.containsKey(ch)) {
               if(stack.isEmpty() || key.get(ch) != stack.pop())
                    return false;
           } else {
               stack.push(ch);
           }
       }

       return stack.isEmpty();
    }
}
