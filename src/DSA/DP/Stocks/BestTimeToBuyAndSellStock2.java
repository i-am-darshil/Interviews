package DSA.DP.Stocks;
import java.util.*;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

public class BestTimeToBuyAndSellStock2 {
    public static int maxProfit(int[] prices) {
        // int[][] dp = new int[prices.length][2];
        // for (int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return maxProfitUtil(0, 1, prices, dp);

        // return maxProfitUtilTab(prices);

        return maxProfitUtilTabSpaceOpt(prices);
    }

    public static int maxProfitUtil(int day, int canBuy, int[] prices, int[][] dp) {

        if (day == prices.length) return 0;

        if (dp[day][canBuy] != -1) return dp[day][canBuy];

        int maxP = 0;

        if (canBuy == 1) {
            int profitIfBuy =  prices[day]*-1 + maxProfitUtil(day + 1, 0, prices, dp);
            int profitIfNotBuy = maxProfitUtil(day + 1, 1, prices, dp);
            maxP = Math.max(profitIfBuy, profitIfNotBuy);
        } else {
            int profitISell =  prices[day] + maxProfitUtil(day + 1, 1, prices, dp);
            int profitIfNotSell = maxProfitUtil(day + 1, 0, prices, dp);
            maxP = Math.max(profitISell, profitIfNotSell);
        }

        return dp[day][canBuy] = maxP;

    }

    public static int maxProfitUtilTab(int[] prices) {
        int[][] dp = new int[prices.length+1][2];

        for (int i =prices.length-1; i>=0; i--) {
            for (int j=0; j<2; j++) {
                if (j == 1) {
                    int profitIfBuy = prices[i] * -1 + dp[i+1][0];
                    int profitIfNotBuy = dp[i+1][1];
                    dp[i][j] = Math.max(profitIfBuy, profitIfNotBuy);
                } else {
                    int profitISell = prices[i] + dp[i+1][1];
                    int profitIfNotSell = dp[i+1][0];
                    dp[i][j] = Math.max(profitISell, profitIfNotSell);
                }
            }
        }

        if (dp[0][1] < 0) return 0;
        return dp[0][1];
    }

    public static int maxProfitUtilTabSpaceOpt(int[] prices) {
        int[] next = new int[2];

        for (int i = prices.length-1; i>=0; i--) {

            int[] cur = new int[2];

            for (int j=0; j<2; j++) {
                if (j == 1) {
                    int profitIfBuy = prices[i] * -1 + next[0];
                    int profitIfNotBuy = next[1];
                    cur[j] = Math.max(profitIfBuy, profitIfNotBuy);
                } else {
                    int profitISell = prices[i] + next[1];
                    int profitIfNotSell = next[0];
                    cur[j] = Math.max(profitISell, profitIfNotSell);
                }
            }

            next = cur;
        }

        if (next[1] < 0) return 0;
        return next[1];
    }
}
