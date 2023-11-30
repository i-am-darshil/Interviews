package DSA.Trees;

import java.util.*;

// https://www.geeksforgeeks.org/print-path-between-any-two-nodes-in-a-binary-tree/
public class PathBetweenTwoNodes {

    public static void main(String[] args) {
        Node root = getNode(0);
        root.left = getNode(1);
        root.left.left = getNode(3);
        root.left.left.left = getNode(7);
        root.left.right = getNode(4);
        root.left.right.left = getNode(8);
        root.left.right.right = getNode(9);
        root.right = getNode(2);
        root.right.left = getNode(5);
        root.right.right = getNode(6);

        int node1 = 7;
        int node2 = 9;

        System.out.println(getPath(root, node1, node2));
    }

    private static List<Integer> getPath(Node root, int node1, int node2) {
        List<Integer> ans = new LinkedList<>();
        getPathUtil(root, node1, node2, ans);
        return ans;
    }

    // Approach 2
    private static List<Integer> getPathUtil(Node node, int node1, int node2, List<Integer> ans) {

        if (node == null) return new LinkedList<>();

        List<Integer> leftPath = getPathUtil(node.left, node1, node2, ans);
        List<Integer> rightPath = getPathUtil(node.right, node1, node2, ans);

        if (leftPath.size() > 0 && rightPath.size() > 0) {
            List<Integer> combinedList = new LinkedList<>();
            combinedList.addAll(leftPath);
            combinedList.add(node.data);
            Collections.reverse(rightPath);
            combinedList.addAll(rightPath);
            ans.addAll(combinedList);
            return combinedList;
        }


        if (leftPath.size() > 0) {
            leftPath.add(node.data);
            if (node.data == node1 || node.data == node2) {
                ans.addAll(leftPath);
            }
            return leftPath;
        }

        if (rightPath.size() > 0) {
            rightPath.add(node.data);
            if (node.data == node1 || node.data == node2) {
                ans.addAll(leftPath);
            }
            return rightPath;
        }

        if (node.data == node1 || node.data == node2) {
            return new LinkedList<>(Arrays.asList(node.data));
        }

        return new LinkedList<>();
    }

    private static Node getNode(int data)
    {
        Node newNode = new Node();
        newNode.data = data;
        newNode.left = newNode.right = null;
        return newNode;
    }

    private static class Node {
        Node left;
        Node right;
        int data;

    }
}
