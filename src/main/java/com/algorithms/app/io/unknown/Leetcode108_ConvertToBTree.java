package com.algorithms.app.io.unknown;

public class Leetcode108_ConvertToBTree {

    public static void main(String[] args) {
//        System.out.println(
//                arrayToBinaryTree(new int[]{2, 3, 1, 2, 1})
//        );

        System.out.println(
                arrayToBST(new int[]{-10, -3, 0, 5, 9})
        );
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }

        private void preOrder(TreeNode node, StringBuilder sb) {
            if (node == null)
                return;

            sb.append(node.val).append(" ");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }

        @Override
        public String toString() {
            var builder = new StringBuilder();
            builder.append("[ ");
            preOrder(this, builder);
            builder.append("]");
            return builder.toString();
        }
    }


    public static TreeNode arrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        return bst(nums, 0, nums.length - 1);
    }

    private static TreeNode bst(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = low + (high - low) / 2;
        var node = new TreeNode(nums[mid]);

        node.left = bst(nums, low, mid - 1);
        node.right = bst(nums, mid + 1, high);

        return node;
    }


    private static TreeNode root;

    public static TreeNode arrayToBinaryTree(int[] nums) {
        for (int num : nums) {
            construct(num);
        }
        return root;
    }

    private static void construct(int num) {
        if (root == null) {
            root = new TreeNode(num);
        } else {
            child(num, root);
        }
    }

    private static void child(int num, TreeNode root) {
        if (num > root.val) {
            if (root.right == null)
                root.right = new TreeNode(num);
            else
                child(num, root.right);
        } else {
            if (root.left == null)
                root.left = new TreeNode(num);
            else
                child(num, root.left);
        }
    }
}
