package DSA.DP.Strings;

// https://leetcode.com/problems/longest-palindromic-subsequence

import java.util.*;

public class LongestPalindromeSubseq {
    public static int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return longestPalindromeSubseqUtil(s, 0, s.length()-1, dp);
    }

    private static int longestPalindromeSubseqUtil(String str, int s, int e, int[][] dp) {
        if (s > e) return 0;
        if (s == e) {
            if (str.charAt(s) == str.charAt(e)) return 1;
            else return 0;
        }
        if (dp[s][e] != -1) return dp[s][e];

        if (str.charAt(s) == str.charAt(e)) {
            return dp[s][e] = 2 + longestPalindromeSubseqUtil(str, s+1, e-1, dp);
        } else {
            int movedStart = longestPalindromeSubseqUtil(str, s+1, e, dp);
            int movedEnd = longestPalindromeSubseqUtil(str, s, e-1, dp);
            return dp[s][e] = Math.max(movedStart, movedEnd);
        }
    }
}
