package DSA.DP.Subsequence;
import java.util.*;

public class CoinChange2 {
    public static int change(int amount, int[] coins) {

        int[][] dp = new int[coins.length][amount + 1];

        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }

        return changeUtility(coins, coins.length - 1, amount, dp);
    }

    public static int changeUtility(int[] coins, int index, int amount, int[][] dp ) {
        if (amount == 0) return 1;

        if (index == 0) {
            if (amount % coins[index] == 0) {
                return 1;
            }
            return 0;
        }

        if (dp[index][amount] != -1) return dp[index][amount];

        int pick = 0;
        if (amount - coins[index] >= 0) {
            pick = changeUtility(coins, index, amount - coins[index], dp);
        }

        int notPick = changeUtility(coins, index-1, amount, dp);

        return dp[index][amount] = pick + notPick;
    }
}
