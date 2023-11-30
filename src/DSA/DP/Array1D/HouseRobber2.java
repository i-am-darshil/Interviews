package DSA.DP.Array1D;

//https://leetcode.com/problems/house-robber-ii/description/

import java.util.*;

public class HouseRobber2 {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        // int[] dp = new int[nums.length];

        // Arrays.fill(dp, -1);
        // int takeLast = robUtils(nums.length - 1, nums, 1, dp);
        // Arrays.fill(dp, -1);
        // int takeFirst = robUtils(nums.length - 2, nums, 0, dp);

        // return Math.max(takeLast, takeFirst);

        return robUtilsTabulation(nums);
    }

    private int robUtils(int i, int[] nums, int upTill, int[] dp) {
        if (i == upTill) return nums[i];
        if (i < upTill) return 0;
        if (dp[i] != -1) return dp[i];

        int take = nums[i] + robUtils(i-2, nums, upTill, dp);
        int notTake = robUtils(i-1, nums, upTill, dp);

        return dp[i] = Math.max(take, notTake);
    }

    private int robUtilsTabulation(int[] nums) {

        int[] dp = new int[nums.length];

        // Take first
        for (int i=0; i<nums.length-1; i++) {
            int take = nums[i];
            if (i-2 >= 0) {
                take += dp[i-2];
            }
            int notTake = 0;
            if (i-1 >=0) {
                notTake += dp[i-1];
            }
            dp[i] = Math.max(take, notTake);
        }

        int takeFirst = dp[nums.length - 2];

        // Take last
        Arrays.fill(dp, 0);
        dp[0] = 0;
        for (int i=1; i<nums.length; i++) {
            int take = nums[i];
            if (i-2 >= 0) {
                take +=  dp[i-2];
            }

            int notTake = dp[i-1];
            dp[i] = Math.max(take, notTake);
        }
        int takeLast = dp[nums.length - 1];

        return Math.max(takeFirst, takeLast);
    }
}
