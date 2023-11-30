package DSA.DP.Strings;

import java.util.*;
public class PrintLCS {
    public static void main (String[] args) {
        String s1 = "aaaabbbba";
        String s2 = "babababa";

        String[][] dp = new String[s1.length()][s2.length()];

        for (String[] arr: dp) {
            Arrays.fill(arr, "-1");
        }

        System.out.println(PrintLCSUtility(s1, s2, s1.length()-1, s2.length()-1, dp));

        System.out.println(PrintLCSUtilityTabulation(s1, s2));
    }

    private static String PrintLCSUtility(String s1, String s2, int i, int j, String[][] dp) {
        if (i < 0 || j < 0) return "";

        if (!dp[i][j].equals("-1")) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = PrintLCSUtility(s1, s2, i-1, j-1, dp) + s1.charAt(i);
        }

        String takeFirst = PrintLCSUtility(s1, s2, i-1, j, dp);

        String takeSecond = PrintLCSUtility(s1, s2, i, j-1, dp);

        if (takeFirst.length() > takeSecond.length()) {
            return dp[i][j] = takeFirst;
        } else {
            return dp[i][j] = takeSecond;
        }

    }

    private static String PrintLCSUtilityTabulation(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for (int i= 1; i<=s1.length(); i++) {
            for( int j=1; j<=s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    int left = dp[i][j-1];
                    int up = dp[i-1][j];
                    dp[i][j] = Math.max(left, up);
                }
            }
        }

        System.out.println(dp[s1.length()][s2.length()]);

        StringBuilder sb = new StringBuilder();

        int i = s1.length();
        int j = s2.length();

        while (i > 0 && j > 0 ) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            } else {
                if (dp[i-1][j] > dp[i][j-1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return sb.reverse().toString();
    }
}
