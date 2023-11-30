package DSA.Graphs;

import java.util.*;

public class NumberofDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Set<String> ans = new HashSet();
        int[] deltaX = new int[] {0, 1, 0, -1};
        int[] deltaY = new int[] {1, 0, -1, 0};

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    List<List<Integer>> coords = new LinkedList<>();
                    dfs(i, j, grid, visited, i, j, coords, deltaX, deltaY);
                    // System.out.println(coords);
                    ans.add(coords.toString());
                }
            }
        }

        return ans.size();
    }

    private void dfs(
            int x, int y, int[][] grid, boolean[][] visited, int startx, int starty, List<List<Integer>> coords,
            int[] deltaX, int[] deltaY
    ) {

        int n = grid.length;
        int m = grid[0].length;

        List<Integer> c = Arrays.asList(x - startx, y - starty);
        coords.add(c);
        visited[x][y] = true;

        for (int i=0; i<deltaX.length; i++) {
            int nx = x + deltaX[i];
            int ny = y + deltaY[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && grid[nx][ny] == 1) {
                dfs(nx, ny, grid, visited, startx, starty, coords, deltaX, deltaY);
            }
        }

    }
}
