package DSA.Trees.Traversal;

import java.util.*;
public class PostOrder {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }


    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;
        postorderTraversalRecursive(root, ans);
        return ans;
    }

    private static void postorderTraversalRecursive(TreeNode node, List<Integer> ans) {
        if (node.left != null) {
            postorderTraversalRecursive(node.left, ans);
        }

        if (node.right != null) {
            postorderTraversalRecursive(node.right, ans);
        }

        ans.add(node.val);
    }

    private static List<Integer> postorderTraversalIterative(TreeNode root) {
        // Left Right Root

        // What is PreOrder
        // Root Left Right
        // Reverse Left & Right
        // Root Right Left
        // Reverse of all -> Left Right Root (which is post order traversal)

        List<Integer> ans = new LinkedList<>();

        if (root == null) return ans;

        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}
