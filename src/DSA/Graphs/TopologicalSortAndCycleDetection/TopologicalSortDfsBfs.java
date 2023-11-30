package DSA.Graphs.TopologicalSortAndCycleDetection;

// https://practice.geeksforgeeks.org/problems/topological-sort

import java.util.*;
public class TopologicalSortDfsBfs {
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // add your code here
        int[] topoOrder = new int[V];
        // topoSortUtil(V, adj, topoOrder);

        topoSortUtilBFS(V, adj, topoOrder);
        return topoOrder;
    }

    // Kahn's Algorithm
    private static void topoSortUtilBFS(int n, ArrayList<ArrayList<Integer>> adj, int[] topoOrder) {

        int[] indegrees = new int[n];
        Queue<Integer> queue = new LinkedList<>();

        generateIndegrees(n, adj, indegrees);

        for (int i = 0; i<n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            topoOrder[index] = node;
            index++;

            for (int neighbour: adj.get(node)) {
                if (indegrees[neighbour] <= 0) continue; // If this was true, it means it is a CYCLE

                indegrees[neighbour]--;
                if (indegrees[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

    }

    private static void generateIndegrees(int n, ArrayList<ArrayList<Integer>> adj, int[] indegrees) {
        for (int i = 0; i<n; i++) {
            for (int neighbour: adj.get(i)) {
                indegrees[neighbour]++;
            }
        }
    }

    private static void topoSortUtil(int n, ArrayList<ArrayList<Integer>> adj, int[] topoOrder) {


        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i<n; i++) {
            if (!visited[i]) {
                dfs(i, visited, adj, stack);
            }
        }

        for (int i=0; i<n; i++) {
            topoOrder[i] = stack.pop();
        }


    }

    private static void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {

        visited[node] = true;

        for (int neighbour: adj.get(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, adj, stack);
            }
        }

        stack.push(node);
    }
}
