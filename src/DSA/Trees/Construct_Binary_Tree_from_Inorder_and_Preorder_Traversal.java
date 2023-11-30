package DSA.Trees;

import java.util.HashMap;
import java.util.Map;

public class Construct_Binary_Tree_from_Inorder_and_Preorder_Traversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeUtil(preorder, inorder);
    }

    public TreeNode buildTreeUtil(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderToIndexMap = new HashMap<>();
        int N = preorder.length;

        for (int i = 0; i<N; i++) {
            inorderToIndexMap.put(inorder[i], i);
        }

        return build(preorder, inorder, 0, inorder.length - 1, 0, preorder.length - 1, inorderToIndexMap);
    }

    private TreeNode build(int[] preorder, int[] inorder, int inStart, int inEnd, int preStart, int preEnd, Map<Integer, Integer> inorderToIndexMap) {
        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }

        int nodeVal = preorder[preStart];
        int nodeInInorderIndex = inorderToIndexMap.get(nodeVal);
        int numNodes = nodeInInorderIndex - inStart;

        TreeNode left = build(preorder, inorder, inStart, nodeInInorderIndex - 1,
                preStart + 1, (preStart + 1) + (numNodes - 1), inorderToIndexMap);

        TreeNode right = build(preorder, inorder, nodeInInorderIndex + 1, inEnd,
                (preStart + 1) + (numNodes - 1) + 1, preEnd, inorderToIndexMap);

        TreeNode node = new TreeNode(nodeVal, left, right);

        return node;
    }

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
