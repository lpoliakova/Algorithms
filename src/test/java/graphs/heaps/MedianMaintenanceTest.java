package graphs.heaps;

import graphs.input.NumbersList;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MedianMaintenanceTest {
    private static final Integer MOD = 10000;

    @Test
    public void maintainTest() {
        File file = new File("TestFiles/medianTest.txt");
        List<Integer> numbers;
        try {
            numbers = NumbersList.readNumbers(file);
        } catch (FileNotFoundException ex) {
            System.out.printf("Input file not found");
            return;
        }
        Integer[] medians = MedianMaintenance.maintain(numbers);
        Integer allMedians = Arrays.stream(medians).reduce(0, Integer::sum);
        assertEquals(1213, allMedians % MOD);
    }
}
