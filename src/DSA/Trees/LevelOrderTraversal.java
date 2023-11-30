package DSA.Trees;

import java.util.*;
public class LevelOrderTraversal {

    private static class TreeNode <T> {
        T val;
        TreeNode left;
        TreeNode right;
    }

    public static List<List<Integer>> LevelOrderTraversal(TreeNode root) {

        List<List<Integer>> ans = new LinkedList<>();

        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);


        while (!queue.isEmpty()) {

            int numElementsInLevel = queue.size();
            List<Integer> levelList = new LinkedList<>();

            for (int i=0; i<numElementsInLevel; i++) {
                TreeNode node = queue.poll();
                levelList.add((int) node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ans.add(levelList);

        }

        return ans;

    }
}
