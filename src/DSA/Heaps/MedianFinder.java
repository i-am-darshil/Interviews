package DSA.Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/find-median-from-data-stream/description/
public class MedianFinder {
    private final Queue<Integer> maxHeap;
    private final Queue<Integer> minHeap;
    private int numElements;

    public MedianFinder() {
        Comparator<Integer> maxHeapComp = Comparator.comparing((x) -> x, (x1, x2) -> x2-x1);
        maxHeap = new PriorityQueue<>(maxHeapComp);
        minHeap = new PriorityQueue<>();
        numElements = 0;
    }

    public void addNum(int num) {
        numElements++;
        maxHeap.offer(num);
        int numElementsInMaxHeap = numElements / 2;
        if (maxHeap.size() > 1 && maxHeap.size() > numElementsInMaxHeap) {
            int elementToBePlacedInMinHeap = maxHeap.poll();
            minHeap.offer(elementToBePlacedInMinHeap);
        }

        while (maxHeap.size() > 1 && maxHeap.size() > 1 && maxHeap.peek() > minHeap.peek()) {
            int maxHeapEl = maxHeap.poll();
            int minHeapEl = minHeap.poll();
            maxHeap.offer(minHeapEl);
            minHeap.offer(maxHeapEl);
        }
    }

    // 1,4
    // 2,3

    public double findMedian() {
        double median;
        if (numElements == 1) {
            median = maxHeap.peek();
        }
        else if (numElements % 2 == 0) {
            median = (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            median = minHeap.peek() * 1.0;
        }
        return median;
    }
}
