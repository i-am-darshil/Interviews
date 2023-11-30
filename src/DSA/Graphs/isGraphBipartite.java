package DSA.Graphs;
import java.util.*;
public class isGraphBipartite {
    public boolean isBipartite(int[][] graph) {

        return isBipartiteUtil(graph);

        // return isBipartiteDFS(graph);
    }

    private boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;

        int[] nodeColors = new int[n];

        boolean canPartition = true;

        for (int i=0; i<n; i++) {
            if (nodeColors[i] == 0) {
                canPartition = canPartition && dfs(i, nodeColors, 1, graph);
                if (!canPartition) return canPartition;
            }

        }

        return canPartition;

    }

    private boolean dfs(int node, int[] nodeColors, int curColor, int[][] graph) {

        nodeColors[node] = curColor;
        boolean canPartition;

        for (int neighbour: graph[node]) {
            if (nodeColors[neighbour] == curColor) {
                return false;
            }

            if (nodeColors[neighbour] == 0) {
                canPartition = dfs(neighbour, nodeColors, curColor % 2 + 1, graph);
                if (!canPartition) return false;
            }
        }

        return true;

    }



    private  boolean isBipartiteUtil(int[][] graph) {

        int n = graph.length;

        int[] nodeColors = new int[n];

        boolean canPartition = true;

        for (int i=0; i<n; i++) {
            if (nodeColors[i] == 0) {
                canPartition = canPartition && bfs(i, nodeColors, 1, graph);
                if (!canPartition) return canPartition;
            }

        }

        return canPartition;
    }

    private boolean bfs(int start, int[] nodeColors, int startColor, int[][]graph) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(start, startColor));
        nodeColors[start] = startColor;

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            int val = n.val;
            int color = n.color;

            for (int i: graph[val]) {
                if (nodeColors[i] == color) {
                    return false;
                }
                if (nodeColors[i] == 0) {
                    nodeColors[i] = color % 2 + 1;
                    queue.offer(new Node(i, color % 2 + 1));
                }
            }
        }

        return true;
    }


    private class Node {
        int color;
        int val;

        public Node (int v, int c) {
            color = c;
            val = v;
        }
    }
}
