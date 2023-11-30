package DSA.Graphs.TopologicalSortAndCycleDetection;

import java.util.*;

public class CycleDetectInUndirectedGraphBFS {

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] visited = new boolean[V];

        for (int i=0; i<V; i++) {
            if (!visited[i]) {
                boolean isCycle = BFSCycleCheck(i, visited, adj);
                if (isCycle) return true;
            }
        }
        return false;
    }

    public boolean BFSCycleCheck(int start, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair<>(start, -1));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> node = queue.poll();
            int value = node.first;
            int parent = node.second;

            for (int neighbour: adj.get(node.first)) {
                if (!visited[neighbour]) {
                    queue.offer(new Pair<>(neighbour, value));
                    visited[neighbour] = true;
                } else {
                    if (neighbour != parent) return true;
                }
            }
        }

        return false;

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
