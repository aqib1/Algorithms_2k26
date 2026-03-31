package graph;

import java.util.*;

public class ShortestPath {
    static void addEdge(List<List<Integer>> graph, int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    static void main() {
        int numberOfNodes = 6;

        // Har node ke neighbours ki list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            graph.add(new ArrayList<>());
        }

        // Undirected edges: dono directions mein connection
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 5);

        int start = 0;
        int end = 5;

        List<Integer> path = shortestPath(graph, start, end);

    }

    static List<Integer> shortestPath(
            List<List<Integer>> graph, int start, int end) {
        int []parent = new int[graph.size()];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[graph.size()];
        var queue = new ArrayDeque<Integer>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            var curr = queue.remove();
            if(curr == end)
                break;

            for(var neighbour : graph.get(curr)) {
                if(!visited[neighbour]) {
                    visited[neighbour] = true;
                    parent[neighbour] = curr;
                    queue.add(neighbour);
                }
            }
        }

        if(!visited[end]) {
            return List.of();
        }

        var path = new ArrayList<Integer>();

        for(int node = end; node != -1; node = parent[node]) {
            path.add(node);
        }
        System.out.println(path);
        Collections.reverse(path);
        return path;
    }
}
