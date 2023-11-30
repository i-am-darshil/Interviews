package DSA.Graphs;

import java.util.*;

// https://leetcode.com/problems/number-of-provinces/description/
public class NumProvinces {
    public static int findCircleNum(int[][] isConnected) {

        int numProvinces = 0;

        boolean[] visited = new boolean[isConnected.length];

        List<List<Integer>> adjList = getAdjList(isConnected);

        for (int i=0; i<visited.length; i++) {
            if (!visited[i]) {
                numProvinces++;
                // dfs(i, visited, isConnected);
                dfs(i, visited, adjList);
            }
        }

        return numProvinces;
    }

    private static void dfs(int node, boolean[] visited, int[][] isConnected) {
        visited[node] = true;

        for (int neighbour=0; neighbour < isConnected[node].length; neighbour++) {
            if (isConnected[node][neighbour] == 1 && !visited[neighbour]) {
                dfs(neighbour, visited, isConnected);
            }
        }
    }

    private static List<List<Integer>> getAdjList(int[][] isConnected) {
        List<List<Integer>> adjList = new ArrayList<>(isConnected.length);

        for (int i = 0; i < isConnected.length; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i=0; i<isConnected.length; i++) {
            for (int j=0; j<isConnected[i].length; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    adjList.get(i).add(j);
                }
            }
        }

        return adjList;
    }

    private static void dfs(int node, boolean[] visited, List<List<Integer>> adjList) {
        visited[node] = true;

        for (int neighbour: adjList.get(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, adjList);
            }
        }
    }
}
