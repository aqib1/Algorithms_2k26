package catoNetworks;

// Time complexity O(N)
// Space complexity:
// balance tree -> O(log N)
// Non balance for example linked list -> O(N)
public class DfsBT {

    static void main() {
        var node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.right = new TreeNode(6);

        var dfsBT = new DfsBT();
        dfsBT.inOrder(node);
        IO.println();
        dfsBT.preOrder(node);
        IO.println();
        dfsBT.postOrder(node);
    }

    // L, N, R
    public void inOrder(TreeNode node) {
       if(node == null)
           return;

       inOrder(node.left);
       IO.print(node.val + " ");
       inOrder(node.right);
    }

    // N, L, R
    public void preOrder(TreeNode node) {
        if(node == null)
            return;

        IO.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // L, R, N
    public void postOrder(TreeNode node) {
        if(node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        IO.print(node.val + " ");
    }
}
