package DSA.Trees;

public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        maxPathSum(root, maxSum);

        return maxSum[0];
    }

    private int maxPathSum(TreeNode node, int[] maxSum) {
        int sumL = 0;
        int sumR = 0;
        int maxLR = 0;

        if (node.left != null) {
            sumL = maxPathSum(node.left, maxSum);
            maxLR = Math.max(maxLR, sumL);
        }

        if (node.right != null) {
            sumR = maxPathSum(node.right, maxSum);
            maxLR = Math.max(maxLR, sumR);
        }

        int localMaxSum = 0;

        if (sumL > 0) {
            localMaxSum += sumL;
        }

        if (sumR > 0) {
            localMaxSum += sumR;
        }

        localMaxSum += node.val;

        maxSum[0] = Math.max(localMaxSum, maxSum[0]);

        return node.val + maxLR;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
