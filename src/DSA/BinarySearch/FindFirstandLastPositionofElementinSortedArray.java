package DSA.BinarySearch;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        return searchRangeUtil(nums, target);
    }

    private int[] searchRangeUtil(int[] nums, int target) {
        int firstPos = binarySearch(nums, target, true);
        if (firstPos == -1) return new int[] {-1, -1};

        int lastPos = binarySearch(nums, target, false);
        return new int[] {firstPos, lastPos};
    }

    private int binarySearch(int[] nums, int target, boolean findFirst) {
        int ans = -1;

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                ans = mid;
                if (findFirst) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }
}
