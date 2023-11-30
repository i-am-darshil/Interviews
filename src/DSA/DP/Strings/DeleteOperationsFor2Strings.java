package DSA.DP.Strings;

// https://leetcode.com/problems/delete-operation-for-two-strings/description/
import java.util.*;

public class DeleteOperationsFor2Strings {
    public static int minDistance(String word1, String word2) {

        // int[][] dp = new int[word1.length()][word2.length()];

        // for (int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return minDistanceUtility(word1, word2, word1.length() - 1, word2.length()-1, dp);
        return minDistanceUtilityTab(word1, word2);
    }

    private static int minDistanceUtility(String s1, String s2, int i, int j, int[][] dp) {

        // if (s1.substring(0, i+1).equals(s2.substring(0, j+1))) return 0;

        if (i < 0 && j < 0) return 0;
        if (i < 0) return j+1;
        if (j < 0) return i+1;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = minDistanceUtility(s1, s2, i-1, j-1, dp);
        }

        int delLeft = 1 + minDistanceUtility(s1, s2, i-1, j, dp);
        int delRight = 1 + minDistanceUtility(s1, s2, i, j-1, dp);

        return dp[i][j] = Math.min(delLeft, delRight);
    }

    private static int minDistanceUtilityTab(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for (int i=1; i<= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j=1; j<= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i=1; i <= s1.length(); i++) {
            for (int j=1; j<= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int delLeft = 1 + dp[i-1][j];
                    int delRight = 1 + dp[i][j-1];
                    dp[i][j] = Math.min(delLeft, delRight);
                }
            }
        }

        return dp[s1.length()][s2.length()];


    }
}
