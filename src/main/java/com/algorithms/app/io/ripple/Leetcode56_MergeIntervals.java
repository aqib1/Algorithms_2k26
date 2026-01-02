package com.algorithms.app.io.ripple;

import java.util.*;

public class Leetcode56_MergeIntervals {
    static class Node {
        private Node left;
        private Node right;
        private int[] interval;

        public Node(int[] interval) {
            this.interval = interval;
        }

        public Node(Node left, Node right, int[] interval) {
            this(interval);
            this.left = left;
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int[] getInterval() {
            return interval;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setInterval(int[] interval) {
            this.interval = interval;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", interval=" + Arrays.toString(interval) +
                    '}';
        }
    }

    static class BIntervalTree {
        private Node root;

        public void insert(int[] interval) {
            if(root == null) {
                root = new Node(interval);
            } else {
                addChildren(root, interval);
            }
        }

        // [1, 3], [0, 6], [-5, -1], [7, 9]
        public void addChildren(Node root, int[] interval) {
            if(interval[1] < root.getInterval()[0]) {
                if(root.left == null) {
                    root.left = new Node(interval);
                } else {
                    addChildren(root.left, interval);
                }
            } else if(interval[0] > root.getInterval()[1]) {
                if(root.right == null) {
                    root.right = new Node(interval);
                } else {
                    addChildren(root.right, interval);
                }
            } else {
                var rootInterval = root.getInterval();
                rootInterval[0] = Math.min(rootInterval[0], interval[0]);
                rootInterval[1] = Math.max(rootInterval[1], interval[1]);
            }
        }

        public List<int[]> levelOrder() {
            var levelOrder = new ArrayList<int[]>();
            inOrder(root, levelOrder);
            return levelOrder;
        }

        public void inOrder(Node root, List<int[]> data) {
            if(root == null)
                return;

            inOrder(root.left, data);
            data.add(root.interval);
            inOrder(root.right, data);
        }
    }


    // Time complexity ON(LogN)
    // Space complexity O(n)
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        var intervalTree = new BIntervalTree();
        for(var interval: intervals) {
            intervalTree.insert(interval);
        }
        var result = intervalTree.levelOrder();
        return result.toArray(new int[result.size()][]);
    }
}
