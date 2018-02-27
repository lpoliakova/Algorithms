package graphs.dijkstra;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import graphs.dataStructures.Edge;
import graphs.input.AdjacencyList;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class ShortestPathTest {

    @DataProvider
    public static Object[][] filesResults() {
        return new Object[][] {
                {"TestFiles/dijkstraTest1.txt", new Integer[] {0, 1, 2, 3}},
                {"TestFiles/dijkstraTest2.txt", new Integer[] {0, 807, 581, 522}},
                {"TestFiles/dijkstraTest3.txt", new Integer[] {0, 198, 53, 141}},
                {"TestFiles/dijkstraTest4.txt", new Integer[] {0, 323, 409, 548}}
        };
    }

    @Test
    @UseDataProvider("filesResults")
    public void findMultipleShortestTest(String fileName, Integer[] expectedResult) {
        File file = new File(fileName);
        Map<Integer, List<Edge>> reverseGraph;
        try {
            reverseGraph = AdjacencyList.readReverseWeightGraphFromLines(file);
        } catch (FileNotFoundException ex) {
            System.out.printf("Input file not found");
            return;
        }
        Integer[] distances = ShortestPath.findShortest(reverseGraph);
        Integer[] result = Arrays.copyOfRange(distances, 1, 5);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void findShortestTest() {
        File file = new File("TestFiles/dijkstraTest.txt");
        Map<Integer, List<Edge>> reverseGraph;
        try {
            reverseGraph = AdjacencyList.readReverseWeightGraphFromLines(file);
        } catch (FileNotFoundException ex) {
            System.out.printf("Input file not found");
            return;
        }
        Integer[] distances = ShortestPath.findShortest(reverseGraph);

        assertEquals(2599, (int)distances[7]);
        assertEquals(2610, (int)distances[37]);
        assertEquals(2947, (int)distances[59]);
        assertEquals(2052, (int)distances[82]);
        assertEquals(2367, (int)distances[99]);
        assertEquals(2399, (int)distances[115]);
        assertEquals(2029, (int)distances[133]);
        assertEquals(2442, (int)distances[165]);
        assertEquals(2505, (int)distances[188]);
        assertEquals(3068, (int)distances[197]);
    }

}
