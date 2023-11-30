package DSA.DP.Grid2D;

//https://leetcode.com/problems/unique-paths-ii/description/

public class GridUniquePaths {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        // int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        // for (int[] arr: dp) {
        //     Arrays.fill(arr, -1);
        // }
        // return uniquePathsWithObstaclesUtility(obstacleGrid.length - 1, obstacleGrid[0].length - 1, obstacleGrid, dp);

        // return uniquePathsWithObstaclesUtilityTabulation(obstacleGrid, dp);
        return uniquePathsWithObstaclesUtilityTabulationOptimized(obstacleGrid);

    }

    private int uniquePathsWithObstaclesUtility(int row, int col, int[][] obstacleGrid, int[][] dp) {
        if (obstacleGrid[row][col] == 1) return 0;
        if (row == 0 && col == 0) return 1;
        if (dp[row][col] != -1) return dp[row][col];
        int left = 0;
        int up = 0;

        if (col - 1 >= 0) {
            left = uniquePathsWithObstaclesUtility(row, col - 1, obstacleGrid, dp);
        }

        if (row - 1 >= 0) {
            up = uniquePathsWithObstaclesUtility(row - 1, col, obstacleGrid, dp);
        }

        return dp[row][col] = up + left;
    }

    private int uniquePathsWithObstaclesUtilityTabulation(int[][] obstacleGrid, int[][] dp) {
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i==0 && j==0) {
                    dp[0][0] = 1;
                } else {
                    int left = 0;
                    int up = 0;

                    if (j - 1 >= 0) {
                        left = dp[i][j-1];
                    }

                    if (i - 1 >= 0) {
                        up = dp[i-1][j];
                    }
                    dp[i][j] = left + up;
                }
            }
        }
        return dp[obstacleGrid.length-1][obstacleGrid[0].length - 1];
    }

    private int uniquePathsWithObstaclesUtilityTabulationOptimized(int[][] obstacleGrid) {
        int[] prev = new int[obstacleGrid[0].length];

        for (int i = 0; i < obstacleGrid.length; i++) {
            int[] cur = new int[obstacleGrid[0].length];
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    cur[j] = 0;
                } else if (i==0 && j==0) {
                    cur[0] = 1;
                } else {
                    int left = 0;
                    int up = 0;

                    if (j - 1 >= 0) {
                        left = cur[j-1];
                    }

                    if (i - 1 >= 0) {
                        up = prev[j];
                    }
                    cur[j] = left + up;
                }
            }
            prev = cur;
        }
        return prev[obstacleGrid[0].length - 1];
    }
}