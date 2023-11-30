package DSA.Trees;

import java.util.*;

public class Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeUtil(inorder, postorder);
    }

    private TreeNode buildTreeUtil(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderValToIndexMap = new HashMap<>();
        int N = inorder.length;
        for (int i = 0; i<N; i++) {
            inorderValToIndexMap.put(inorder[i], i);
        }

        return build(inorder, postorder, 0, N-1, 0, N-1, inorderValToIndexMap);
    }

    private TreeNode build(int[] inorder, int[] postorder, int inStart, int inEnd, int poStart, int poEnd, Map<Integer, Integer> inorderValToIndexMap) {
        if (inStart > inStart || poStart > poEnd) {
            return null;
        }

        int val = postorder[poEnd];

        int inorderIndex = inorderValToIndexMap.get(val);
        int numNodes = inorderIndex - inStart;

        TreeNode left = build(inorder, postorder, inStart, inorderIndex - 1,
                poStart, poStart + (numNodes - 1), inorderValToIndexMap);

        TreeNode right = build(inorder, postorder, inorderIndex + 1, inEnd,
                poStart + numNodes, poEnd-1, inorderValToIndexMap);

        TreeNode node = new TreeNode(val, left, right);

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
