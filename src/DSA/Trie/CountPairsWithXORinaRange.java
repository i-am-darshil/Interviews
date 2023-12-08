package DSA.Trie;

// https://leetcode.com/problems/count-pairs-with-xor-in-a-range/
public class CountPairsWithXORinaRange {
    public int countPairs(int[] nums, int low, int high) {
        Trie trie = new Trie(20000);
        int countLessThanLow = 0;
        int countLessThanHighPlus1 = 0;
        int nicePair = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            countLessThanLow = trie.getCountLessThan(num, low);
            countLessThanHighPlus1 = trie.getCountLessThan(num, high + 1);
            nicePair += (countLessThanHighPlus1 - countLessThanLow);
            trie.add(num);
        }

        return nicePair;
    }

    class Node {
        private Node[] children;
        private int count = 0;

        public Node() {
            this.children = new Node[2];
        }

        public boolean contains(int bit) {
            return children[bit] != null;
        }

        public void add(int bit) {
            children[bit] = new Node();
        }

        public Node get(int bit) {
            return children[bit];
        }

        public void incrementCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    class Trie {

        private Node root;
        private final int maxDigits;

        public Trie(int maxNumRange) {
            root = new Node();
            maxDigits = (int) (Math.log(maxNumRange) / Math.log(2));
        }

        public void add(int num) {
            Node node = root;

            for (int i = maxDigits; i >= 0; i--) {
                int bit = getBit(num, i);

                if (!node.contains(bit)) {
                    node.add(bit);
                }

                Node child = node.get(bit);
                child.incrementCount();

                node = child;
            }
        }

        public int getCountLessThan(int num, int lessThan) {
            Node node = root;
            int count = 0;

            for (int i = maxDigits; i >=0 ; i--) {
                if (node == null) {
                    return count;
                }

                int numBit = getBit(num, i);
                int lessThanBit = getBit(lessThan, i);

                if (lessThanBit == 1) {
                    if (node.contains(numBit)) {
                        count += node.get(numBit).getCount();
                    }
                    Node child = node.get(1 - numBit);
                    node = child;
                } else {
                    Node child = node.get(numBit);
                    node = child;
                }
            }

            return count;
        }

        private int getBit(int n, int k) {
            return (n >> k) & 1;
        }

    }
}
