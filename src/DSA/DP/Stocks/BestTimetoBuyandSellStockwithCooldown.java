package DSA.DP.Stocks;

import java.util.*;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
public class BestTimetoBuyandSellStockwithCooldown {
    public int maxProfit(int[] prices) {

        // int[][] dp = new int[prices.length][2];
        // for (int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return maxProfitUtil(0, 1, prices, dp);

        return maxProfitUtilTab(prices);
    }

    private int maxProfitUtil(int day, int canBuy, int[] prices, int[][] dp) {
        if (day >= prices.length) return 0;

        if (dp[day][canBuy] != -1) return dp[day][canBuy];

        int maxP = 0;
        if (canBuy == 1) {
            int brought = prices[day] * -1 + maxProfitUtil(day+1, 0, prices, dp);
            int notBrought = maxProfitUtil(day+1, 1, prices, dp);
            maxP = Math.max(brought, notBrought);
        } else {
            int sold = prices[day] * 1 + maxProfitUtil(day+2, 1, prices, dp);
            int notSold = maxProfitUtil(day+1, 0, prices, dp);
            maxP = Math.max(sold, notSold);
        }

        return dp[day][canBuy] = maxP;
    }

    private int maxProfitUtilTab(int[] prices) {
        int[][] dp = new int[prices.length + 2][2];

        for (int day = prices.length - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                int maxP = 0;
                if (canBuy == 1) {
                    int brought = prices[day] * -1 + dp[day+1][0];
                    int notBrought = dp[day+1][1];
                    maxP = Math.max(brought, notBrought);
                } else {
                    int sold = prices[day] * 1 + dp[day+2][1];
                    // if (day+2 < prices.length) {
                    //     sold += dp[day+2][1];
                    // }
                    int notSold = dp[day+1][0];
                    // if (day+1 < prices.length) {
                    //     notSold = dp[day+1][0];
                    // }
                    maxP = Math.max(sold, notSold);
                }
                dp[day][canBuy] = maxP;
            }
        }

        return dp[0][1];
    }
}
