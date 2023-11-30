package DSA.DP.Strings;

// https://leetcode.com/problems/distinct-subsequences/description/

import java.util.*;

public class DistinctSubsequences {

    public int numDistinct(String s, String t) {

        // return printLCSUtil(s, t);
        int[][] dp = new int[s.length()][t.length()];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return numDistinctUtil(s.length()-1, t.length()-1, s, t, dp);

    }

    private int printLCSUtil(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i=1; i<=s1.length(); i++) {
            for (int j=1; j<= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int i= s1.length();
        int j = s2.length();

        int ans = 0;
        if (dp[i][j] != j) {
            return 0;
        }

        int[][] anotherDp = new int[i+1][j+1];

        for (int[] arr: anotherDp) {
            Arrays.fill(arr, -1);
        }

        return traverse(i, j, dp, s1, s2, anotherDp);

        // boolean checkUp = false;

        // while (j > 0 && i > 0) {
        //     if (s1.charAt(i-1) == s2.charAt(j-1)) {
        //         if (dp[i][j] == dp[i-1][j]) ans++;
        //         if (dp[i][j] == dp[i][j-1]) ans++;
        //         if (j==0 && dp[i][j] == dp[i][j-1]) checkUp = true;
        //         i--;
        //         j--;
        //     } else {
        //         if (dp[i-1][j] > dp[i][j-1]) {
        //             i--;
        //         } else {
        //             j--;
        //         }
        //     }
        // }

        // return ans;
    }

    private int traverse(int i, int j, int[][] dp, String s1, String s2, int[][] anotherDp) {
        if (j < 1) return 1;

        if (anotherDp[i][j] != -1) return anotherDp[i][j];

        if (s1.charAt(i-1) == s2.charAt(j-1)) {
            int diag = traverse(i-1, j-1, dp, s1, s2, anotherDp);
            int up = 0;
            int left = 0;

            if (dp[i][j] == dp[i-1][j]) {
                up = traverse(i-1, j, dp, s1, s2, anotherDp);
            }

            if (dp[i][j] == dp[i][j-1]) {
                left = traverse(i, j-1, dp, s1, s2, anotherDp);
            }

            return anotherDp[i][j] = diag + up + left;
        } else {
            if (dp[i-1][j] > dp[i][j-1]) {
                return anotherDp[i][j] = traverse(i-1, j, dp, s1, s2, anotherDp);
            } else {
                return anotherDp[i][j] = traverse(i, j-1, dp, s1, s2, anotherDp);
            }
        }
    }

    private int numDistinctUtil(int i, int j, String s1, String s2, int[][] dp) {
        if (j < 0) return 1;
        if (i < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            int take = numDistinctUtil(i-1, j-1, s1, s2, dp);
            int notTake = numDistinctUtil(i-1, j, s1, s2, dp);
            return dp[i][j] = take + notTake;
        }

        return dp[i][j] = numDistinctUtil(i-1, j, s1, s2, dp);
    }

}
