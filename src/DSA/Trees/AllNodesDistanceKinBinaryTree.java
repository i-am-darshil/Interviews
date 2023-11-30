package DSA.Trees;

import java.util.*;

public class AllNodesDistanceKinBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // return distanceKUtil(root, target, k);

        return distanceKUtilGraph(root, target, k);
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }


    private List<Integer> distanceKUtilGraph(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        List<Integer> ans = new LinkedList<>();

        generateAdjList(root, adjMap);

        if (k > adjMap.size()) return ans;

        Map<Integer, Boolean> visited = new HashMap<>();
        for (int key: adjMap.keySet()) {
            visited.put(key, false);
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(target.val);
        visited.put(target.val, true);

        int dist = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i<levelSize; i++) {
                int node = queue.poll();

                if (dist == k) {
                    ans.add(node);
                    continue;
                }

                for (int neighbour: adjMap.get(node)) {
                    if (!visited.get(neighbour)) {
                        queue.offer(neighbour);
                        visited.put(neighbour, true);
                    }
                }

            }

            dist++;
        }

        return ans;
    }

    private void generateAdjList(TreeNode node, Map<Integer, List<Integer>> adjMap) {
        if (node == null) return;

        if (!adjMap.containsKey(node.val)) {
            adjMap.put(node.val, new ArrayList<>());
        }

        if (node.left != null) {
            adjMap.get(node.val).add(node.left.val);
            adjMap.put(node.left.val, new ArrayList<>());
            adjMap.get(node.left.val).add(node.val);
            generateAdjList(node.left, adjMap);
        }

        if (node.right != null) {
            adjMap.get(node.val).add(node.right.val);
            adjMap.put(node.right.val, new ArrayList<>());
            adjMap.get(node.right.val).add(node.val);
            generateAdjList(node.right, adjMap);
        }
    }



    // private List<Integer> distanceKUtil(TreeNode root, TreeNode target, int k) {
    //     List<Integer> ans = new LinkedList<>();
    //     dfs(root, target, k, k, ans);
    //     return ans;
    // }

    // private int dfs(TreeNode node, TreeNode target, int k, int rem, List<Integer> ans) {
    //     if (node == null) {
    //         return k;
    //     }

    //     if (rem == 0) {
    //         ans.add(node.val);
    //         return k;
    //     }

    //     int remForNextHop = rem;

    //     if (node == target || rem < k) {
    //         remForNextHop = rem-1;
    //     }

    //     int remFromLeft = k;

    //     if (node.left != null) {
    //         remFromLeft = dfs(node.left, target, k, remForNextHop, ans);
    //     }

    //     if (remFromLeft == 0) {
    //         ans.add(node.val);
    //         return k;
    //     }

    //     if (remFromLeft < k) {
    //         remForNextHop = nextRemFromLeft - 1;
    //     }

    //     int nextRemFromRight = k;
    //     if (node.right != null) {
    //         nextRemFromRight = dfs(node.right, target, k, nextRem, ans);
    //     }

    //     if (nextRemFromRight == 0) {
    //         ans.add(node.val);
    //         return k;
    //     }

    //     if (nextRemFromRight < k) {
    //         remForNextHop = nextRemFromLeft - 1;
    //         dfs(node.left, target, k, remForNextHop, ans);
    //     }

    //     return nextRem;

    // }
}
