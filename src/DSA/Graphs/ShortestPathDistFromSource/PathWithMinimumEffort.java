package DSA.Graphs.ShortestPathDistFromSource;

import java.util.*;

public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        return minimumEffortPathUtil(heights);
    }

    private int minimumEffortPathUtil(int[][] heights) {
        int N = heights.length;
        int M = heights[0].length;

        int[][] effort = new int[N][M];

        int[] deltaX = {0, 1, 0, -1};
        int[] deltaY = {1, 0, -1, 0};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                effort[i][j] = (int) Math.pow(10, 9);
            }
        }

        effort[0][0] = 0;

        Queue<Tuple> queue = new PriorityQueue<>((t1, t2) -> {
            return t1.effort - t2.effort;
        });

        queue.offer(new Tuple(0, 0, 0));

        int minEffortReq = (int) Math.pow(10, 9);

        while (!queue.isEmpty()) {
            Tuple currNode = queue.poll();
            int currRow = currNode.row;
            int currCol = currNode.col;
            int effortSoFar = currNode.effort;

            if (currRow == N-1 && currCol == M-1) {
                minEffortReq = Math.min(effortSoFar, minEffortReq);
                // continue;
                break;
            }

            for (int d = 0; d < deltaX.length; d++) {
                int newRow = currRow + deltaX[d];
                int newCol = currCol + deltaY[d];
                if (
                        newRow >= 0 && newRow < N &&
                                newCol >= 0 && newCol < M
                ) {
                    int absDiff = Math.abs(heights[newRow][newCol] - heights[currRow][currCol]);
                    int newEffort = Math.max(effortSoFar, absDiff);
                    if (newEffort < effort[newRow][newCol]) {
                        effort[newRow][newCol] = newEffort;
                        queue.offer(new Tuple(newRow, newCol, newEffort));
                    }
                }

            }

        }

        if (minEffortReq >= (int) Math.pow(10, 9)) {
            return -1;
        } else {
            return minEffortReq;
        }

    }



    private class Tuple {
        int row;
        int col;
        int effort;

        public Tuple(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
    }
}
