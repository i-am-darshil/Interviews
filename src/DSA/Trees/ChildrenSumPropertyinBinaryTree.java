package DSA.Trees;

// https://www.codingninjas.com/studio/problems/childrensumproperty_790723?leftPanelTab=0



import java.util.* ;
import java.io.*;
/*************************************************************

 Following is the Binary Tree node structure

 class BinaryTreeNode < Integer > {
 int data;
 BinaryTreeNode < Integer > left;
 BinaryTreeNode < Integer > right;

 public BinaryTreeNode(int data) {
 this.data = data;
 }
 }

 *************************************************************/

public class ChildrenSumPropertyinBinaryTree {

    static class BinaryTreeNode < Integer > {
        int data;
        BinaryTreeNode < Integer > left;
        BinaryTreeNode < Integer > right;

        public BinaryTreeNode(int data) {
            this.data = data;
        }
    }

    public static void changeTree(BinaryTreeNode < Integer > root) {
        // Write your code here.
        rebalanceUtil2(root);
    }

    private static void rebalanceUtil2(BinaryTreeNode< Integer > node) {
        if (node == null) return;

        int childSum = getChildSum(node);

        // if (childSum > node.data) {
        //     node.data = childSum;
        // } else {
        //     if (node.left != null) {
        //         node.left.data = node.data;
        //     }
        //     if (node.right != null) {
        //         node.right.data = node.data;
        //     }
        // }

        if (node.data > childSum) {
            if (node.left != null) {
                node.left.data = node.data;
            }
            if (node.right != null) {
                node.right.data = node.data;
            }
        }

        if (node.left != null) {
            rebalanceUtil2(node.left);
        }
        if (node.right != null) {
            rebalanceUtil2(node.right);
        }

        int childSum2 = getChildSum(node);
        if (childSum2 > 0) {
            node.data = childSum2;
        }
    }

    private static int getChildSum(BinaryTreeNode< Integer > node) {
        int childSum = 0;
        if (node.left != null) {
            childSum += node.left.data;
        }
        if (node.right != null) {
            childSum += node.right.data;
        }
        return childSum;
    }

    private static void rebalanceUtil(BinaryTreeNode< Integer > root) {
        int maxInTree = findMax(root);
        rebalanceAttempt2(root, maxInTree);
    }

    private static void rebalanceAttempt2(BinaryTreeNode< Integer > node, int maxInTree) {

        if (node == null) return;

        if (node.left == null && node.right == null) {
            node.data = maxInTree;
            return;
        }

        int sum = 0;
        if (node.left != null) {
            rebalanceAttempt2(node.left, maxInTree);
            sum += node.left.data;
        }

        if (node.right != null) {
            rebalanceAttempt2(node.right, maxInTree);
            sum += node.right.data;
        }

        node.data = sum;
    }

    private static int findMax(BinaryTreeNode node) {
        if (node == null) return 0;

        int localMax = node.data;
        int left = findMax(node.left);
        localMax = Math.max(localMax, left);
        int right = findMax(node.right);
        localMax = Math.max(localMax, right);

        return localMax;
    }

    private static void rebalance(BinaryTreeNode< Integer > node) {

        if (node == null) return;

        if (node.left != null && node.right != null) {
            int sumChild = node.left.data + node.right.data;

            if (node.data > sumChild) {
                int diff = node.data - sumChild;
                node.left.data = node.left.data + diff;
                rebalance(node.left);
            } else {
                rebalance(node.left);
                rebalance(node.right);
                int sumChild2 = node.left.data + node.right.data;
                if (node.data > sumChild2) {
                    rebalance(node);
                } else {
                    node.data = sumChild2;
                }
            }
        } else if (node.left != null) {
            int sumChild = node.left.data;
            if (node.data > sumChild) {
                node.left.data = node.data;
                rebalance(node.left);
            } else {
                rebalance(node.left);
                int sumChild2 = node.left.data;
                if (node.data > sumChild2) {
                    rebalance(node);
                } else {
                    node.data = sumChild2;
                }
            }
        } else if (node.right != null) {
            int sumChild = node.right.data;
            if (node.data > sumChild) {
                node.right.data = node.data;
                rebalance(node.right);
            } else {
                rebalance(node.right);
                int sumChild2 = node.right.data;
                if (node.data > sumChild2) {
                    rebalance(node);
                } else {
                    node.data = sumChild2;
                }
            }
        }
    }
}











