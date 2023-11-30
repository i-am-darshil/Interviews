package DSA.Trees;

import java.util.*;

// https://www.codingninjas.com/studio/problems/time-to-burn-tree_630563

public class TimeToBurnTree
{
    public static int timeToBurnTree(BinaryTreeNode<Integer> root, int start)
    {
        // Write your code here.
        return timeToBurnTreeUtil(root, start);
    }

    private static int timeToBurnTreeUtil(BinaryTreeNode<Integer> root, int start) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        generateAdjMap(root, adjMap);

        Map<Integer, Boolean> visited = new HashMap<>();
        for (int key: adjMap.keySet()) {
            visited.put(key, false);
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited.put(start, true);

        int mins = -1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            mins++;
            for (int i = 0; i < levelSize; i++) {
                int node = queue.poll();

                for (int neighbour: adjMap.get(node)) {
                    if (!visited.get(neighbour)) {
                        queue.offer(neighbour);
                        visited.put(neighbour, true);
                    }
                }
            }
        }

        return mins;

    }

    private static void generateAdjMap(BinaryTreeNode<Integer> node, Map<Integer, List<Integer>> adjMap) {
        if (node == null) return;

        if (!adjMap.containsKey(node.data)) {
            adjMap.put(node.data, new ArrayList<>());
        }

        if (node.left != null) {
            adjMap.get(node.data).add(node.left.data);
            adjMap.put(node.left.data, new ArrayList<>());
            adjMap.get(node.left.data).add(node.data);
            generateAdjMap(node.left, adjMap);
        }

        if (node.right != null) {
            adjMap.get(node.data).add(node.right.data);
            adjMap.put(node.right.data, new ArrayList<>());
            adjMap.get(node.right.data).add(node.data);
            generateAdjMap(node.right, adjMap);
        }
    }

    class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }

}
