package DSA.Graphs.ShortestPathDistFromSource;

import java.util.*;

public class BellmanFord {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        // Write your code here

        int[] dist = new int[V];

        Arrays.fill(dist, (int) Math.pow(10, 8));

        dist[S] = 0;

        for (int i = 0; i < V-1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                int from = edges.get(j).get(0);
                int to = edges.get(j).get(1);
                int weight = edges.get(j).get(2);

                int newDis = dist[from] + weight;

                if (newDis < dist[to]) {
                    dist[to] = newDis;
                }
            }
        }

        for (int j = 0; j < edges.size(); j++) {
            int from = edges.get(j).get(0);
            int to = edges.get(j).get(1);
            int weight = edges.get(j).get(2);

            int newDis = dist[from] + weight;

            if (newDis < dist[to]) {
                return new int[] {-1};
            }
        }

        return dist;
    }
}
