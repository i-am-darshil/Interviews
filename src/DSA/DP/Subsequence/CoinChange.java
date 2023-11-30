package DSA.DP.Subsequence;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = new int[] {1, 2, 5};
        int amount = 11;
        System.out.println(CoinChange(coins, amount));
    }

    public static int CoinChange(int[] coins, int amount) {

        if (amount == 0) return 0;

        // Memoization
//        int dp[][] = new int[coins.length][amount + 1];
//        for (int[] arr: dp) {
//            Arrays.fill(arr, -1);
//        }
//        int ans = CoinChangeUtility(coins, coins.length - 1, amount, dp);
//        if ( ans >= (int) Math.pow(10, 9)) {
//            return -1;
//        }
//        return ans;

        // Tabulation
        return CoinChangeTabulation(coins, amount);
    }

    public static int CoinChangeUtility(final int[] coins, final int index, final int amount, final int[][] dp) {

        if (amount == 0) return 0;
        if (index == 0) {
            if (coins[0]  > amount) return (int) Math.pow(10, 9);
            else {
                if (amount % coins[0] == 0) return amount / coins[0];
                else return (int) Math.pow(10, 9);
            }

        }
        if (dp[index][amount] != -1) return dp[index][amount];

        int pick = Integer.MAX_VALUE;
        if (amount - coins[index] >= 0) {
            pick = 1 + CoinChangeUtility(coins, index, amount - coins[index], dp);
        }
        int notPick = 0 + CoinChangeUtility(coins, index-1, amount, dp);

        return dp[index][amount] = Math.min(pick, notPick);
    }

    public static int CoinChangeTabulation(final int[] coins, final int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i=0; i<coins.length; i++) {
            dp[i][0] = 0;
        }

        for (int i=0; i<=amount; i++) {
            if (i < coins[0]) {
                dp[0][i] = (int) Math.pow(10, 9);
            } else if (coins[0] == i) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 1 + dp[0][i-coins[0]];
            }
        }

        for (int index=1; index<coins.length; index++) {
            for (int amt=1; amt<=amount; amt++) {
                int pick = (int) Math.pow(10, 9);
                if (amt - coins[index] >= 0) {
                    pick = 1 + dp[index][amt - coins[index]];
                }
                dp[index][amt] = Math.min(pick, dp[index-1][amt]);
            }
        }

        if (dp[coins.length - 1][amount] >= (int) Math.pow(10, 9)) {
            return -1;
        }

        return dp[coins.length - 1][amount];
    }
}
