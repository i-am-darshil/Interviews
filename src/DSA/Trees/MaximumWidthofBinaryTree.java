package DSA.Trees;

import java.util.*;

// https://leetcode.com/problems/maximum-width-of-binary-tree/description/

public class MaximumWidthofBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        return widthOfBinaryTreeUtil(root);
    }

    private int widthOfBinaryTreeUtil(TreeNode root) {

        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(root, 0));

        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int numNodesInLevel = queue.size();

            int minIndexAtLevel = 0;
            int maxIndexAtLevel = 0;

            for (int i = 0; i<numNodesInLevel; i++) {
                Node node = queue.poll();

                int index = node.index;
                TreeNode n = node.node;

                if (i == 0) minIndexAtLevel = index;
                if (i == numNodesInLevel-1) maxIndexAtLevel = index;
                // minIndexAtLevel = Math.min(minIndexAtLevel, index);
                // maxIndexAtLevel = Math.max(maxIndexAtLevel, index);

                if (n.left != null) {
                    queue.offer(new Node(n.left, index*2));
                }
                if (n.right != null) {
                    queue.offer(new Node(n.right, index*2 + 1));
                }
            }

            maxWidth = Math.max(maxIndexAtLevel - minIndexAtLevel + 1, maxWidth);
            // maxWidth = Math.max(maxIndexAtLevel - 0 + 1, maxWidth);
        }

        return maxWidth;

    }

    private class Node {
        TreeNode node;
        int index;
        public Node(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
