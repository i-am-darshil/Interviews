package DSA.DP.Strings;

// https://leetcode.com/problems/edit-distance/description/

public class EditDistance {
    public int minDistance(String word1, String word2) {

        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        // int[][] dp = new int[word1.length()][word2.length()];

        // for (int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return minDistanceUtility(word1.length() - 1, word2.length() - 1, word1, word2, dp);

        return minDistanceTab(word1, word2);
    }

    private int minDistanceUtility(int i, int j, String w1, String w2, int[][] dp) {

        if (j < 0 && i < 0) return 0;

        if (j < 0) return i+1;

        if (i < 0) return j+1;

        if (dp[i][j] != -1) return dp[i][j];

        if (w1.charAt(i) == w2.charAt(j)) {
            return dp[i][j] =  minDistanceUtility(i-1, j-1, w1, w2, dp);
        }

        int inserted = 1 + minDistanceUtility(i, j-1, w1, w2, dp);
        int minOp = inserted;

        int deleted = 1 + minDistanceUtility(i-1, j, w1, w2, dp);
        minOp = Math.min(minOp, deleted);

        int replaced = 1 + minDistanceUtility(i-1, j-1, w1, w2, dp);
        minOp = Math.min(minOp, replaced);

        return dp[i][j] = minOp;
    }

    private int minDistanceTab(String w1, String w2) {
        int[][] dp = new int[w1.length() + 1][w2.length() + 1];

        for (int i=1; i<=w2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i=1; i<= w1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i=1; i<= w1.length(); i++) {
            for (int j=1; j<=w2.length(); j++) {
                if (w1.charAt(i-1) == w2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {

                    int inserted = 1 + dp[i][j-1];
                    int minOp = inserted;

                    int deleted = 1 + dp[i-1][j];
                    minOp = Math.min(minOp, deleted);

                    int replaced = 1 + dp[i-1][j-1];
                    minOp = Math.min(minOp, replaced);

                    dp[i][j] = minOp;
                }
            }
        }

        return dp[w1.length()][w2.length()];
    }
}
