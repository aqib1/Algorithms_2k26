package com.algorithms.app.io.leetcode;

public class IsRobotBounded {
    // Time complexity O(n) and space O(1)
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        var d = 'N';
        for(var i: instructions.toCharArray()) {
            if(i == 'G') {
                if(d == 'N') {
                    y++;
                } else if(d == 'E') {
                    x++;
                } else if(d == 'S') {
                    y--;
                } else {
                    x--;
                }
            } else if(i == 'R') {
                if(d == 'N') {
                    d = 'E';
                } else if(d == 'E') {
                    d = 'S';
                } else if(d == 'S') {
                    d = 'W';
                } else {
                    d = 'N';
                }
            } else if (i == 'L') {
                if(d == 'N') {
                    d = 'W';
                } else if(d == 'W') {
                    d = 'S';
                } else if(d == 'S') {
                    d = 'E';
                } else {
                    d = 'N';
                }
            }
        }

        return (x == 0 && y == 0) || d != 'N';
    }
}
