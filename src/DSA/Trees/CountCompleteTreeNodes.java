package DSA.Trees;

// https://leetcode.com/problems/count-complete-tree-nodes/description/

public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        return countNodesUtil2(root);
    }

    private int countNodesUtil2(TreeNode root) {
        if (root == null) return 0;
        int maxH = findDepth(root) - 1;

        // index from start
        int[] lastLevelRightMostProps = new int[1];

        int numNodes = 0;

        if (maxH >= 1) {
            numNodes = (int) Math.pow(2, maxH) - 1;
        } else {
            numNodes = 1;
            return numNodes;
        }

        findlastLevelRightMostNode(root, lastLevelRightMostProps, 0);

        return numNodes + lastLevelRightMostProps[0] + 1;

    }

    // The idea is that for each node compare the height of the left and right subtree. In this way you eliminate half of the nodes in every step.
    // Complexity is O(lgn*lgn), as you need lg n steps to walk down the tree, while getting the height of the subtree takes another lg n.

    // For complete binary tree
    // Index of left child from start of level = 2 * (index of parent)
    // Index of right child from start of level = 2 * (index of parent) + 1
    // Intuition is that for parent of index i (starting from 0), there will be i nodes before it at that level.
    // Each node before current parent will have 2 children (since complete binary tree).
    // So for next level, 2 * i nodes would be filled before filling the nodes of the parent index (i) under consideration.

    private static void findlastLevelRightMostNode(TreeNode node, int[] lastLevelRightMostProps, int index) {

        lastLevelRightMostProps[0] = index;

        int leftDepth = 0;
        int rightDepth = 0;

        if (node.left != null) {
            leftDepth = findDepth(node.left);
        }

        if (node.right != null) {
            rightDepth = findDepth(node.right);
        }

        if (leftDepth == 0 && rightDepth == 0) return;

        if (leftDepth <= rightDepth) {
            findlastLevelRightMostNode(node.right, lastLevelRightMostProps, index*2+1);
        } else {
            findlastLevelRightMostNode(node.left, lastLevelRightMostProps, index*2);
        }

    }

    private static int findDepth(TreeNode node) {
        if (node == null) return 0;

        return 1 + Math.max(findDepth(node.left), findDepth(node.right));
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
