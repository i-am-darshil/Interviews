package DSA.Trees;

import java.util.*;
public class BoundaryOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;
        Stack<Integer> stack = new Stack<>();
        ans.add(root.val);
        if (root.left != null) {
            leftBoundaryOfBinaryTree(root.left, ans);
        }

        if (root.left != null || root.right != null) {
            childBoundaryOfBinaryTree(root, ans);
        }

        if (root.right != null) {
            rightBoundaryOfBinaryTree(root.right, stack);
        }

        while (!stack.empty()) {
            ans.add(stack.pop());
        }

        return ans;

    }

    private void leftBoundaryOfBinaryTree(TreeNode node, List<Integer> ans) {

        if (node == null) return;

        if (node.left == null && node.right == null) return;

        ans.add(node.val);

        if (node.left != null) {
            leftBoundaryOfBinaryTree(node.left, ans);
        } else {
            leftBoundaryOfBinaryTree(node.right, ans);
        }

    }

    private void rightBoundaryOfBinaryTree(TreeNode node, Stack<Integer> stack) {
        if (node == null) return;

        if (node.left == null && node.right == null) return;

        stack.push(node.val);

        if (node.right != null) {
            rightBoundaryOfBinaryTree(node.right, stack);
        } else {
            rightBoundaryOfBinaryTree(node.left, stack);
        }
    }

    private void childBoundaryOfBinaryTree(TreeNode node, List<Integer> ans) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            ans.add(node.val);
        }

        childBoundaryOfBinaryTree(node.left, ans);

        childBoundaryOfBinaryTree(node.right, ans);
    }

    private void reverse(List<Integer> l) {
        for (int i=0; i<l.size()/2; i++) {
            int temp = l.get(i);
            l.set(i, l.get(l.size() - 1 - i));
            l.set(l.size() - 1 - i, temp);
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
