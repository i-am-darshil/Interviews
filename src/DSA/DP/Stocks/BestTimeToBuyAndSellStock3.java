package DSA.DP.Stocks;
import java.util.*;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

public class BestTimeToBuyAndSellStock3 {
    public static int maxProfit(int[] prices) {

//         int[][][] dp = new int[prices.length][2][3];
//
//         for (int[][] array2d : dp) {
//             for (int[] arr: array2d) {
//                 Arrays.fill(arr, -1);
//             }
//         }
//         return maxProfitUtil(0, 1, 2, prices, dp);
//
//         return maxProfitUtilTab(prices);

        return maxProfitUtilTabSpaceOpt(prices);
    }

    private static int maxProfitUtil(int day, int canBuy, int transRem, int[] prices, int[][][] dp) {
        if (day == prices.length) return 0;
        if (transRem == 0) return 0;
        if (dp[day][canBuy][transRem] != -1) return dp[day][canBuy][transRem];

        int maxP = 0;

        if (canBuy == 1) {
            int profitWhenBuy = prices[day]*-1 + maxProfitUtil(day + 1, 0, transRem, prices, dp);
            int profitWhenNotBuy = maxProfitUtil(day + 1, 1, transRem, prices, dp);
            maxP = Math.max(profitWhenBuy, profitWhenNotBuy);
        } else {
            int profitWhenSell = prices[day] + maxProfitUtil(day + 1, 1, transRem-1, prices, dp);
            int profitWhenNotSell = maxProfitUtil(day + 1, 0, transRem, prices, dp);
            maxP = Math.max(profitWhenSell, profitWhenNotSell);
        }

        return dp[day][canBuy][transRem] = maxP;
    }

    private static int maxProfitUtilTab(int[] prices) {
        int[][][] dp = new int[prices.length+1][2][3];

        for (int day = prices.length-1; day >= 0; day--) {
            for (int canBuy = 0; canBuy < 2; canBuy++) {
                for (int transRem = 1; transRem < 3; transRem++) {
                    int maxP = 0;
                    if (canBuy == 1) {
                        int profitWhenBuy = prices[day]*-1 + dp[day+1][0][transRem];
                        int profitWhenNotBuy = dp[day+1][1][transRem];
                        maxP = Math.max(profitWhenBuy, profitWhenNotBuy);
                    } else {
                        int profitWhenSell = prices[day] + dp[day+1][1][transRem-1];
                        int profitWhenNotSell = dp[day+1][0][transRem];
                        maxP = Math.max(profitWhenSell, profitWhenNotSell);
                    }
                    dp[day][canBuy][transRem] = maxP;
                }
            }
        }

        return dp[0][1][2];

    }

    private static int maxProfitUtilTabSpaceOpt(int[] prices) {
        int[][] next = new int[2][3];

        for (int day = prices.length-1; day >= 0; day--) {
            int[][] cur = new int[2][3];

            for (int canBuy = 0; canBuy < 2; canBuy++) {
                for (int transRem = 1; transRem < 3; transRem++) {
                    int maxP = 0;
                    if (canBuy == 1) {
                        int profitWhenBuy = prices[day]*-1 + next[0][transRem];
                        int profitWhenNotBuy = next[1][transRem];
                        maxP = Math.max(profitWhenBuy, profitWhenNotBuy);
                    } else {
                        int profitWhenSell = prices[day] + next[1][transRem-1];
                        int profitWhenNotSell = next[0][transRem];
                        maxP = Math.max(profitWhenSell, profitWhenNotSell);
                    }
                    cur[canBuy][transRem] = maxP;
                }
            }

            next = cur;
        }

        return next[1][2];

    }
}
