package DSA.Graphs.ShortestPathDistFromSource;

import java.util.*;

public class Dijkstra {
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        // Write your code here
        return dijkstraUtil(V, adj, S);
    }

    private static int[] dijkstraUtil(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        Queue<Pair> queue = new PriorityQueue<>(Comparator.comparing(i -> i.dist, (i1, i2) -> {
            return i1 - i2;
        }));

        int[] dist = new int[V];

        Arrays.fill(dist, (int) Math.pow(10, 9));

        dist[S] = 0;

        queue.offer(new Pair(S, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int currNode = curr.node;
            int currDist = curr.dist;


            for (List<Integer> neighbour: adj.get(currNode)) {
                int neighbourNode = neighbour.get(0);
                int neighbourWt = neighbour.get(1);

                if (currDist + neighbourWt < dist[neighbourNode]) {
                    dist[neighbourNode] = currDist + neighbourWt;
                    queue.offer(new Pair(neighbourNode, dist[neighbourNode]));
                }
            }

        }

        for (int i = 0; i < V; i++) {
            if (dist[i] >= (int) Math.pow(10,9)) {
                dist[i] = -1;
            }
        }

        return dist;
    }

    private static class Pair {
        int dist;
        int node;

        public Pair(int node, int dist) {
            this.dist = dist;
            this.node = node;
        }
    }
}
