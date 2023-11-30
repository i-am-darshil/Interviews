package DSA.Trees;

import java.util.*;

public class TopViewofBinaryTree {
    static ArrayList<Integer> topView(Node root)
    {
        // add your code
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (root == null) return ans;

        Map<Integer, Integer> map = new HashMap<>();
        int[] minCol = {0};

        // topView(root, 0, map, minCol);

        bfs(root, map, minCol);

        for (int i = minCol[0]; i < minCol[0] + map.size(); i++) {
            ans.add(map.get(i));
        }

        return ans;

    }

    public static void topView(Node node, int col, Map<Integer, Integer> map, int[] minCol) {
        if (node == null) return;

        minCol[0] = Math.min(minCol[0], col);

        if (!map.containsKey(col)) {
            map.put(col, node.data);
        }

        if (node.left != null) {
            topView(node.left, col-1, map, minCol);
        }

        if (node.right != null) {
            topView(node.right, col+1, map, minCol);
        }
    }

    private static void bfs(Node root, Map<Integer, Integer> map, int[] minCol) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(new TreeNode(root, 0));

        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();

            int col = t.col;
            Node node = t.node;

            minCol[0] = Math.min(minCol[0], col);

            if (!map.containsKey(col)) {
                map.put(col, node.data);
            }

            if (node.left != null) {
                queue.offer(new TreeNode(node.left, col-1));
            }

            if (node.right != null) {
                queue.offer(new TreeNode(node.right, col+1));
            }
        }
    }

    private static class TreeNode {
        Node node;
        int col;

        public TreeNode(Node n, int c) {
            node = n;
            col = c;
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;
    }
}
