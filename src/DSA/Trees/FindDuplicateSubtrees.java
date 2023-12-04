package DSA.Trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/find-duplicate-subtrees/description/
public class FindDuplicateSubtrees {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        Map<String, Integer> tripletToIdMap = new HashMap<>();
        Map<String, Integer> tripletToCntMap = new HashMap<>();

        findDuplicates(root, tripletToIdMap, tripletToCntMap, res);

        return res;
    }

    private int findDuplicates(TreeNode node, Map<String, Integer> tripletToIdMap, Map<String, Integer> tripletToCntMap, List<TreeNode> res) {
        if (node == null) {
            return 0;
        }

        int leftSubTreeId = findDuplicates(node.left, tripletToIdMap, tripletToCntMap, res);
        int rightSubTreeId = findDuplicates(node.right, tripletToIdMap, tripletToCntMap, res);

        String triplet = leftSubTreeId + "," + node.val + "," + rightSubTreeId;

        if (tripletToIdMap.containsKey(triplet)) {
            int cnt = tripletToCntMap.get(triplet);
            if (cnt == 1) res.add(node);
            tripletToCntMap.put(triplet, cnt + 1);
        } else {
            tripletToIdMap.put(triplet, tripletToIdMap.size() + 1);
            tripletToCntMap.put(triplet, 1);
        }

        return tripletToIdMap.get(triplet);
    }
}
