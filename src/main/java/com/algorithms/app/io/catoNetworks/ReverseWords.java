package com.algorithms.app.io.catoNetworks;

import java.util.Stack;
import java.util.stream.IntStream;

public class ReverseWords {
    void main() {
        IO.println(reverse("dont waste your time on cato"));
    }
    public String reverse(String w) {
        w = w.trim();
        var stack = new Stack<String>();
        var split = w.split(" ");
        IntStream.range(0, split.length)
                .filter(x -> !split[x].isBlank())
                .forEach(x -> stack.push(split[x].trim()));
        var builder = new StringBuilder();
        while (!stack.isEmpty()) builder.append(stack.pop()).append(" ");
        return builder.toString().trim();
    }
}
