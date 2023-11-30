package DSA.Graphs.ShortestPathDistFromSource;

import java.util.*;

public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // return findCheapestPriceUtil(n, flights, src, dst, k);
        return findCheapestPriceUtilApproach2(n, flights, src, dst, k);

    }

    private int findCheapestPriceUtilApproach2(int n, int[][] flights, int src, int dst, int k){
        List<List<Pair>> adjList = new ArrayList<>();

        generateAdjList(n, flights, adjList);

        Queue<FlightProps> queue = new LinkedList<>();

        queue.offer(new FlightProps(src, 0));

        int[] costFromSrc = new int[n];

        Arrays.fill(costFromSrc, (int) Math.pow(10, 9));

        costFromSrc[src] = 0;

        int minCost = (int) Math.pow(10, 9);

        int stops = 0;
        while (!queue.isEmpty()) {

            int numNodesAtCurrStop = queue.size();


            for (int i = 0; i < numNodesAtCurrStop; i++) {

                FlightProps currProps = queue.poll();
                int currNode = currProps.node;
                // int stopNumber = currProps.stopNumber;
                int costSoFar = currProps.costSoFar;

                if (currNode == dst) {
                    continue;
                }

                for (Pair neighbourPair: adjList.get(currNode)) {
                    int neighbourNode = neighbourPair.node;
                    int neighbourCost = neighbourPair.cost;

                    if (stops < k || (stops == k && neighbourNode == dst)) {
                        int newCost = costSoFar + neighbourCost;
                        if (newCost < costFromSrc[neighbourNode]) {
                            costFromSrc[neighbourNode] = newCost;
                            queue.offer(new FlightProps(neighbourNode, newCost));
                        }
                    }
                }

            }

            stops++;

        }

        if (costFromSrc[dst] >= (int) Math.pow(10, 9)) return -1;
        return costFromSrc[dst];


    }

    private class FlightProps {
        int node;
        // int stopNumber;
        int costSoFar;

        public FlightProps (int node, int costSoFar) {
            this.node = node;
            // this.stopNumber = stopNumber;
            this.costSoFar = costSoFar;
        }
    }

    // This won't work bcoz if we will update the min distances for paths, and then those paths cannot be used because we exhauted k, we will not be able to backtrack that.
    private int findCheapestPriceUtil(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair>> adjList = new ArrayList<>();

        generateAdjList(n, flights, adjList);

        Queue<Tuple> queue = new PriorityQueue<>((t1, t2) -> {
            return t1.costFromStart - t2.costFromStart;
        });

        queue.offer(new Tuple(src, 0, k+1));

        int[] cost = new int[n];
        Arrays.fill(cost, (int) Math.pow(10, 9));
        cost[src] = 0;

        while (!queue.isEmpty()) {
            Tuple nodeProps = queue.poll();
            int node = nodeProps.node;
            int costFromStart = nodeProps.costFromStart;
            int stopsRemaining = nodeProps.stopsRemaining;

            if (node == dst) {
                return costFromStart;
            }

            if (stopsRemaining == 0) continue;

            for (Pair neighbourProps: adjList.get(node)) {
                int neighbourNode = neighbourProps.node;
                int neighbourNodeCost = neighbourProps.cost;

                int newCost = costFromStart + neighbourNodeCost;

                if (newCost < cost[neighbourNode]) {
                    if (stopsRemaining > 0)

                        cost[neighbourNode] = newCost;
                    queue.offer(new Tuple(neighbourNode, newCost, stopsRemaining-1));
                }

            }

        }

        return -1;
    }

    private void generateAdjList(int n, int[][] flights, List<List<Pair>> adjList ) {
        for (int i =0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            int from = flights[i][0];
            int to = flights[i][1];
            int cost = flights[i][2];

            adjList.get(from).add(new Pair(to, cost));

        }
    }

    private class Tuple {
        int costFromStart;
        int stopsRemaining;
        int node;

        public Tuple(int node, int costFromStart, int stopsRemaining) {
            this.costFromStart = costFromStart;
            this.stopsRemaining = stopsRemaining;
            this.node = node;
        }
    }

    private class Pair {
        int node;
        int cost;

        public Pair (int n, int c) {
            node = n;
            cost = c;
        }
    }
}
