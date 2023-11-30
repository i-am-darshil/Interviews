package DSA.Graphs;

import java.util.*;

public class   FloodFill {
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;
        int ogColor = image[sr][sc];
        int[] deltaRow = new int[] {-1, 0, 1, 0};
        int[] deltaCol = new int[] {0, 1, 0, -1};

        // in case you want to copy - start
        int[][] ans = new int[image.length][image[0].length];

        for (int i = 0; i<ans.length; i++) {
            ans[i] = Arrays.copyOf(image[i], image[i].length);
        }
        // in case you want to copy - end

        dfs(sr, sc, color, image, ogColor, deltaRow, deltaCol);
        return image;
    }

    public static void dfs(int row, int col, int color, int[][] image, int ogColor, int[] deltaRow, int[] deltaCol) {
        image[row][col] = color;

        // int flag = 1;
        // for (int i=0; i<=1; i++) {
        //     for (int j=0; j<=1; j++) {
        //         flag = flag * -1;
        //         int newRow = row + i*flag;
        //         int newCol = col + (1-i)*flag;

        //         if (
        //             newRow >= 0 && newRow < image.length &&
        //             newCol >= 0 && newCol < image[newRow].length &&
        //             image[newRow][newCol] == ogColor
        //         ) {
        //             dfs(newRow, newCol, color, image, ogColor);
        //         }
        //     }
        // }

        for (int i=0; i<deltaRow.length; i++) {
            int newRow = row + deltaRow[i];
            int newCol = col + deltaCol[i];

            if (
                    newRow >= 0 && newRow < image.length &&
                            newCol >= 0 && newCol < image[newRow].length &&
                            image[newRow][newCol] == ogColor
            ) {
                dfs(newRow, newCol, color, image, ogColor, deltaRow, deltaCol);
            }
        }
    }
}
