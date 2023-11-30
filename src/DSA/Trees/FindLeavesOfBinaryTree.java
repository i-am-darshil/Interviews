package DSA.Trees;

// https://leetcode.com/problems/find-leaves-of-binary-tree/description/
import java.util.*;
public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        // return findLeavesUtil(root);
        // return findLeavesApproach2(root);
        return findLeavesApproach3(root);

    }


    private List<List<Integer>> findLeavesApproach2(TreeNode root) {
        List<List<Integer>> valAndHeight = new ArrayList<>();
        List<List<Integer>> ans = new LinkedList<>();
        mapHeight(root, valAndHeight);

        Collections.sort(valAndHeight, Comparator.comparing(item -> item.get(1)));

        int i = 0;
        while (i < valAndHeight.size()) {
            List<Integer> nodesAtSameLevel = new LinkedList<>();
            nodesAtSameLevel.add(valAndHeight.get(i).get(0));
            i++;
            while (i < valAndHeight.size() && valAndHeight.get(i).get(1) == valAndHeight.get(i-1).get(1) ) {
                nodesAtSameLevel.add(valAndHeight.get(i).get(0));
                i++;
            }
            ans.add(nodesAtSameLevel);
        }

        return ans;
    }

    private int mapHeight(TreeNode node, List<List<Integer>> valAndHeight) {
        if (node == null) return 0;

        int maxH = 0;
        int leftH = mapHeight(node.left, valAndHeight);
        int rightH = mapHeight(node.right, valAndHeight);
        maxH = 1 + Math.max(leftH, rightH);

        List<Integer> nodeProps = new ArrayList<>();
        nodeProps.add(node.val);
        nodeProps.add(maxH);
        valAndHeight.add(nodeProps);

        return maxH;
    }

    private List<List<Integer>> findLeavesApproach3(TreeNode root) {
        Map<Integer, List<Integer>> heightToValMap = new HashMap<>();

        List<List<Integer>> ans = new LinkedList<>();
        int maxH = mapHeightApproach3(root, heightToValMap);

        for (int i = 1; i <= maxH; i++) {
            ans.add(heightToValMap.get(i));
        }

        return ans;
    }

    private int mapHeightApproach3(TreeNode node, Map<Integer, List<Integer>> heightToValMap) {
        if (node == null) return 0;

        int maxH = 0;
        int leftH = mapHeightApproach3(node.left, heightToValMap);
        int rightH = mapHeightApproach3(node.right, heightToValMap);
        maxH = 1 + Math.max(leftH, rightH);
        if (heightToValMap.containsKey(maxH)) {
            heightToValMap.get(maxH).add(node.val);
        } else {
            heightToValMap.put(maxH, new ArrayList<>(Arrays.asList(node.val)));
        }
        return maxH;
    }



    private List<List<Integer>> findLeavesUtil(TreeNode root) {
        Map<Integer, Integer> adjMap = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();
        List<List<Integer>> ans = new LinkedList<>();

        generateAdjMap(adjMap, root, indegrees);

        Queue<Integer> queue = new LinkedList<>();

        for (int key: indegrees.keySet()) {
            if (indegrees.get(key) == 0) {
                queue.offer(key);
            }
        }

        // System.out.println(adjMap);
        // System.out.println(indegrees);


        while (!queue.isEmpty()) {
            int numNodes = queue.size();
            List<Integer> nodesInLevel = new LinkedList<>();

            for (int i =0; i < numNodes; i++) {
                int node = queue.poll();

                nodesInLevel.add(node);

                if (node == root.val) {
                    break;
                }

                // System.out.println(node);
                int neighbour = adjMap.get(node);

                indegrees.compute(neighbour, (k,v) -> {
                    return v-1;
                });

                if (indegrees.get(neighbour) == 0) {
                    queue.offer(neighbour);
                }
            }

            ans.add(nodesInLevel);
        }

        return ans;

    }

    private void generateAdjMap(Map<Integer, Integer> adjMap, TreeNode node, Map<Integer, Integer> indegrees) {
        if (node == null) return;
        indegrees.put(node.val, 0);
        if (node.left != null) {
            adjMap.put(node.left.val, node.val);
            indegrees.compute(node.val, (k, v) -> {
                return v + 1;
            });
            generateAdjMap(adjMap, node.left, indegrees);
        }

        if (node.right != null) {
            adjMap.put(node.right.val, node.val);
            indegrees.compute(node.val, (k, v) -> {
                return v + 1;
            });
            generateAdjMap(adjMap, node.right, indegrees);
        }
    }

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
