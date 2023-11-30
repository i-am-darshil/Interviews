package DSA.Graphs.TopologicalSortAndCycleDetection;
import java.util.*;
public class CourseSchedule_CycleDetectInDirectedGraph {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1) return true;
        // return !canFinishUtil(numCourses, prerequisites);
        return canFinishUtilBFS(numCourses, prerequisites);
    }


    public boolean canFinishUtilBFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indegrees = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        generateAdjListForBFS(numCourses, prerequisites, adjList);
        generateInDegree(adjList, indegrees, numCourses);

        int numNodesVisited = 0;

        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            numNodesVisited++;

            for (int neighbour: adjList.get(node)) {
                indegrees[neighbour]--;

                if (indegrees[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        return numNodesVisited == numCourses;

    }

    private void generateInDegree(List<List<Integer>> adjList, int[] indegrees, int numCourses) {
        for (int i = 0; i < numCourses; i++) {
            for (int neighbour: adjList.get(i)) {
                indegrees[neighbour]++;
            }
        }
    }

    private void generateAdjListForBFS(int numCourses, int[][] prerequisites, List<List<Integer>> adjList) {

        for (int i = 0;i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];

            adjList.get(from).add(to);
        }
    }

    // DFS
    private boolean canFinishUtil(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();

        generateAdjList(numCourses, prerequisites, adjList);

        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];
        boolean isCycle;

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                isCycle = isCycleDFS(i, adjList, visited, onPath);
                if (isCycle) return true;
            }
        }

        return false;
    }

    private boolean isCycleDFS(int node, List<List<Integer>> adjList, boolean[] visited, boolean[] onPath) {
        visited[node] = true;
        onPath[node] = true;

        boolean isCycle;

        for (Integer neighbour: adjList.get(node)) {
            if (onPath[neighbour]) {
                return true;
            }

            if (!visited[neighbour]) {
                isCycle = isCycleDFS(neighbour, adjList, visited, onPath);
                if (isCycle) return true;
            }
        }

        onPath[node] = false;
        return false;
    }

    private void generateAdjList(int numCourses, int[][] prerequisites, List<List<Integer>> adjList) {

        for (int i = 0; i<numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i< prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];

            adjList.get(from).add(to);
        }
    }
}
