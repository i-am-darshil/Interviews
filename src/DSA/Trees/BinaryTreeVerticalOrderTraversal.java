package DSA.Trees;

import java.util.*;
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] minCol = new int[1];
        minCol[0] = 0;

        // verticalOrder(root, 0, map, minCol);

        bfs(root, map, minCol);

        for (int i = minCol[0]; i < minCol[0] + map.size(); i++) {
            ans.add(map.get(i));
        }

        return ans;
    }

    // DFS should not be used :<(
    private void verticalOrder(TreeNode node, int col, Map<Integer, List<Integer>> map, int[] minCol) {
        if (node == null) return;

        minCol[0] = Math.min(minCol[0], col);
        map.computeIfAbsent(col, k -> {
            return new ArrayList<>();
        }).add(node.val);

        if (node.left != null) {
            verticalOrder(node.left, col-1, map, minCol);
        }

        if (node.right != null) {
            verticalOrder(node.right, col+1, map, minCol);
        }
    }

    private void bfs(TreeNode root, Map<Integer, List<Integer>> map, int[] minCol) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(root, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int col = node.col;
            TreeNode t = node.node;

            minCol[0] = Math.min(minCol[0], col);

            map.computeIfAbsent(col, k -> {
                return new ArrayList<>();
            }).add(t.val);

            if (t.left != null) {
                queue.offer(new Node(t.left, col-1));
            }

            if (t.right != null) {
                queue.offer(new Node(t.right, col+1));
            }

        }


    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    private class Node {
        TreeNode node;
        int col;

        public Node(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }
}
