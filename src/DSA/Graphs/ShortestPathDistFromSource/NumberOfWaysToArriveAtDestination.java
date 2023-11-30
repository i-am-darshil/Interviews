package DSA.Graphs.ShortestPathDistFromSource;

// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
import java.util.*;

public class NumberOfWaysToArriveAtDestination {
    public int countPaths(int n, int[][] roads) {
        return (int) (countPathsUtil(n, roads, 0, n-1));
    }

    private long countPathsUtil(int n, int[][] roads, int start, int end) {
        List<List<Point>> adjList = new ArrayList<>();
        generateAdjList(n, roads, adjList);

        Queue<Pair> queue = new PriorityQueue<>((p1, p2) -> p1.timeSoFar - p2.timeSoFar);
        int[] timeFromStart = new int[n];
        long[] numPaths = new long[n];

        Arrays.fill(timeFromStart, Integer.MAX_VALUE);

        timeFromStart[start] = 0;
        numPaths[start] = 1;

        queue.offer(new Pair(start, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int node = pair.node;
            int timeSoFar = pair.timeSoFar;

            if (node == end) {
                continue;
            }

            for (Point neighbour: adjList.get(node)) {
                int neighbourNode = neighbour.node;
                int timeToReach = neighbour.time;
                int newTimeSoFar = timeSoFar + timeToReach;

                if (newTimeSoFar < timeFromStart[neighbourNode]) {
                    numPaths[neighbourNode] = numPaths[node] % ((int) Math.pow(10, 9) + 7);
                    timeFromStart[neighbourNode] = newTimeSoFar;
                    queue.offer(new Pair(neighbourNode, timeFromStart[neighbourNode]));
                } else if (newTimeSoFar == timeFromStart[neighbourNode]) {
                    numPaths[neighbourNode] = (numPaths[neighbourNode] + numPaths[node]) % ((int) Math.pow(10, 9) + 7);
                }
            }
        }

        return numPaths[end] % ((int) Math.pow(10, 9) + 7);
    }

    // GAVE TTL
    private int countPathsUtilTTL(int n, int[][] roads) {
        List<List<Point>> adjList = new ArrayList<>();
        generateAdjList(n, roads, adjList);

        Queue<QPair> queue = new PriorityQueue<>((p1, p2) -> p1.timeSoFar - p2.timeSoFar);
        int[] timeFromStart = new int[n];

        Arrays.fill(timeFromStart, (int) Math.pow(10, 9));

        int start = 0;

        timeFromStart[start] = 0;

        queue.offer(new QPair(new ArrayList<>(Arrays.asList(start)), 0));

        int numOfMinPaths = 0;

        // List<Integer> numParents = new ArrayList<>();
        // for (int i =0; i < n; i++) {
        //     numParents.add(0);
        // }

        while (!queue.isEmpty()) {
            QPair qPair = queue.poll();
            List<Integer> nodes = qPair.nodes;
            int node = nodes.get(nodes.size() - 1);
            int timeSoFar = qPair.timeSoFar;

            if (node == n-1) {
                numOfMinPaths++;
                continue;
            }

            for (Point neighbour: adjList.get(node)) {
                int neighbourNode = neighbour.node;
                int timeToReach = neighbour.time;
                int newTimeSoFar = timeSoFar + timeToReach;

                if (newTimeSoFar <= timeFromStart[neighbourNode]) {
                    List<Integer> newNodes = new ArrayList<>(nodes);
                    newNodes.add(neighbourNode);
                    // numParents.set(neighbourNode, 1);
                    timeFromStart[neighbourNode] = newTimeSoFar;
                    queue.offer(new QPair(newNodes, timeFromStart[neighbourNode]));
                }
            }
        }

        return numOfMinPaths % ((int) Math.pow(10, 9) + 7);
    }

    private void generateAdjList(int n, int[][] roads, List<List<Point>> adjList) {
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i =0; i < roads.length; i++) {
            int from = roads[i][0];
            int to = roads[i][1];
            int time = roads[i][2];

            adjList.get(from).add(new Point(to, time));
            adjList.get(to).add(new Point(from, time));

        }
    }

    private class Pair {
        int node;
        int timeSoFar;

        public Pair(int n, int t) {
            node = n;
            timeSoFar = t;
        }
    }

    private class QPair {
        List<Integer> nodes;
        int timeSoFar;

        public QPair(List<Integer>  n, int t) {
            nodes = n;
            timeSoFar = t;
        }
    }

    private class Point {
        int node;
        int time;

        public Point(int n, int t) {
            node = n;
            time = t;
        }
    }
}
