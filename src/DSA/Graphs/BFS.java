package DSA.Graphs;

import java.util.*;

public class BFS {

    public static List<Integer> BFS(int V, List<List<Integer>> adj) {
        List<Integer> bfs = new LinkedList<>();

        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[adj.size()];

        queue.offer(V);
        visited[V] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            bfs.add(node);

            for (int n: adj.get(node)) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.offer(n);
                }
            }
        }

        return bfs;
    }
}
