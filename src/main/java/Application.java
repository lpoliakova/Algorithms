import dfs.StronglyConnectedComponents;
import input.AdjacencyList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("test.txt");
        Map<Integer, List<Integer>> graph;
        Map<Integer, List<Integer>> reverseGraph;
        try {
            graph = AdjacencyList.readGraphFromPairs(file);
            reverseGraph = AdjacencyList.readReverseGraphFromPairs(file);
        } catch (FileNotFoundException ex) {
            System.out.printf("Input file not found");
            return;
        }
        List<List<Integer>> scc = StronglyConnectedComponents.find(graph, reverseGraph, 875714);
        for (List<Integer> component : scc) {
            System.out.print(Arrays.toString(component.toArray()));
            System.out.println();
        }
        System.out.println(formatAnswer(scc));
    }

    private static String formatAnswer(List<List<Integer>> scc){
        List<Integer> sizes = scc.stream().map(List::size).sorted((e1,e2) -> -e1.compareTo(e2)).limit(5).collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++){
            if (sizes.size() <= i) {
                result.append("0,");
            } else {
                result.append(sizes.get(i)).append(",");
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
