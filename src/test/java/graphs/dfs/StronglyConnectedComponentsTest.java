package graphs.dfs;

import graphs.input.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StronglyConnectedComponentsTest {
    @Test
    void findComponentsTest(){
        File file = new File("TestFiles/connectedComponentsTest.txt");
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
        List<Integer> expected = Arrays.asList(434821, 968, 459, 313, 211);
        List<Integer> actual = scc.stream().map(List::size).sorted((e1,e2) -> -e1.compareTo(e2)).limit(5).collect(Collectors.toList());
        Assertions.assertEquals(expected, actual);
    }
}
