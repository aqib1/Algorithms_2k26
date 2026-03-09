package catoNetworks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BfsBT {

    static void main() {
        var node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);
        IO.println(inOrder(node));
    }


    public static class Node {
        int val;
        Node left;
        Node right;
        Node(int val) {
            this.val = val;
        }
    }

    // Time complexity O(n)
    // Space complexity O(n)
    public static List<List<Integer>> inOrder(Node root) {
        if(root == null)
            return new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
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
