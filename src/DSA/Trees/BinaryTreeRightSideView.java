package DSA.Trees;

// https://leetcode.com/problems/binary-tree-right-side-view/

import java.util.*;
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new LinkedList<>();

        // if (root == null) return ans;

        // Map<Integer, Integer> map = new HashMap<>();

        // bfs(root, map);

        // for (int i=0; i<map.size(); i++) {
        //     ans.add(map.get(i));
        // }

        dfs(root, ans, 0);

        return ans;

    }

    private void bfs(TreeNode root, Map<Integer, Integer> map) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int num = queue.size();

            for (int i=0; i<num; i++) {
                TreeNode node = queue.poll();
                map.put(level, node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            level++;
        }

    }

    private void dfs(TreeNode node, List<Integer> ans, int depth) {
        if (node == null) return;

        if (depth == ans.size()) {
            ans.add(node.val);
        }

        dfs(node.right, ans, depth+1);

        dfs(node.left, ans, depth + 1);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
