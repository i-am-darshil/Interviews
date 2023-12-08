package DSA.ImplementationSpecific;

// https://leetcode.com/problems/rle-iterator/description/
public class RLEIterator {

    private final int[] encoding;
    private int pointer;

    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        pointer = 0;
    }

    public int next(int n) {

        while (pointer < encoding.length) {
            int count = encoding[pointer];
            int el = encoding[pointer + 1];

            if (count >= n) {
                encoding[pointer] -= n;
                return el;
            } else {
                n -= count;
            }

            pointer += 2;
        }

        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */
