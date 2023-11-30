package DSA.Trees.Traversal;

import java.util.*;

public class Inorder {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    private static List<Integer> inorderRecursion(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;

        inorderRecursionUtility(root, ans);

        return ans;
    }

    private static void inorderRecursionUtility(TreeNode node, List<Integer> ans) {
        if (node.left != null) {
            inorderRecursionUtility(node.left, ans);
        }
        ans.add(node.val);
        if (node.right != null) {
            inorderRecursionUtility(node.right, ans);
        }
    }

    private static List<Integer> inorderIterative(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;

        Stack<TreeNode> stack = new Stack();

        TreeNode cur = root;

        while (true) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            if (stack.empty()) {
                break;
            }

            TreeNode node = stack.pop();
            ans.add(node.val);
            cur = node.right;
        }

        return ans;
    }
}
