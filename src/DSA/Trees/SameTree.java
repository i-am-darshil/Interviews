package DSA.Trees;

// https://leetcode.com/problems/same-tree/description/

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeUtil(p, q);
    }

    private boolean isSameTreeUtil(TreeNode p, TreeNode q) {

        if (p == null) {
            if (q == null) return true;
            else return false;
        }

        if (q == null) return false;

        boolean isValSame = p.val == q.val;
        if (!isValSame) return false;

        boolean isLeftSame = isSameTreeUtil(p.left, q.left);
        if (!isLeftSame) return false;

        boolean isRightSame = isSameTreeUtil(p.right, q.right);
        if (!isRightSame) return false;

        return true;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
