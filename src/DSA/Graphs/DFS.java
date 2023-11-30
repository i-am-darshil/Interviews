package DSA.Graphs;

import java.util.*;

public class DFS {
    public static List<Integer> dfs(int V, List<List<Integer>> adj) {

        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[V];
        dfs(0, ans, visited, adj);

        return ans;

    }

    private static void dfs(int node, List<Integer> ans, boolean[] visited, List<List<Integer>> adj) {

        visited[node] = true;
        ans.add(node);

        for (int n: adj.get(node)) {
            if (!visited[n]) {
                dfs(n, ans, visited, adj);
            }
        }
    }
}
