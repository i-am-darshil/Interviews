package DSA.DP.Strings;

// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
import java.util.*;

public class minInsertionsToMakeStringPalindrome {
    public static int minInsertions(String s) {

        int[][] dp = new int[s.length()][s.length()];

        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return minInsertions(0, s.length()-1, s, dp);
    }

    private static int minInsertions(int s, int e, String str, int[][] dp) {
        if (s >= e) return 0;

        if (dp[s][e] != -1) return dp[s][e];

        if (str.charAt(s) == str.charAt(e)) {
            return dp[s][e] = minInsertions(s+1, e-1, str, dp);
        }

        int insertLeft = 1 + minInsertions(s, e-1, str, dp);
        int insertRight = 1 + minInsertions(s+1, e, str, dp);

        return dp[s][e]= Math.min(insertLeft, insertRight);
    }

    private static int minInsertionsTabulation(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int num = 1; num < s.length(); num++) {
            for (int i=0; i + num<s.length(); i++) {
                int j = i + num;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    int insertLeft = 1 + dp[i][j-1];
                    int insertRight = 1 + dp[i+1][j];
                    dp[i][j]= Math.min(insertLeft, insertRight);
                }
            }
        }

        return dp[0][s.length()-1];
    }
}
