package DSA.DP.Subsequence;

import java.util.*;

public class KnapSack01 {
    public static void main(String[] args) {
        int[] val = new int[] {4, 2, 17, 9};
        int[] weights = new int[] {1, 2, 4, 3};

        int MAX_W = 4;

        System.out.println(knapsack(val, weights, MAX_W));
    }

    static int knapsack(final int[] val, final int[] weights, final int MAX_W) {
        int[][] dp = new int[val.length][MAX_W + 1];

        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        return knapSackUtility(val, weights, val.length - 1, MAX_W, dp);
    }

    static int knapSackUtility(final int[] val, final int[] weights, final int index,
                               final int weightRemaining, final int[][] dp) {

        if (weightRemaining == 0) return 0;
        if (index == 0) {
            if (weights[index] <= weightRemaining) return val[index];
            else return 0;
        }
        if (dp[index][weightRemaining] != -1) return dp[index][weightRemaining];

        int pick = 0;
        if (weightRemaining - weights[index] >= 0) {
            pick = val[index] + knapSackUtility(val, weights, index-1, weightRemaining - weights[index], dp);
        }

        int notPick = knapSackUtility(val, weights, index-1, weightRemaining, dp);

        dp[index][weightRemaining] = Math.max(pick, notPick);

        return dp[index][weightRemaining];

    }
}
