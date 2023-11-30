package DSA.Graphs.ShortestPathDistFromSource;

import java.util.*;

public class PrintShortestPathinWeightedUndirectedGraph {
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {
        // code here
        return shortestPathUtil(n, m, edges);
    }

    private static class Pair {
        int node;
        int distSoFar;

        public Pair (int node, int distSoFar) {
            this.node = node;
            this.distSoFar = distSoFar;
        }
    }

    private static List<Integer> shortestPathUtil(int n, int m, int edges[][]) {
        List<List<List<Integer>>> adjList = new ArrayList<>();
        generateAdjList(n, m, edges, adjList);

        Queue<Pair> pq = new PriorityQueue<>(Comparator.comparing(item -> item.distSoFar, (i1, i2) -> {
            return i1 - i2;
        }));

        int source = 1;

        int[] dist = new int[n + 1];

        Arrays.fill(dist, (int) Math.pow(10, 9));

        dist[source] = 0;

        List<Integer> ans = new LinkedList<>();

        Map<Integer, Integer> parentMap = new HashMap<>();

        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair nodeProp = pq.poll();

            int node = nodeProp.node;
            int distSoFar = nodeProp.distSoFar;

            for (List<Integer> neighbourProp: adjList.get(node)) {
                int neighbour = neighbourProp.get(0);
                int weight = neighbourProp.get(1);

                if (distSoFar + weight < dist[neighbour]) {
                    dist[neighbour] = distSoFar + weight;
                    parentMap.put(neighbour, node);
                    pq.offer(new Pair(neighbour, dist[neighbour]));
                }
            }

        }

        if (dist[n] >= (int) Math.pow(10, 9)) {
            ans.add(-1);
            return ans;
        }

        int dest = n;
        while (true) {
            ans.add(dest);
            if (dest == 1) {
                break;
            }
            int parent = parentMap.get(dest);
            dest = parent;
        }


        reverse(ans);

        return ans;
    }

    private static void reverse(List<Integer> l) {
        int N = l.size();
        for (int i = 0; i < N/2; i++) {
            int temp = l.get(i);
            l.set(i, l.get(N-1-i));
            l.set(N-1-i, temp);
        }
    }

    private static void generateAdjList(int n, int m, int edges[][], List<List<List<Integer>>> adjList) {
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            int weight = edges[i][2];

            List<Integer> edge1 = new ArrayList<>();
            edge1.add(end);
            edge1.add(weight);

            List<Integer> edge2 = new ArrayList<>();
            edge2.add(start);
            edge2.add(weight);

            adjList.get(start).add(edge1);
            adjList.get(end).add(edge2);
        }
    }
}
