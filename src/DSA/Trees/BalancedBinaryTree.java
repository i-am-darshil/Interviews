package DSA.Trees;

// https://leetcode.com/problems/balanced-binary-tree/description/

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (isBalancedUtil(root) == -1) return false;
        return true;
    }

    private int isBalancedUtil (TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftH = isBalancedUtil(node.left);
        if (leftH == -1) return -1;

        int rightH = isBalancedUtil(node.right);
        if (rightH == -1) return -1;

        if (Math.abs(leftH - rightH) > 1) return -1;

        return 1 + Math.max(leftH, rightH);
    }

    private class TreeNode<V> {
        V val;
        TreeNode left;
        TreeNode right;
    }
}
