package DSA.DP.Subsequence;

import java.util.*;

public class UnboundedKnapsack {

    public static void main(String[] args) {
        int[] profit = new int[]{5, 11, 13};
        int[] weight = new int[]{2, 4, 6};
        int w = 10;
        int n = 3;
        System.out.println(10/3);
        System.out.println(unboundedKnapsack(n, w, profit, weight));
    }

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {

        int[][] dp = new int[n][w+1];

        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        // Write your code here.
        return unboundedKnapsackUtility(profit, weight, n-1,  w, dp);
    }

    public static int unboundedKnapsackUtility(int[] profit, int[] weight, int index, int wt, int[][] dp ) {

        if (wt <= 0) return 0;
        if (index == 0) {
            if (wt < weight[index]) return 0;
            return (profit[index] * (wt/weight[index]));
        }

        if (dp[index][wt] != -1) return dp[index][wt];

        int take = 0;
        if (wt - weight[index] >= 0) {
            take = profit[index] + unboundedKnapsackUtility(profit, weight, index, wt - weight[index], dp);
        }

        int notTake = unboundedKnapsackUtility(profit, weight, index - 1, wt, dp);

        return dp[index][wt] = Math.max(take, notTake);
    }
}
