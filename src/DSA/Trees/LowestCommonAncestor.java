package DSA.Trees;

import java.util.*;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorUtil2(root, p, q);
    }

    // Approach 2
    private TreeNode lowestCommonAncestorUtil2(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return null;

        if (node == p || node == q) return node;

        TreeNode left = lowestCommonAncestorUtil2(node.left, p, q);

        TreeNode right = lowestCommonAncestorUtil2(node.right, p, q);

        if (left != null && right != null) return node;

        if (left != null) return left;

        if (right != null) return right;

        return null;
    }

    // Approach 1
    private TreeNode lowestCommonAncestorUtil(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> path1 = new LinkedList<>();
        List<TreeNode> path2 = new LinkedList<>();

        boolean[] found = new boolean[2];

        pathToNode(root, path1, path2, p.val, q.val, found);

        TreeNode lowestAncestor = root;
        int minPathLength = Math.min(path1.size(), path2.size());
        for (int i = 0; i < minPathLength; i++) {
            if (path1.get(i) == path2.get(i)) {
                lowestAncestor = path1.get(i);
            }
        }

        return lowestAncestor;
    }

    private boolean pathToNode(TreeNode node, List<TreeNode> p1, List<TreeNode> p2 , int target1, int target2, boolean[] found) {
        if (node == null) return false;

        if (!found[0]) {
            p1.add(node);
        }

        if (!found[1]) {
            p2.add(node);
        }

        if (node.val == target1) {
            found[0] = true;
        }

        if (node.val == target2) {
            found[1] = true;
        }

        if (found[0] && found[1]) return true;

        boolean foundTargets = false;
        foundTargets = pathToNode(node.left, p1, p2, target1, target2, found);
        if (foundTargets) return true;

        foundTargets = pathToNode(node.right, p1, p2, target1, target2, found);
        if (foundTargets) return true;

        if (!found[0]) {
            p1.remove(p1.size() - 1);
        }

        if (!found[1]) {
            p2.remove(p2.size() - 1);
        }

        return false;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
