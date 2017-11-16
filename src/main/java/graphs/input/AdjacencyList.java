package graphs.input;

import graphs.dataStructures.Edge;

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

    public static Map<Integer, List<Edge>> readReverseWeightGraphFromLines(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        Map<Integer, List<Edge>> graph = new HashMap<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] lineParts = line.split("\t");
            Integer endNode = Integer.parseInt(lineParts[0]);
            for (int i = 1; i < lineParts.length; i++) {
                String[] edgeParts = lineParts[i].split(",");
                Integer startNode = Integer.parseInt(edgeParts[0]);
                Integer length = Integer.parseInt(edgeParts[1]);
                addEdge(graph, startNode, endNode, length);
            }

        }
        return graph;
    }

    private static void addEdge(Map<Integer, List<Integer>> graph, Integer startNode, Integer endNode){
        List<Integer> edges = graph.getOrDefault(startNode, new ArrayList<>());
        edges.add(endNode);
        graph.put(startNode, edges);
    }

    private static void addEdge(Map<Integer, List<Edge>> graph, Integer startNode, Integer endNode, Integer length){
        List<Edge> edges = graph.getOrDefault(startNode, new ArrayList<>());
        edges.add(new Edge(endNode, startNode, length));
        graph.put(startNode, edges);
    }

}
