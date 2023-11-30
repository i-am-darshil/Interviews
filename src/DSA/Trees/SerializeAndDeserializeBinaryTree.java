package DSA.Trees;

import java.util.*;

public class SerializeAndDeserializeBinaryTree {
    private static String DELIMITER = "____DIFF____";

    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        processNode(root, queue, sb);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            processNode(node.left, queue, sb);
            processNode(node.right, queue, sb);
        }

        sb = sb.delete(sb.length()-1, sb.length());

        return sb.toString();
    }

    private void processNode(TreeNode node, Queue<TreeNode> queue, StringBuilder sb) {
        if (node != null) {
            sb.append(node.val);
            sb.append(",");
            queue.add(node);
        } else {
            sb.append("X");
            sb.append(",");
        }
    }

    public TreeNode deserialize(String data) {
        if (data.equals("X")) return null;
        System.out.println(data);
        String[] dataArr = data.split(",");

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(dataArr[0]));

        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (dataArr[i].equals("X")) {
                node.left = null;
            } else {
                node.left = new TreeNode(Integer.valueOf(dataArr[i]));
                queue.offer(node.left);
            }
            i++;

            if (dataArr[i].equals("X")) {
                node.right = null;
            } else {
                node.right = new TreeNode(Integer.valueOf(dataArr[i]));
                queue.offer(node.right);
            }
            i++;
        }

        return root;

    }

    // INITIAL APPROACH TRIED. IT FAILED FOR TREES THAT HAS DUPLICATE VALUES

    // Encodes a tree to a single string.
    public String serializeForUniqueVals(TreeNode root) {
        if (root == null) return "";
        List<Integer> inorderTraversal = new ArrayList<>();
        List<Integer> preOrderTraversal = new ArrayList<>();

        inorder(root, inorderTraversal);
        preorder(root, preOrderTraversal);

        StringBuilder sb = new StringBuilder();

        sb.append(inorderTraversal.get(0));
        for (int i = 1; i < inorderTraversal.size(); i++) {
            sb.append(",");
            sb.append(inorderTraversal.get(i));
        }

        sb.append(DELIMITER);

        sb.append(preOrderTraversal.get(0));
        for (int i = 1; i < preOrderTraversal.size(); i++) {
            sb.append(",");
            sb.append(preOrderTraversal.get(i));
        }

        System.out.println(sb);

        return sb.toString();
    }

    private void inorder(TreeNode node, List<Integer> inorderTraversal) {
        if (node == null) return;

        if (node.left != null) {
            inorder(node.left, inorderTraversal);
        }
        inorderTraversal.add(node.val);
        if (node.right != null) {
            inorder(node.right, inorderTraversal);
        }
    }

    private void preorder(TreeNode node, List<Integer> preOrderTraversal) {
        if (node == null) return;
        preOrderTraversal.add(node.val);

        if (node.left != null) {
            preorder(node.left, preOrderTraversal);
        }

        if (node.right != null) {
            preorder(node.right, preOrderTraversal);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeForUniqueVals(String data) {
        String[] traversals = data.split(DELIMITER);

        if (traversals.length < 2) return null;
        // System.out.println("length " + traversals.length);
        // System.out.println("length " + traversals);
        // for (String s: traversals) {
        //     System.out.println("n " + s);
        // }


        String[] inorder = traversals[0].split(",");
        String[] preorder = traversals[1].split(",");
        int N = inorder.length;

        // Map<String, Integer> inorderValToIndexMap = new HashMap<>();

        // for (int i =0; i < N; i++) {
        //     inorderValToIndexMap.put(inorder[i], i);
        // }

        return constructBinaryTree(inorder, preorder, 0, N-1, 0, N-1);
    }

    private TreeNode constructBinaryTree(String[] inorder, String[] preorder, int inStart, int inEnd, int preStart, int preEnd) {


        if (preStart > preEnd) return null;
        System.out.println(inStart + " " + inEnd + " ");
        System.out.println(preStart + " " + preEnd + " ");
        System.out.println("---");


        String val = preorder[preStart];
        // int indexInInorder = inorderValToIndexMap.get(val);
        int indexInInorder = findIndex(val, inorder, inStart, inEnd);

        int numNodes = indexInInorder - inStart;

        TreeNode left = constructBinaryTree(inorder, preorder, inStart, indexInInorder - 1, preStart + 1, (preStart + 1) + (numNodes-1) );

        TreeNode right = constructBinaryTree(inorder, preorder, indexInInorder+1, inEnd, (preStart + 1) + (numNodes-1) + 1, preEnd );

        TreeNode node = new TreeNode(Integer.valueOf(val));

        node.left = left;
        node.right = right;

        return node;
    }

    private int findIndex(String val, String[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (arr[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
