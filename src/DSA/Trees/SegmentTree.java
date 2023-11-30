package DSA.Trees;

public class SegmentTree {
    private static class Node {
        int startInterval;
        int endInterval;
        int query;
        Node left;
        Node right;

        public Node (int startInterval, int endInterval) {
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }

        public Node (int startInterval, int endInterval, int query) {
            this(startInterval, endInterval);
            this.query = query;
        }
    }

    private Node root;
    private int[] arr;

    public SegmentTree (int[] arr) {
        this.arr = arr;
        root = generateTree(0, arr.length - 1, arr);
    }

    // Generate Tree
    private Node generateTree(int startInterval, int endInterval, int[] arr) {

        if (startInterval == endInterval) {
            return new Node(startInterval, endInterval, arr[startInterval]);
        }

        Node node = new Node(startInterval, endInterval);
        int mid = startInterval + (endInterval - startInterval)/2;

        node.left = generateTree(startInterval, mid, arr);
        node.right = generateTree(mid+1, endInterval, arr);
        node.query = node.left.query + node.right.query;
        return node;
    }

    // Query Range
    public int query(int startRange, int endRange) {
        if (outOfRange(startRange) || outOfRange(endRange)) return Integer.MIN_VALUE;

        return query(startRange, endRange, root);
    }

    private int query(int startRange, int endRange, Node node) {
        if (nodeRangeWithinQueryRange(startRange, endRange, node)) {
            return node.query;
        }

        if (nodeRangeOutsideQueryRange(startRange, endRange, node)) {
            return 0;
        }

        return query(startRange, endRange, node.left) + query(startRange, endRange, node.right);
    }

    private boolean nodeRangeWithinQueryRange(int startRange, int endRange, Node node) {
        return (node.startInterval >= startRange && node.endInterval <= endRange);
    }

    private boolean nodeRangeOutsideQueryRange(int startRange, int endRange, Node node) {
        return (startRange > node.endInterval || endRange < node.startInterval);
    }

    private boolean outOfRange(int ind) {
        return ind < 0 || ind >= arr.length;
    }

    // Update
    public void update(int index, int newVal) {
        update(root, index, newVal);
    }

    private Node update(Node node, int index, int newVal){
        if (isNodeToBeUpdated(node, index)) {
            node.query = newVal;
            return node;
        }

        if (indexToUpdateOutsideNodeRange(node, index)) {
            return node;
        }

        node.left = update(node.left, index, newVal);
        node.right = update(node.right, index, newVal);
        node.query = node.left.query + node.right.query;

        return node;
    }

    private boolean indexToUpdateOutsideNodeRange(Node node, int index) {
        return index < node.startInterval || index > node.endInterval;
    }

    private boolean isNodeToBeUpdated(Node node, int index) {
        return node.startInterval == index && node.endInterval == index;
    }
}
