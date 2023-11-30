package DSA.Graphs;

import java.util.*;

public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        return updateMatrixUtil(mat);
    }

    private int[][] updateMatrixUtil(int[][] mat) {

        Queue<Node> queue = new LinkedList<>();
        int[][] ans = new int[mat.length][mat[0].length];
        int[] deltaX = new int[] {0, 1, 0, -1};
        int[] deltaY = new int[] {1, 0, -1, 0};
        boolean[][] visited = new boolean[mat.length][mat[0].length];

        for (int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int dist = 0;

        while (!queue.isEmpty()) {
            int numNodesInLevel = queue.size();

            for (int i=0; i<numNodesInLevel; i++) {
                Node node = queue.poll();
                ans[node.row][node.col] = dist;

                for (int d=0; d<deltaX.length; d++) {
                    int newRow = node.row + deltaX[d];
                    int newCol = node.col + deltaY[d];

                    if (newRow >= 0 && newRow < mat.length &&
                            newCol >= 0 && newCol < mat[newRow].length &&
                            visited[newRow][newCol] == false
                    ) {
                        visited[newRow][newCol] = true;
                        queue.offer(new Node(newRow, newCol));
                    }
                }
            }

            dist++;
        }

        return ans;
    }

    private class Node {
        int row;
        int col;

        public Node(int r, int c) {
            row = r;
            col = c;
        }
    }
}
