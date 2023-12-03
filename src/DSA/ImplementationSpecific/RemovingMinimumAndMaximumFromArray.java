package DSA.ImplementationSpecific;

// https://leetcode.com/problems/removing-minimum-and-maximum-from-array/description/
public class RemovingMinimumAndMaximumFromArray {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minPos = 0;
        int maxPos = 0;

        int minVal = nums[0];
        int maxVal = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
                minPos = i;
            }

            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxPos = i;
            }
        }

        int minD = 0;
        int maxD = 0;
        int dels = 0;
        int minDels = Integer.MAX_VALUE;;

        // both left
        minD = minPos + 1;
        maxD = maxPos + 1;
        dels = Math.max(minD, maxD);
        minDels = Math.min(minDels, dels);

        // both right
        minD = n - minPos;
        maxD = n - maxPos;
        dels = Math.max(minD, maxD);
        minDels = Math.min(minDels, dels);

        // min left, max right
        minD = minPos + 1;
        maxD = n - maxPos;
        dels = minD + maxD;
        minDels = Math.min(minDels, dels);

        // min right, max left
        minD = n - minPos;
        maxD = maxPos + 1;
        dels = minD + maxD;
        minDels = Math.min(minDels, dels);

        return minDels;
    }
}
