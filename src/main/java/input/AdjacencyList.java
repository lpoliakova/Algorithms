package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AdjacencyList {

    public static Map<Integer, List<Integer>> readGraphFromPairs(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        while (scan.hasNext()) {
            Integer startNode = scan.nextInt();
            Integer endNode = scan.nextInt();
            addEdge(graph, startNode, endNode);
        }
        return graph;
    }

    public static Map<Integer, List<Integer>> readReverseGraphFromPairs(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        while (scan.hasNext()) {
            Integer endNode = scan.nextInt();
            Integer startNode = scan.nextInt();
            addEdge(graph, startNode, endNode);
        }
        return graph;
    }

    private static void addEdge(Map<Integer, List<Integer>> graph, Integer startNode, Integer endNode){
        List<Integer> edges = graph.getOrDefault(startNode, new ArrayList<>());
        edges.add(endNode);
        graph.put(startNode, edges);
    }

}
