package DSA.Graphs;

// https://leetcode.com/problems/number-of-enclaves/description/

public class NumberFfEnclaves {
    public int numEnclaves(int[][] grid) {
        return numEnclavesUtil(grid);
    }

    private int numEnclavesUtil(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] deltaX = {0, 1, 0, -1};
        int[] deltaY = {1, 0, -1, 0};

        boolean[][] visited = new boolean[n][m];

        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1 && !visited[0][j]) {
                dfs(0, j, grid, visited, deltaX, deltaY);
            }

            if (grid[n-1][j] == 1 && !visited[n-1][j]) {
                dfs(n-1, j, grid, visited, deltaX, deltaY);
            }
        }

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1 && !visited[i][0]) {
                dfs(i, 0, grid, visited, deltaX, deltaY);
            }

            if (grid[i][m-1] == 1 && !visited[i][m-1]) {
                dfs(i, m-1, grid, visited, deltaX, deltaY);
            }
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int r, int c, int[][] grid, boolean[][] visited, int[] deltaX, int[] deltaY) {
        int n = grid.length;
        int m = grid[0].length;

        visited[r][c] = true;

        for (int i=0; i<deltaX.length; i++) {
            int nR = r + deltaX[i];
            int nC = c + deltaY[i];

            if (
                    nR >= 0 && nR < n &&
                            nC >= 0 && nC < m &&
                            !visited[nR][nC] && grid[nR][nC] == 1
            ) {
                dfs(nR, nC, grid, visited, deltaX, deltaY);
            }
        }
    }
}
