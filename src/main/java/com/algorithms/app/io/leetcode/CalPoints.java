package com.algorithms.app.io.leetcode;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Stack;

public class CalPoints {
    static void main() {
        var calPoints = new CalPoints();
        System.out.println(
                calPoints.calPoints(
                        new String[] {
                                "5","-2","4","C","D","9","+","+"
                        }
                )
        );
    }
    public int calPoints(String[] operations) {
        var stack = new Stack<Integer>();
        for (var op : operations) {
            if ("C".equals(op) && !stack.isEmpty()) {
                stack.pop();
            } else if ("D".equals(op) && !stack.isEmpty()) {
                stack.push(stack.peek() * 2);
            } else if ("+".equals(op) && stack.size() >= 2) {
                var first = stack.pop();
                var second = stack.pop();
                stack.push(second);
                stack.push(first);
                stack.push(first + second);
            } else {
                stack.push(Integer.parseInt(op));
            }
        }

        var sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
