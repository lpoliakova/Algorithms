package graphs.hashTables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class TwoSum implements Callable<Set<Long>>{
    private final List<Long> numbers;
    private final Long startInterval;
    private final Long endInterval;

    public TwoSum(List<Long> numbers, Long startInterval, Long endInterval) {
        this.numbers = numbers;
        this.startInterval = startInterval;
        this.endInterval = endInterval;
    }

    @Override
    public Set<Long> call() throws Exception {
        return computeOnInterval(numbers, startInterval, endInterval);
    }

    public static Set<Long> computeOnInterval(List<Long> numbers, Long startInterval, Long endInterval) {
        Set<Long> sums = new HashSet<>();
        Set<Long> hashTable = new HashSet<>(numbers);
        for (long sum = startInterval; sum <= endInterval; sum++) {
            if (sum % 100 == 0) {
                System.out.println(sum);
            }
            for (Long firstNum : hashTable) {
                Long secondNum = sum - firstNum;
                if (!secondNum.equals(firstNum) && hashTable.contains(secondNum)) {
                    sums.add(sum);
                    break;
                }
            }
        }
        return sums;
    }
}
