package DSA.Graphs;

// https://leetcode.com/problems/surrounded-regions/

public class SurroundedRegions {
    public void solve(char[][] board) {
        solveUtil(board);
        for (int i=0; i < board.length; i++) {
            for (int j = 0; j<board[0].length; j++) {
                if (board[i][j] == 'N') board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
    }

    private void solveUtil(char[][] b) {
        boolean[][] visited = new boolean[b.length][b[0].length];
        int[] deltaX = {0, 1, 0, -1};
        int[] deltaY = {1, 0, -1, 0};

        for (int col = 0; col<b[0].length; col++) {
            if (b[0][col] == 'O' && !visited[0][col]) {
                b[0][col] = 'N';
                dfs(0, col, b, visited, deltaX, deltaY);
            }

            if (b[b.length-1][col] == 'O' && !visited[b.length-1][col]) {
                b[b.length-1][col] = 'N';
                dfs(b.length-1, col, b, visited, deltaX, deltaY);
            }
        }

        for (int row = 0; row<b.length; row++) {
            if (b[row][0] == 'O' && !visited[row][0]) {
                b[row][0] = 'N';
                dfs(row, 0, b, visited, deltaX, deltaY);
            }

            if (b[row][b[0].length-1] == 'O' && !visited[row][b[0].length-1]) {
                b[row][b[0].length-1] = 'N';
                dfs(row, b[0].length-1, b, visited, deltaX, deltaY);
            }
        }
    }

    private void dfs(int r, int c, char[][] b, boolean[][] visited, int[] deltaX, int[] deltaY) {
        visited[r][c] = true;

        for (int del = 0; del < deltaX.length; del++) {
            int newR = r + deltaX[del];
            int newC = c + deltaY[del];

            if (
                    newR >=0 && newR < b.length &&
                            newC >=0 && newC < b[0].length &&
                            !visited[newR][newC] && b[newR][newC] == 'O'
            ) {
                b[newR][newC] = 'N';
                dfs(newR, newC, b, visited, deltaX, deltaY);
            }
        }
    }

    // FAIL
    private void solveUtilFail(char[][] b) {
        boolean[][] visited = new boolean[b.length][b[0].length];
        int[] deltaX = new int[] {0, 1, 0, -1};
        int[] deltaY = new int[] {1, 0, -1, 0};


        for (int i=0; i<b.length; i++) {
            for (int j=0; j<b[i].length; j++) {

                if (b[i][j] == 'O' && !visited[i][j]) {
                    tryCaptureFail(b, i, j, visited, deltaX, deltaY);
                }


            }
        }
    }

    // FAIL
    private void tryCaptureFail(char[][] b, int row, int col, boolean[][] visited, int[] deltaX, int[] deltaY) {

        visited[row][col] = true;
        b[row][col] = 'X';

        for (int d = 0; d < deltaX.length; d++) {
            int nRow = row + deltaX[d];
            int nCol = col + deltaY[d];


            if (nRow < 0 || nRow >= b.length || nCol < 0 || nCol >= b[nRow].length) {
                b[row][col] = 'O';
                return;
            }
            else if (b[nRow][nCol] == 'O') {
                if (!visited[nRow][nCol]) {
                    tryCaptureFail(b, nRow, nCol, visited, deltaX, deltaY);
                    if (b[nRow][nCol] == 'O') {
                        b[row][col] = 'O';
                        return;
                    }
                } else {
                    b[row][col] = 'O';
                    return;
                }

            }
        }
    }
}
