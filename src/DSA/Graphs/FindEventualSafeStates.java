package DSA.Graphs;
import java.util.*;
// https://leetcode.com/problems/find-eventual-safe-states/description/

public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // return eventualSafeNodesUtil(graph);
        return eventualSafeNodesTopo(graph);
    }

    private List<Integer>  eventualSafeNodesTopo(int[][] graph) {
        int N = graph.length;
        List<Integer> ans = new ArrayList<>();
        if (N == 0) return ans;

        List<List<Integer>> reverseEdgeMap = new ArrayList<>();
        int[] reverseIndegrees = new int[N];

        for (int i = 0; i < N; i++) {
            reverseEdgeMap.add(new ArrayList<>());
        }

        generateReverseEdgeMap(graph, reverseEdgeMap, N, reverseIndegrees);
        boolean[] isSafe = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (reverseIndegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            isSafe[node] = true;

            for (int neighbour: reverseEdgeMap.get(node)) {
                reverseIndegrees[neighbour]--;
                if (reverseIndegrees[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }


        for (int i = 0; i < N; i++) {
            if (isSafe[i]) {
                ans.add(i);
            }
        }
        return ans;

    }

    private void generateReverseEdgeMap(int[][] graph, List<List<Integer>> reverseEdgeMap, int N, int[] reverseIndegrees) {
        for (int i = 0; i < N; i++) {
            for (int neighbourInd = 0; neighbourInd < graph[i].length; neighbourInd++) {
                int neighbour = graph[i][neighbourInd];
                reverseEdgeMap.get(neighbour).add(i);
                reverseIndegrees[i]++;
            }
        }
    }



    private List<Integer> eventualSafeNodesUtil(int[][] graph) {

        int n = graph.length;
        boolean[] visited = new boolean[n];

        int[] isSafe = new int[n];
        Arrays.fill(isSafe, -1);

        List<Integer> ans = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, isSafe);
            }
        }

        for (int i = 0; i<n; i++) {
            if (isSafe[i] == 1) {
                ans.add(i);
            }
        }

        return ans;

    }

    private void dfs(int node, int[][] graph, boolean[] visited, int[] isSafe) {

        visited[node] = true;

        if (graph[node].length == 0) {
            isSafe[node] = 1;
            return;
        }

        int isSafeNode = 1;

        for (int neighbour: graph[node]) {

            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited, isSafe);
                isSafeNode = isSafe[neighbour];
                if (isSafeNode == 0) break;
            } else {
                if (isSafe[neighbour] == -1) {
                    isSafeNode = 0;
                    break;
                } else {
                    isSafeNode = isSafe[neighbour];
                    if (isSafeNode == 0) {
                        break;
                    }
                }
            }

        }

        isSafe[node] = isSafeNode;
    }
}
