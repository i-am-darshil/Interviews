package DSA.DP.Subsequence;

import java.util.*;

public class TargetSum {

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,1,1};
        int target = 3;
        System.out.println(findTargetSumWays(nums, target));
        System.out.println(findTargetSumWaysApproach2(nums, target));
    }

    public static int findTargetSumWays(int[] nums, int target) {
//        Memoization not possible since target can be any value. It is not constrained to be 0 to target

//        int[][] dp = new int[nums.length][target+1];
//        for (int[] arr: dp) {
//            Arrays.fill(arr, -1);
//        }

        return findTargetSumWaysUtility(nums, nums.length - 1, target);

    }

    public static int findTargetSumWaysUtility(int[] nums, int index, int target) {
        if (index == 0) {
            if (target - nums[0] == 0 && target + nums[0] == 0) return  2;
            else if (target - nums[0] == 0 || target + nums[0] == 0) return  1;
            else return  0;
        }

//        Memoization not possible since target can be any value. It is not constrained to be 0 to target
//        if (dp[index][target] != -1) return dp[index][target];



        int pick = findTargetSumWaysUtility(nums, index-1, target-nums[index]);

        int notPick = findTargetSumWaysUtility(nums, index-1, target+nums[index]);

        return  pick + notPick;
    }

    public static int findTargetSumWaysApproach2(int[] nums, int target) {
        int sum = 0;
        for (int el: nums) {
            sum += el;
        }

        // s1 + s2 = sum
        // s1 - s2 = target
        // s1 = (sum + target)/2
        int newTarget = sum + target;

        if (newTarget % 2 != 0) return 0;
        newTarget = newTarget / 2;

        int[][] dp = new int[nums.length][newTarget+1];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        return findTargetSumWaysUtility2(nums, nums.length - 1, newTarget, dp);
    }

    public static int findTargetSumWaysUtility2(int[] nums, int index, int target, int[][] dp) {
        if (index == 0) {
            if (target == nums[0]) {
                if (nums[0] == 0) return 2;
                else return 1;
            }
            if (target == 0) return 1;
            else return 0;
        }

        if (dp[index][target] != -1) return dp[index][target];

        int pick = 0;
        if (target-nums[index] >= 0) {
            pick = findTargetSumWaysUtility2(nums, index-1, target-nums[index], dp);
        }

        int notPick = findTargetSumWaysUtility2(nums, index-1, target, dp);

        return dp[index][target] = pick + notPick;

    }
}
