package dfs;

import java.util.*;

public class StronglyConnectedComponents {
    public static List<List<Integer>> find(Map<Integer, List<Integer>> graph, Map<Integer, List<Integer>> reverseGraph,
                                            Integer graphSize){
        List<Integer> nodeEnumeration = enumerateNodes(reverseGraph, graphSize);
        return getConnectedComponents(graph, nodeEnumeration, graphSize);
    }

    private static List<Integer> enumerateNodes(Map<Integer, List<Integer>> graph, Integer graphSize) {
        List<Integer> enumerated = new ArrayList<>(graphSize);
        Boolean[] visited = getInitialVisit(graphSize);
        for (int node = graphSize; node > 0; node--){
            if (!visited[node]) {
                enumerated.addAll(findReachableNodes(graph, node, visited));
            }
        }
        return enumerated;
    }

    private static List<List<Integer>> getConnectedComponents(Map<Integer, List<Integer>> graph, List<Integer> nodes, Integer graphSize){
        List<List<Integer>> components = new ArrayList<>();
        Boolean[] visited = getInitialVisit(graphSize);
        for (int node = nodes.size() - 1; node >= 0; node--) {
            if (!visited[nodes.get(node)]) {
                components.add(findReachableNodes(graph, nodes.get(node), visited));
            }
        }
        return components;
    }

    private static List<Integer> findReachableNodesRecursive(Map<Integer, List<Integer>> graph, Integer startNode, Boolean[] visited){
        List<Integer> reached = new ArrayList<>();
        visited[startNode] = true;
        List<Integer> edges = graph.get(startNode);
        if (edges != null) {
            for (Integer node : edges) {
                if (!visited[node]) {
                    reached.addAll(findReachableNodesRecursive(graph, node, visited));
                }
            }
        }
        reached.add(startNode);
        return reached;
    }

    private static List<Integer> findReachableNodes(Map<Integer, List<Integer>> graph, Integer startNode, Boolean[] visited){
        List<Integer> reached = new ArrayList<>();
        Stack<Integer> processing = new Stack<>();
        processing.push(startNode);
        while (!processing.isEmpty()){
            Integer node = processing.peek();
            if (visited[node]) {
                processing.pop();
            } else {
                List<Integer> edges = graph.get(node);
                if (edges == null || edges.isEmpty()) {
                    visited[node] = true;
                    reached.add(node);
                    processing.pop();
                } else {
                    Integer nextNode = edges.get(0);
                    edges.remove(0);
                    processing.push(nextNode);
                }
            }
        }
        return reached;
    }

    private static Boolean[] getInitialVisit(Integer size){
        Boolean[] visited = new Boolean[size + 1];
        for (int node = 0; node <= size; node++) {
            visited[node] = false;
        }
        return visited;
    }
}
