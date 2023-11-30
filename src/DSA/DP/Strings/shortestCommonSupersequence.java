package DSA.DP.Strings;

import java.util.*;

public class shortestCommonSupersequence {

    public static void main(String[] args) {
        String s1 = "nbvdsssjdw";
        String s2 = "kkjumxxtntso";

        String ans = PrintLCSUtilityTabulation(s1, s2);
        System.out.println(ans + " " + ans.length());

    }

    public static String shortestCommonSupersequence(String str1, String str2) {


//        if (str1.length() > str2.length()) {
//            String[][] dp = new String[str1.length()][str2.length()];
//            for (String[] arr: dp) {
//                Arrays.fill(arr, "-1");
//            }
//
//            return shortestCommonSupersequenceUtilMemoize(str1.length() - 1, str2.length() - 1, str1, str2, dp);
//        } else {
//            String[][] dp = new String[str1.length()][str2.length()];
//            for (String[] arr: dp) {
//                Arrays.fill(arr, "-1");
//            }
//
//            return shortestCommonSupersequenceUtilMemoize(str2.length() - 1, str1.length() - 1, str2, str1, dp);
//        }

        String[][] dp = new String[str1.length()][str2.length()];
        for (String[] arr: dp) {
            Arrays.fill(arr, "-1");
        }

        return shortestCommonSupersequenceUtilMemoize2(str1.length() - 1, str2.length() - 1, str1, str2, dp);



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
                    sb.append(s1.charAt(i-1));
                    i--;
                } else {
                    sb.append(s2.charAt(j-1));
                    j--;
                }
            }
        }


//        while(i>0){
//            sb.append(s1.charAt(i-1));
//            i--;
//        }
//        while(j>0){
//            sb.append(s2.charAt(j-1));
//            j--;
//        }


        if (i!=j) {
            if (i > 0) {
                StringBuilder reversed = new StringBuilder(s1.substring(0, i)).reverse();
                sb.append(reversed);
            }
            else if (j > 0) {
                StringBuilder reversed = new StringBuilder(s2.substring(0, j)).reverse();
                sb.append(reversed);
            }
        }

        return sb.reverse().toString();
    }

    // Approach tried
    // s1 - Bigger String, s2 - Smaller String
    // Try to convert s2 -> s1

    // DOES NOT WORK - We are trying to create one string like another. In process of doing so, we are creating s1 + s2 sort of.
    // There might be common subsequences, which gets missed out and we still add the character
    // Eg. s1 = "brute" s2 = "rueb"
    private static String shortestCommonSupersequenceUtil(int i, int j, String s1, String s2) {

        if (j < 0) return s1;

        if (i < 0) return s2.substring(0, j+1) + s1;

        if (s1.charAt(i) == s2.charAt(j)) {
            return shortestCommonSupersequenceUtil(i-1, j-1, s1, s2);
        }

        String newStringAfterInsert = s1.substring(0, i+1) + s2.charAt(j);

        if (i+1 < s1.length()) {
            newStringAfterInsert += s1.substring(i+1, s1.length());
        }
        String insert = shortestCommonSupersequenceUtil(i, j-1, newStringAfterInsert, s2);

        String notInsert = shortestCommonSupersequenceUtil(i-1, j, s1, s2);

        if (insert.length() < notInsert.length()) {
            return insert;
        } else {
            return notInsert;
        }
    }

    // DOES NOT WORK - We are trying to create one string like another. In process of doing so, we are creating s1 + s2 sort of.
    // There might be common subsequences, which gets missed out and we still add the character
    // Eg. s1 = "brute" s2 = "rueb"
    private static String shortestCommonSupersequenceUtilMemoize(int i, int j, String s1, String s2, String[][] dp) {

        if (j < 0) return s1;

        if (i < 0) return s2.substring(0, j+1) + s1;

        if (!dp[i][j].equals("-1")) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = shortestCommonSupersequenceUtilMemoize(i-1, j-1, s1, s2, dp);
        }

        String newStringAfterInsert = s1.substring(0, i+1) + s2.charAt(j);

        if (i+1 < s1.length()) {
            newStringAfterInsert += s1.substring(i+1, s1.length());
        }
        String insert = shortestCommonSupersequenceUtilMemoize(i, j-1, newStringAfterInsert, s2, dp);

        String notInsert = shortestCommonSupersequenceUtilMemoize(i-1, j, s1, s2, dp);

        if (insert.length() < notInsert.length()) {
            return dp[i][j] = insert;
        } else {
            return dp[i][j] = notInsert;
        }
    }

    // DOES NOT WORK - We are trying to create one string like another. In process of doing so, we are creating s1 + s2 sort of.
    // There might be common subsequences, which gets missed out and we still add the character
    // Eg. s1 = "brute" s2 = "rueb"
    private static String shortestCommonSupersequenceUtilMemoize2(int i, int j, String s1, String s2, String[][] dp) {

        if (i < 0 && j < 0) {
            if (s1.length() < s2.length()) return s1;
            else return s2;
        }

        if (j < 0) return s1.substring(0, i+1) + s2;

        if (i < 0) return s2.substring(0, j+1) + s1;

        if (!dp[i][j].equals("-1")) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = shortestCommonSupersequenceUtilMemoize(i-1, j-1, s1, s2, dp);
        }

        String newStringAfterInsertAt1 = s1.substring(0, i+1) + s2.charAt(j);

        if (i+1 < s1.length()) {
            newStringAfterInsertAt1 += s1.substring(i+1, s1.length());
        }
        String insertAt1 = shortestCommonSupersequenceUtilMemoize(i, j-1, newStringAfterInsertAt1, s2, dp);

        String newStringAfterInsertAt2 = s2.substring(0, j+1) + s1.charAt(i);

        if (j+1 < s2.length()) {
            newStringAfterInsertAt2 += s2.substring(j+1, s2.length());
        }
        String insertAt2 = shortestCommonSupersequenceUtilMemoize(i-1, j, s1, newStringAfterInsertAt2, dp);

        if (insertAt1.length() < insertAt2.length()) {
            return dp[i][j] = insertAt1;
        } else {
            return dp[i][j] = insertAt2;
        }
    }
}
