package DSA.Trees;

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/

public class FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        // flattenRecursively(root);
        flattenIteratively(root);
    }

    private void flattenRecursively(TreeNode node) {
        if (node == null) return;

        if (node.left == null) {
            flattenRecursively(node.right);
        } else {
            TreeNode rightMostNodeOfLeftChild = getRightMostNode(node.left);
            rightMostNodeOfLeftChild.right = node.right;
            node.right = node.left;
            node.left = null;
            flattenRecursively(node.right);
        }
    }

    private TreeNode getRightMostNode(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node;
        }
        if (node.right != null) {
            return getRightMostNode(node.right);
        }
        return getRightMostNode(node.left);
    }

    private void flattenIteratively(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                curr = curr.right;
            } else {
                TreeNode rightMostNodeOfLeftChild = curr.left;
                while (rightMostNodeOfLeftChild.left !=null
                        || rightMostNodeOfLeftChild.right != null) {
                    if (rightMostNodeOfLeftChild.right != null) {
                        rightMostNodeOfLeftChild = rightMostNodeOfLeftChild.right;
                    } else {
                        rightMostNodeOfLeftChild = rightMostNodeOfLeftChild.left;
                    }
                }

                rightMostNodeOfLeftChild.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
                curr = curr.right;
            }
        }
    }

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
