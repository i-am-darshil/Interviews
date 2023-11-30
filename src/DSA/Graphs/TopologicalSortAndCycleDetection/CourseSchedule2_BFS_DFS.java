package DSA.Graphs.TopologicalSortAndCycleDetection;

import java.util.*;

public class CourseSchedule2_BFS_DFS {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // return findOrderDFS(numCourses, prerequisites);
        return findOrderBFS(numCourses, prerequisites);
    }


    private int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        int[] ans = new int[numCourses];
        int[] indegrees = new int[numCourses];

        generateAdjListAndIndegree(adjList, indegrees, numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();

            ans[index++] = node;

            for (int neighbour: adjList.get(node)) {
                indegrees[neighbour]--;

                if (indegrees[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        if (index == numCourses) {
            return ans;
        } else {
            return new int[] {};
        }
    }

    private void generateAdjListAndIndegree(List<List<Integer>> adjList, int[] indegrees, int numCourses, int[][] prerequisites) {

        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];

            indegrees[to]++;

            adjList.get(from).add(to);
        }
    }


    private int[] findOrderDFS(int numCourses, int[][] prerequisites) {

        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];

        List<List<Integer>> adjList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        generateAdjList(numCourses, prerequisites, adjList);
        boolean isCycle = false;

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                isCycle = dfsAndIsCycle(i, adjList, visited, onPath, stack);
                if (isCycle) {
                    return new int[]{};
                }
            }
        }


        int[] topoOrder = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            topoOrder[i] = stack.pop();
        }

        return topoOrder;
    }

    private boolean dfsAndIsCycle(int node, List<List<Integer>> adjList, boolean[] visited, boolean[] onPath, Stack<Integer> stack) {

        visited[node] = true;
        onPath[node] = true;

        for (int neighbour: adjList.get(node)) {
            if (onPath[neighbour] == true) {
                return true;
            }
            if (!visited[neighbour]) {
                boolean isCycle = dfsAndIsCycle(neighbour, adjList, visited, onPath, stack);
                if (isCycle) {
                    return true;
                }
            }
        }

        onPath[node] = false;
        stack.push(node);
        return false;
    }

    private void generateAdjList(int numCourses, int[][] prerequisites, List<List<Integer>> adjList) {
        for (int i=0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {

            int from = prerequisites[i][1];
            int to = prerequisites[i][0];

            adjList.get(from).add(to);
        }
    }
}
