package DSA.Trees;

import java.util.*;

public class ZigZagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new LinkedList<>();

        if (root == null) return ans;

        zigzagLevelOrder(root, ans);

        return ans;
    }

    private void zigzagLevelOrder(TreeNode root, List<List<Integer>> ans) {

        Stack<TreeNode> currStack = new Stack<>();

        currStack.push(root);
        int leftToRight = 1;

        while (!currStack.empty()) {
            int numNodesInLevel = currStack.size();
            List<Integer> levelNodes = new LinkedList<Integer>();
            Stack<TreeNode> nextStack = new Stack<>();

            for (int i = 0; i < numNodesInLevel; i++) {
                TreeNode node = currStack.pop();
                levelNodes.add(node.val);

                if (leftToRight == 1) {
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                }

            }

            currStack = nextStack;

            leftToRight *= -1;
            ans.add(levelNodes);
        }

    }

    private void zigzagLevelOrderUsingQueue(TreeNode root, List<List<Integer>> ans) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int leftToRight = 1;

        while (!queue.isEmpty()) {
            int numNodesInLevel = queue.size();
            // List<Integer> levelNodes = new ArrayList<>(numNodesInLevel);

            // for (int i=0; i<numNodesInLevel; i++) {
            //     levelNodes.add(0);
            // }

            List<Integer> levelNodes = Arrays.asList(new Integer[numNodesInLevel]);

            for (int i = 0; i < numNodesInLevel; i++) {
                TreeNode node = queue.poll();
                int indexToAdd = 0;
                if (leftToRight == 1) {
                    indexToAdd = i;
                } else {
                    indexToAdd = numNodesInLevel - 1 - i;
                }
                levelNodes.set(indexToAdd, node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            leftToRight *= -1;
            ans.add(levelNodes);
        }

    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
