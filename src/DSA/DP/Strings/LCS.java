package DSA.DP.Strings;

import java.util.Arrays;
public class LCS {
    public static void main(String args[]) {
        String s1 = "aaaabbbb";
        String s2 = "babababa";

        int[][] dp = new int[s1.length()][s2.length()];

        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }


        System.out.println(lcsUtility(s1, s2, s1.length()-1, s2.length()-1));
        System.out.println(lcsUtilityMemoization(s1, s2, s1.length()-1, s2.length()-1, dp));
        System.out.println(lcsUtilityTabulation(s1, s2));
        System.out.println(lcsUtilityTabulationSpaceOptimized(s1, s2));
        System.out.println(printLcsUtility(s1, s2, s1.length()-1, s2.length()-1));

    }

    private static int lcsUtility(final String s1, final String s2, final int i, final int j) {

        if (i < 0 || j < 0) return 0;

        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsUtility(s1, s2, i-1, j-1);
        }

        return Math.max(lcsUtility(s1, s2, i-1, j), lcsUtility(s1, s2, i, j-1));

    }

    private static int lcsUtilityMemoization(final String s1, final String s2, final int i, final int j, final int[][] dp) {

        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = 1 + lcsUtilityMemoization(s1, s2, i-1, j-1, dp);
        }

        return dp[i][j] = Math.max(lcsUtilityMemoization(s1, s2, i-1, j, dp), lcsUtilityMemoization(s1, s2, i, j-1, dp));

    }

    private static int lcsUtilityTabulation(final String s1, final String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        boolean flag = false;
        for (int j = 0; j < s2.length(); j++) {
            if (s1.charAt(0)== s2.charAt(j) || flag){
                dp[0][j] = 1;
                flag = true;
            }
        }

        flag = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i)== s2.charAt(0) || flag){
                dp[i][0] = 1;
                flag = true;
            }
        }

        for (int i=1; i<s1.length(); i++) {
            for (int j=1; j<s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[s1.length()-1][s2.length()-1];
    }

    private static int lcsUtilityTabulationSpaceOptimized(final String s1, final String s2) {
        int[] prev = new int[s2.length()];

        boolean flag = false;
        for (int j = 0; j < s2.length(); j++) {
            if (s1.charAt(0)== s2.charAt(j) || flag){
                prev[j] = 1;
                flag = true;
            }
        }

        for (int i=1; i<s1.length(); i++) {
            int[] cur = new int[s2.length()];
            cur[0] = s1.charAt(i) == s2.charAt(0) ? 1 + prev[0] : prev[0];

            for (int j=1; j<s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    cur[j] = 1 + prev[j-1];
                } else {
                    cur[j] = Math.max(cur[j-1], prev[j]);
                }
            }

            prev = cur;
        }

        return prev[s2.length()-1];
    }

    private static String printLcsUtility(final String s1, final String s2, final int i, final int j) {

        if (i < 0 || j < 0) return "";

        if (s1.charAt(i) == s2.charAt(j)) {
            return  printLcsUtility(s1, s2, i-1, j-1) + s1.charAt(i);
        }

        String lcsLeft = printLcsUtility(s1, s2, i-1, j);
        String lcsRight = printLcsUtility(s1, s2, i, j-1);

        if (lcsLeft.length() > lcsRight.length()) {
            return lcsLeft;
        } else {
            return lcsRight;
        }

    }
}
