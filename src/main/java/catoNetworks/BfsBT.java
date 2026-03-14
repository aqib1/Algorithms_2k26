package catoNetworks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BfsBT {

    static void main() {
        var node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);
        IO.println(inOrderWithRecur(node));
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }


    // Time complexity O(n)
    // Space complexity O(n)
    public static List<List<Integer>> inOrderWithRecur(TreeNode treeNode) {
        if(treeNode == null)
            return new ArrayList<>();
        List<List<Integer>> response = new ArrayList<>();
        levelOrder(treeNode, 0, response);
        return response;
    }

    public static void levelOrder(TreeNode treeNode, int level, List<List<Integer>> response) {
        if(treeNode == null)
            return;
        if (level == response.size())
            response.add(new ArrayList<>());

        response.get(level).add(treeNode.val);
        levelOrder(treeNode.left, level + 1, response);
        levelOrder(treeNode.right, level + 1, response);
    }


    // Time complexity O(n)
    // Space complexity O(n)
    public static List<List<Integer>> inOrder(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> response = new ArrayList<>();
        var currentLevel = 0;
        q.offer(root);
        while(!q.isEmpty()) {
            var size = q.size();
            response.add(new ArrayList<>());
            for(int i = 0; i < size; i++) {
               var curr = q.poll();
               if(curr != null) {
                   response.get(currentLevel).add(curr.val);

                   if(curr.left != null)
                       q.offer(curr.left);
                   if(curr.right != null)
                       q.offer(curr.right);
               }
            }
            currentLevel++;
        }
        return response;
    }
}
