package DSA.Trees.Traversal;

import java.util.*;

public class MorrisInorder {
    private void morrisInorderTraversalIterative(TreeNode node, List<Integer> ans) {
        TreeNode curr = node;

        while (curr != null) {
            if (curr.left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNodeOfLeftChild = curr.left;
                while (
                        rightMostNodeOfLeftChild.right != null &&
                                rightMostNodeOfLeftChild.right != curr
                ) {
                    rightMostNodeOfLeftChild = rightMostNodeOfLeftChild.right;
                }

                if (rightMostNodeOfLeftChild.right == curr) {
                    rightMostNodeOfLeftChild.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                } else {
                    rightMostNodeOfLeftChild.right = curr;
                    curr = curr.left;
                }
            }
        }
    }

    private void morrisInorderTraversal(TreeNode node, List<Integer> ans) {

        if (node == null) return;
        if (node.left == null) {
            ans.add(node.val);
            morrisInorderTraversal(node.right, ans);
            return;
        }

        TreeNode rightMostNodeOfLeftChild = findRightMost(node.left, node);
        if (rightMostNodeOfLeftChild.right == node) {
            ans.add(node.val);
            rightMostNodeOfLeftChild.right = null;
            morrisInorderTraversal(node.right, ans);
        } else {
            rightMostNodeOfLeftChild.right = node;
            morrisInorderTraversal(node.left, ans);
        }

    }

    private TreeNode findRightMost(TreeNode node, TreeNode rightMostForNode) {
        if (node.right == rightMostForNode || node.right == null) {
            return node;
        } else {
            return findRightMost(node.right, rightMostForNode);
        }
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
