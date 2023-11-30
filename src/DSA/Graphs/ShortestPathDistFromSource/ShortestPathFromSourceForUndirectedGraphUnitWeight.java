package DSA.Graphs.ShortestPathDistFromSource;

// https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1


/* Why use not use topo sort here ??
Because toposort can only exists in DAG.
*/

import java.util.*;
public class ShortestPathFromSourceForUndirectedGraphUnitWeight {
    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
        // Code here
        return shortestPathUtil(edges, n, m, src);
    }

    private int[] shortestPathUtil(int[][] edges,int n,int m ,int src) {

        List<List<Integer>> adjList = new ArrayList<>();

        generateAdjList(edges, n, m, adjList);

        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[n];
        Arrays.fill(dist, (int) Math.pow(10, 9));

        dist[src] = 0;

        queue.offer(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbour: adjList.get(node)) {

                if (dist[node] + 1 < dist[neighbour]) {
                    dist[neighbour] = dist[node] + 1;
                    queue.offer(neighbour);
                }

            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] >= (int)Math.pow(10, 9)) {
                dist[i] = -1;
            }
        }

        return dist;
    }

    private void generateAdjList(int[][] edges,int n,int m, List<List<Integer>> adjList) {
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];

            adjList.get(node1).add(node2);
            adjList.get(node2).add(node1);
        }
    }
}
