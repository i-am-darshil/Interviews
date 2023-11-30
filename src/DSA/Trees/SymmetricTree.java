package DSA.Trees;

// https://leetcode.com/problems/symmetric-tree/description/

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {

        if (root == null) return true;
        return isSymmetricUtil(root.left, root.right);
    }

    private boolean isSymmetricUtil(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return isSymmetricUtil(t1.left, t2.right) && isSymmetricUtil(t1.right, t2.left);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
