package graphs.hashTables;

import graphs.input.NumbersList;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class TwoSumTest {
    private static final Long INTERVAL_START = -10000L;
    private static final Long INTERVAL_END = 10000L;

    @Test
    public void findSumsTest() {
        File file = new File("TestFiles/twoSumTest.txt");
        List<Long> numbers;
        try {
            numbers = NumbersList.readLongNumbers(file);
        } catch (FileNotFoundException ex) {
            System.out.printf("Input file not found");
            return;
        }
        //Set<Long> sums = TwoSum.computeOnInterval(numbers, INTERVAL_START, INTERVAL_END);
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Set<Long>> future1 = service.submit(new TwoSum(numbers, INTERVAL_START, (INTERVAL_START + INTERVAL_END) / 2));
        Future<Set<Long>> future2 = service.submit(new TwoSum(numbers, (INTERVAL_START + INTERVAL_END) / 2 + 1, INTERVAL_END));
        try {
            assertEquals(427, future1.get().size() + future2.get().size());
        } catch (Exception ex) {
            assert(false);
        }
    }
}
