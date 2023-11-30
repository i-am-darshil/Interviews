package DSA.DP.Stocks;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/

import java.util.*;

public class BestTimetoBuyandSellStockwithFee {
    public int maxProfit(int[] prices, int fee) {

        int[][] dp = new int[prices.length][2];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return maxProfitUtil(0, 1, prices, fee, dp);
    }

    private int maxProfitUtil(int day, int canBuy, int[] prices, int fee, int[][] dp) {

        if (day == prices.length) {
            return 0;
        }
        if (dp[day][canBuy] != -1) return dp[day][canBuy];

        int maxP = 0;
        if (canBuy == 1) {
            int brought = prices[day] * -1 + maxProfitUtil(day + 1, 0, prices, fee, dp);
            int notBrought = maxProfitUtil(day + 1, 1, prices, fee, dp);
            maxP = Math.max(brought, notBrought);
        } else {
            int sold = prices[day] - fee + maxProfitUtil(day + 1, 1, prices, fee, dp);
            int notSold = maxProfitUtil(day + 1, 0, prices, fee, dp);
            maxP = Math.max(sold, notSold);
        }

        return dp[day][canBuy] = maxP;
    }
}
