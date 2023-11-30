package DSA.Arrays;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {

        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[i+1] = nums[j];
                i++;
            }
        }

        return i+1;

    }
}
