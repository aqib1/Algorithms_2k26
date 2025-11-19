package com.algorithms.app.io.unknown;

import java.util.HashMap;

public class NumberOfSpecialChar {
    static class SpecialChar {
        private boolean isLower;
        private boolean isUpper;

        public void foundLower() {
            isLower = true;
        }

        public void foundUpper() {
            isUpper = true;
        }

        public boolean isSpecial() {
            return isLower && isUpper;
        }
    }

    public int numberOfSpecialChars(String word) {
        var map = new HashMap<Character, SpecialChar>();
        int count = 0;

        for(char ch: word.toCharArray()) {
            if(map.containsKey(Character.toLowerCase(ch))) {
                var specialChar = map.get(Character.toLowerCase(ch));
                if(Character.isUpperCase(ch)) {
                    specialChar.foundUpper();
                } else {
                    specialChar.foundLower();
                }
            } else {
                var specialChar = new SpecialChar();
                if(Character.isUpperCase(ch)) {
                    specialChar.foundUpper();
                } else {
                    specialChar.foundLower();
                }
                map.put(Character.toLowerCase(ch), specialChar);
            }
        }

        for(var key: map.keySet()) {
            if(map.get(key).isSpecial()) {
                count++;
            }
        }

        return count;
    }

    static void main(String[] args) {
        var specialChar = new NumberOfSpecialChar();
        System.out.println(specialChar.numberOfSpecialChars("aAbcdeEE"));
    }
}
