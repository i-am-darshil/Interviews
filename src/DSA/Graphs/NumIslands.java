package DSA.Graphs;

// https://practice.geeksforgeeks.org/problems/find-the-number-of-islands/1
import java.util.*;

public class NumIslands {
    public static int numIslands(char[][] grid) {
        // Code here

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int numIslands = 0;

        int[] directions = new int[] {-1, 0, 1};

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    numIslands++;
                    dfs(i, j, visited, grid, directions);
                }
            }
        }

        return numIslands;
    }

    public static void dfs(int row, int col, boolean[][] visited, char[][] grid, int[] directions) {
        visited[row][col] = true;

        for (int i=0; i<directions.length; i++) {
            for (int j=0; j<directions.length; j++) {
                int neighbourRow = row + directions[i];
                int neighbourCol = col + directions[j];
                if (
                        neighbourRow >= 0 && neighbourRow < grid.length &&
                                neighbourCol >= 0 && neighbourCol < grid[neighbourRow].length &&
                                !visited[neighbourRow][neighbourCol] && grid[neighbourRow][neighbourCol] == '1'
                ) {
                    dfs(neighbourRow, neighbourCol, visited, grid, directions);
                }
            }
        }
    }

    public static int numIslandsWithBFS(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int numIslands = 0;

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    numIslands++;
                    bfs(i, j, grid, visited);
                }
            }
        }

        return numIslands;
    }

    public static void bfs(int row, int col, char[][] grid, boolean[][] visited) {
        Queue<Pair> queue = new LinkedList<>();

        visited[row][col] = true;
        queue.offer(new Pair<>(row,col));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> node = queue.poll();

            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    int newRow = node.first + i;
                    int newCol = node.second + j;

                    if (
                        newRow >= 0 && newRow < grid.length &&
                                newCol >=0 && newCol < grid[newRow].length &&
                                grid[newRow][newCol] == '1' && !visited[newRow][newCol]
                    ) {
                        visited[newRow][newCol] = true;
                        queue.offer(new Pair(newRow,newCol ));
                    }
                }
            }

        }
    }

    private static class Pair<U, V> {
        U first;
        V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }


}
