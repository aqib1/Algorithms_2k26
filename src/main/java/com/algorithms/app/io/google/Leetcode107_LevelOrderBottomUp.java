package com.algorithms.app.io.google;

import com.algorithms.app.io.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode107_LevelOrderBottomUp {

    public static void main(String[] args) {
        var node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.left.left.left = new TreeNode(6);
        node.left.left.left.left = new TreeNode(7);

        levelOrderBottom(node);
    }

    // Time complexity O(n)
    // Space complexity O(n)
    public static List<List<Integer>> levelOrderBottom(
            TreeNode root
    ) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        levelOrderStorage(root, levelOrder, 0);
        return levelOrder;
    }

    private static void levelOrderStorage(
            TreeNode root,
            List<List<Integer>> levelOrder,
            int level
    ) {
        if (root == null) return;
        if(level >= levelOrder.size()) levelOrder.addFirst(new ArrayList<>());

        levelOrder.get(levelOrder.size() - 1 - level).add(root.val);

        levelOrderStorage(root.left, levelOrder, level + 1);
        levelOrderStorage(root.right, levelOrder, level + 1);
    }
}
