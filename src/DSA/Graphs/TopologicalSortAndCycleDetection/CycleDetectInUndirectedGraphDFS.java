package DSA.Graphs.TopologicalSortAndCycleDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectInUndirectedGraphDFS {

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] visited = new boolean[V];

        for (int i=0; i<V; i++) {
            if (!visited[i]) {
                boolean isCycle = DFSCycleCheck(i, visited, adj, -1);
                if (isCycle) return true;
            }
        }
        return false;
    }

    public boolean DFSCycleCheck(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, int parent) {

        visited[node] = true;

        for (int neighbour: adj.get(node)) {
            if (!visited[neighbour]) {
                boolean hasCycle = DFSCycleCheck(neighbour, visited, adj, node);
                if (hasCycle) return true;
            } else {
                if (neighbour != parent) return true;
            }
        }

        return false;

    }



}
