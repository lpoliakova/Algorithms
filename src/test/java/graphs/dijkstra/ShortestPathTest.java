package graphs.dijkstra;

import graphs.dataStructures.Edge;
import graphs.input.AdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class ShortestPathTest {
    @Test
    void findShortestTest() {
        File file = new File("TestFiles/dijkstraTest.txt");
        Map<Integer, List<Edge>> reverseGraph;
        try {
            reverseGraph = AdjacencyList.readReverseWeightGraphFromLines(file);
        } catch (FileNotFoundException ex) {
            System.out.printf("Input file not found");
            return;
        }
        Integer[] distances = ShortestPath.findShortest(reverseGraph);

        Assertions.assertEquals(2599, (int)distances[7]);
        Assertions.assertEquals(2610, (int)distances[37]);
        Assertions.assertEquals(2947, (int)distances[59]);
        Assertions.assertEquals(2052, (int)distances[82]);
        Assertions.assertEquals(2367, (int)distances[99]);
        Assertions.assertEquals(2399, (int)distances[115]);
        Assertions.assertEquals(2029, (int)distances[133]);
        Assertions.assertEquals(2442, (int)distances[165]);
        Assertions.assertEquals(2505, (int)distances[188]);
        Assertions.assertEquals(3068, (int)distances[197]);
    }

}
