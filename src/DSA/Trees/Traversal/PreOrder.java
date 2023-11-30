package DSA.Trees.Traversal;

import java.util.*;

public class PreOrder {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    private static List<Integer> preorderTraversalRecursion(TreeNode root) {
        List<Integer> ans = new LinkedList<>();

        if (root == null) return ans;

        preorderTraversalRecursionUtility(root, ans);

        return ans;
    }

    private static void preorderTraversalRecursionUtility(TreeNode node, List<Integer> ans) {
        ans.add(node.val);
        if (node.left != null) {
            preorderTraversalRecursionUtility(node.left, ans);
        }

        if (node.right != null) {
            preorderTraversalRecursionUtility(node.right, ans);
        }
    }

    private static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> ans = new LinkedList<>();

        if (root == null) return ans;

        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push((node.left));
            }
        }

        return ans;
    }
}
