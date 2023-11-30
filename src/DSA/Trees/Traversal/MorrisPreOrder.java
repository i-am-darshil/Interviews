package DSA.Trees.Traversal;

import java.util.*;

public class MorrisPreOrder {
    private void MorrisPreOrderTraversal(TreeNode node, List<Integer> ans) {
        TreeNode curr = node;

        while (curr != null) {
            if (curr.left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNodeOfLeftChild = curr.left;
                while (rightMostNodeOfLeftChild.right != null
                        && rightMostNodeOfLeftChild.right != curr) {
                    rightMostNodeOfLeftChild = rightMostNodeOfLeftChild.right;
                }

                if (rightMostNodeOfLeftChild.right == curr) {
                    rightMostNodeOfLeftChild.right = null;
                    curr = curr.right;
                } else {
                    ans.add(curr.val);
                    rightMostNodeOfLeftChild.right = curr;
                    curr = curr.left;
                }
            }
        }
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
