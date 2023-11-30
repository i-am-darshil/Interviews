package DSA.Graphs.ShortestPathDistFromSource;

// https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1

/*

1) ==== Shortest path from any src :
1.Perform toposort and store the order in a stack
2.Once the source node is given, pop the elements in the stack until the stack's top is the source
3. Rest is the same .
Eg :
For src 5--->  dist=[4,6,2,5,1,0,inf]
6->inf because you cannot reach 6 from 5

2) ==== Why use not use normal BFS here ??
// Youtube Comment section from
// https://www.youtube.com/watch?v=ZUFQfFaU-8U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=28

This problem, can be solved through BFS too.But there will be too many redundancies in it.
We start from source=0, it relaxes its adjacents, and pushes these adjacent nodes along with their distances into the queue.
Then these adjacent nodes will further relax their adjacent nodes and push the relaxed nodes.

Consider this graph: first of pair is the adjacent node and second of pair is the edge weight

1->[(2,2), (3,1)]
2->(4,2)
3->(5,1)
5->(4,1)
4->(6,1)

Final queue will be like - (first of pair is the node and second of pair is the distance from source to this node)
(1,0)(2,2)(3,1)(4,4)(5 2)(6 5)(4 3)(6 4)
These all will be popped out when they relax their adjacent nodes.
For ex, (1,0) will be popped out before (2,2)(3,1) are pushed into queue and so on.

As we can see, there is redundancy, node 4 first came in the queue with a distance of 4 from source, and then again
node 4 came with a distance of 3 from the source, which increases time complexity, because, now (4,3) will have to again relax its adjacent nodes
to reduce the distances of its adjacent nodes.

So, if the pre-requisites of any node(say, cur) are relaxed as minimum as possible before the relaxation of node cur.Then
redundancy will never occur.
Taking the same example as above, if 1 2 3 5 are relaxed as minimum as possible before the relaxation of node 4. Then
redundancy will never occur.

The solution to the above observation is Topological sort.
If we have Topo sort sequence, then we will take the source node first and relax its adjacent nodes.After that, we take next node
in the topo sort, and will do the same.

TC - O(N+E)
SC-O(N)
*/

import java.util.*;

public class ShortestPathFromSourceForDAG {
    public int[] shortestPath(int N,int M, int[][] edges) {
        //Code here
        return shortestPathUtil(N, M, edges);
    }

    private int[] shortestPathUtil(int N,int M, int[][] edges) {
        List<List<Pair>> adjList = new ArrayList<>();
        generateAdjList(N, M, edges, adjList);

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N];
        int[] dist = new int[N];

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                topologicalSort(i, adjList, stack, visited);
            }
        }

        for(int i = 0; i < N; i++) {
            dist[i] = (int) Math.pow(10, 9);
        }

        int source = 0;
        dist[source] = 0;

        while (stack.peek() != source) {
            stack.pop();
        }


        while (!stack.empty()) {
            int node = stack.pop();

            for (Pair neighbour: adjList.get(node)) {
                int val = neighbour.node;
                int weight = neighbour.weight;

                dist[val] = Math.min(dist[val], dist[node] + weight);
            }
        }

        for (int i = 0; i < N; i++) {
            if (dist[i] >= (int) Math.pow(10, 9)) {
                dist[i] = -1;
            }
        }

        return dist;



    }

    private void topologicalSort(int node, List<List<Pair>> adjList, Stack<Integer> stack, boolean[] visited) {

        visited[node] = true;

        for (Pair neighbour: adjList.get(node)) {
            if (!visited[neighbour.node]) {
                topologicalSort(neighbour.node, adjList, stack, visited);
            }
        }

        stack.push(node);
    }

    private void generateAdjList(int N,int M, int[][] edges, List<List<Pair>> adjList) {
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            int weight = edges[i][2];

            adjList.get(start).add(new Pair(end, weight));
        }
    }

    private class Pair {
        int node;
        int weight;

        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
