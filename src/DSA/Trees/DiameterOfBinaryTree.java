package DSA.Trees;
// https://leetcode.com/problems/diameter-of-binary-tree/description/
public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        // return diameterOfBinaryTreeUtil(root).diameter;

        int[] diam = new int[1];
        diameterOfBinaryTreeUtil2(root, diam);
        return diam[0];
    }

    private DepthAndDiameter diameterOfBinaryTreeUtil(TreeNode root) {
        if (root == null) return new DepthAndDiameter(0, 0);

        DepthAndDiameter leftD = diameterOfBinaryTreeUtil(root.left);
        DepthAndDiameter rightD = diameterOfBinaryTreeUtil(root.right);

        int maxDepth = Math.max(leftD.depth, rightD.depth);
        int maxDiameter = Math.max(leftD.diameter, rightD.diameter);

        if (leftD.depth + rightD.depth > maxDiameter) {
            return new DepthAndDiameter(1 + maxDepth, leftD.depth + rightD.depth);
        } else {
            return new DepthAndDiameter(1 + maxDepth, maxDiameter);
        }
    }

    private class DepthAndDiameter{
        int depth;
        int diameter;

        public DepthAndDiameter(int dp, int d) {
            depth = dp;
            diameter = d;
        }
    }

    private int diameterOfBinaryTreeUtil2(TreeNode node, int[] diameter) {
        if (node.left == null && node.right == null) return 0;

        int leftH = 0;
        int rightH = 0;
        int diam = 0;

        if (node.left != null) {
            leftH = diameterOfBinaryTreeUtil2(node.left, diameter);
            diam += leftH + 1;
        }

        if (node.right != null) {
            rightH = diameterOfBinaryTreeUtil2(node.right, diameter);
            diam += rightH + 1;
        }

        diameter[0] = Math.max(diameter[0], diam);

        return 1 + Math.max(leftH, rightH);
    }

    private class TreeNode<V> {
        V val;
        TreeNode left;
        TreeNode right;
    }
}
