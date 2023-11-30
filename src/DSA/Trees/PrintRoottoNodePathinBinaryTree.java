package DSA.Trees;

// https://www.interviewbit.com/problems/path-to-given-node/
import java.util.*;
public class PrintRoottoNodePathinBinaryTree {
    public ArrayList<Integer> solve(TreeNode A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) return ans;
        solveUtil(A, B, ans);
        return ans;

    }

    public boolean solveUtil(TreeNode node, int val, ArrayList<Integer> ans) {
        if (node == null) return false;

        ans.add(val);

        if (node.val == val) return true;

        boolean found;
        found = solveUtil(node.left, val, ans);
        if (found == true) return true;

        found = solveUtil(node.right, val, ans);
        if (found == true) return true;

        ans.remove(ans.size()-1);
        return false;

    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
