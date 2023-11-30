package DSA.Graphs.ShortestPathDistFromSource;

public class FloydWarshall {
    public void shortest_distance(int[][] matrix)
    {
        // Code here
        int V = matrix.length;

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = (int) 1e9;
                }
            }
        }

        for (int via = 0; via < V; via++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    int newDist = matrix[i][via] + matrix[via][j];

                    if (newDist < matrix[i][j]) {
                        matrix[i][j] = newDist;
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (matrix[i][j] == (int) 1e9) {
                    matrix[i][j] = -1;
                }
            }
        }
    }
}
