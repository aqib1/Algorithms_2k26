package com.algorithms.app.io.ripple;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

public class Leetcode224_BasicCalculator {
    public static void main(String[] args) {
        var leet = new Leetcode224_BasicCalculator();
        System.out.println(leet.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    private static final Set<Character> OPERATOR = Set.of('^', '*', '/', '+', '-', '(', ')');
    private boolean isDigit(char ch) {
        return !OPERATOR.contains(ch);
    }

    private int precedence(char ch) {
        return switch(ch) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }

    public int calculate(String infix) {
        infix = infix.trim();
        infix = infix.replaceAll("\\s", "");
        try {
            return Integer.parseInt(infix);
        } catch(NumberFormatException ex) {
            // ignore
        }
        return postfixCalculate(infixToPostfix(infix));
    }

    private int postfixCalculate(String[] postfix) {
        if(postfix.length == 0)
            return 0;
        var stack = new Stack<Node>();

        for(var p: postfix) {
            var node = Node.fromString(p);
            if(node instanceof OperatorNode op) {
                op.setFirst(stack.pop());
                op.setSecond(stack.pop());
                stack.push(op);
            } else {
                stack.push(node);
            }
        }

        return stack.pop().evaluate();
    }

    private String[] infixToPostfix(String infix) {
        var postfix = new ArrayList<String>();
        var opStack = new Stack<Character>();
        var digit = "";
        for(int i = 0; i < infix.length(); i++) {
            var current = infix.charAt(i);
            if(isDigit(current)) {
                digit = digit.concat(String.valueOf(current));
            } else {
                if(!digit.isEmpty()) {
                    postfix.add(digit);
                }
                digit = "";
                if(current == '-' && (i == 0 || (!isDigit(infix.charAt(i - 1)) && infix.charAt(i - 1) != ')') || infix.charAt(i - 1) == '(')) {
                    postfix.add("0");
                }

                while(!opStack.isEmpty() && precedence(current) != -1 && precedence(current) <= precedence(opStack.peek())) {
                    postfix.add(String.valueOf(opStack.pop()));
                }
                if(current == ')') {
                    char currOpt;
                    while(!opStack.isEmpty() && (currOpt = opStack.pop()) != '(') {
                        postfix.add(String.valueOf(currOpt));
                    }
                } else {
                    opStack.push(current);
                }
            }
        }

        if(!digit.isEmpty()) {
            postfix.add(digit);
        }
        while(!opStack.isEmpty()) {
            postfix.add(String.valueOf(opStack.pop()));
        }

        return postfix.toArray(new String[0]);
    }


    interface Node {
        int evaluate();
        static Node fromString(String node) {
            return switch(node) {
                case "*" -> new MulNode();
                case "/" -> new DivNode();
                case "+" -> new AddNode();
                case "-" -> new SubNode();
                default -> new NumericNode(node);
            };

        }
    }

    static abstract class OperatorNode implements Node {
        private Node first;
        private Node second;

        public Node getFirst() {
            return first;
        }

        public void setFirst(Node first) {
            this.first = first;
        }

        public Node getSecond() {
            return second;
        }

        public void setSecond(Node second) {
            this.second = second;
        }
    }

    private record NumericNode(String number) implements Node {
        @Override
            public int evaluate() {
                return Integer.parseInt(number);
            }
        }

    private static class AddNode extends OperatorNode {
        @Override
        public int evaluate() {
            return getSecond().evaluate() + getFirst().evaluate();
        }
    }

    private static class SubNode extends OperatorNode {
        @Override
        public int evaluate() {
            return getSecond().evaluate() - getFirst().evaluate();
        }
    }

    private static class MulNode extends OperatorNode {
        @Override
        public int evaluate() {
            return getSecond().evaluate() * getFirst().evaluate();
        }
    }

    private static class DivNode extends OperatorNode {
        @Override
        public int evaluate() {
            return getSecond().evaluate() / getFirst().evaluate();
        }
    }
}
