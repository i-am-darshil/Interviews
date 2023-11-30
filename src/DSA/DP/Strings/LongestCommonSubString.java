package DSA.DP.Strings;

import java.util.Arrays;

// Youtube comment section of https://www.youtube.com/watch?v=_wP9mWNPL5w&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=28
/*
here is the memorization method:
int lcsUtil(string& s1, string& s2, int n, int m, vector<vector<int>>& dp) {
    if (n == 0 || m == 0) {
        return 0;
    }

    if (dp[n][m] != -1) {
        return dp[n][m];
    }

    int result = 0;

    if (s1[n-1] == s2[m-1]) {
        result = 1 + lcsUtil(s1, s2, n-1, m-1, dp);
    }
    else {
        result = 0;
    }

    dp[n][m] = result;
    return result;
}

int lcs(string& s1, string& s2) {
    int n = s1.size();
    int m = s2.size();

    vector<vector<int>> dp(n+1, vector<int>(m+1, -1));

    int ans = 0;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            lcsUtil(s1, s2, i, j, dp);
            ans = max(ans, dp[i][j]);
        }
    }

    return ans;
}
 */

public class LongestCommonSubString {

    public static void main(String[] args) {
        String s1 = "yxxzzxxxx";
        String s2 = "yzyzxxyxxz";

        System.out.println(lcs(s1, s2));
        System.out.println(lcsDp(s1, s2));

    }

    public static int lcs(String str1, String str2){
        // Write your code here.
        int[] maxSum = new int[1];
        lcsUtility(str1.length()-1, str2.length()-1, str1, str2, 0, maxSum);

        return maxSum[0];
    }


    public static int lcsDp(String str1, String str2){
        // Write your code here.

        int[] maxSum = new int[1];
        int[][] dp = new int[str1.length()][str2.length()];

        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        lcsUtilityDp(str1.length()-1, str2.length()-1, str1, str2, 0, maxSum, dp);
        return maxSum[0];
    }

    private static void lcsUtility(int i, int j, String s1, String s2, int sum, int[] maxSum) {

        if (i < 0 || j < 0) return;

        if (s1.charAt(i) == s2.charAt(j)) {
            maxSum[0] = Math.max(maxSum[0], sum+1);

            lcsUtility(i-1, j-1, s1, s2, sum+1, maxSum);
        }
        lcsUtility(i-1, j, s1, s2, 0, maxSum);
        lcsUtility(i, j-1, s1, s2, 0, maxSum);
    }

    private static void lcsUtilityDp(int i, int j, String s1, String s2, int sum, int[] maxSum, int[][] dp) {

        if (i < 0 || j < 0) return;

        if (s1.charAt(i) == s2.charAt(j)) {
            // dp is not much of use here
            maxSum[0] = Math.max(maxSum[0], sum+1);
            dp[i][j] = maxSum[0];
            lcsUtility(i-1, j-1, s1, s2, sum+1, maxSum);
        }
        lcsUtility(i-1, j, s1, s2, 0, maxSum);
        lcsUtility(i, j-1, s1, s2, 0, maxSum);
    }
}
