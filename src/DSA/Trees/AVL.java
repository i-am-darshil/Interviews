package DSA.Trees;

public class AVL {

    public class Node {
        Node left;
        Node right;
        int val;
        int height;

        public Node (int val) {
            this.val = val;
        }
    }

    private Node root;

    public AVL () {

    }

    public void insert(int val) {
        root = insert(root, val);
    }

    private Node insert(Node node, int val) {
        if (node == null) {
            Node newNode = new Node(val);
            return newNode;
        }

        if (val > node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return rotate(node);
    }

    private Node rotate(Node node) {

        int diffInLeftAndRightChildHeight = height(node.left) - height(node.right);

        // left heavy
        if (diffInLeftAndRightChildHeight > 1) {
            Node child = node.left;
            int diffInHeightOfChild = height(child.left) - height(child.right);
            // left left case
            if (diffInHeightOfChild > 0) {
                return rotateRight(node);
            }
            // left right case
            else {
                node.left = rotateLeft(child);
                return rotateRight(node);
            }
        }

        // right heavy
        else if (diffInLeftAndRightChildHeight < -1) {
            Node child = node.right;
            int diffInHeightOfChild = height(child.left) - height(child.right);

            // right left case
            if (diffInHeightOfChild > 0) {
                node.right = rotateRight(child);
                return rotateLeft(node);
            }
            // right right case
            else {
                return rotateLeft(node);
            }
        }

        return node;
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.left;
        Node rightGrandChild = leftChild.right;

        node.left = rightGrandChild;
        leftChild.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        leftChild.height = Math.max(height(leftChild.left), height(leftChild.right)) + 1;

        return leftChild;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        Node leftGrandChild = rightChild.left;

        node.right = leftGrandChild;
        rightChild.left = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rightChild.height = Math.max(height(rightChild.left), height(rightChild.right)) + 1;

        return rightChild;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        else return node.height;
    }



}


