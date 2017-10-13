package heaps;

import java.util.List;
import java.util.PriorityQueue;

public class MedianMaintenance {
    public static Integer[] maintain(List<Integer> numbers) {
        Integer[] medians = new Integer[numbers.size()];
        PriorityQueue<Integer> highHeap = new PriorityQueue<>();
        PriorityQueue<Integer> lowHeap = new PriorityQueue<>((i1, i2) -> -Integer.compare(i1, i2));
        insetFirstTwoNumbers(highHeap, lowHeap, medians, numbers.get(0), numbers.get(1));
        for (int i = 2; i < numbers.size(); i++) {
            insertNumber(numbers.get(i), highHeap, lowHeap);
            medians[i] = extractMedian(highHeap, lowHeap);
        }
        return medians;
    }

    private static void insetFirstTwoNumbers(PriorityQueue<Integer> highHeap, PriorityQueue<Integer> lowHeap, Integer[] medians,
                                             Integer first, Integer second) {
        medians[0] = first;
        if (first > second) {
            highHeap.add(first);
            lowHeap.add(second);
            medians[1] = second;
        } else {
            lowHeap.add(first);
            highHeap.add(second);
            medians[1] = first;
        }
    }

    private static void insertNumber(Integer number, PriorityQueue<Integer> highHeap, PriorityQueue<Integer> lowHeap) {
        Integer highMedian = highHeap.peek();
        Integer lowMedian = lowHeap.peek();
        if (number > highMedian) {
            insertInHeap(number, highHeap, lowHeap);
        } else if (number < lowMedian) {
            insertInHeap(number, lowHeap, highHeap);
        } else {
            insertBetween(number, highHeap, lowHeap);
        }
    }

    private static void insertInHeap(Integer number, PriorityQueue<Integer> addToHeap, PriorityQueue<Integer> otherHeap) {
        addToHeap.add(number);
        if (addToHeap.size() > otherHeap.size() + 1) {
            Integer movingNumber = addToHeap.poll();
            otherHeap.add(movingNumber);
        }
    }

    private static void insertBetween(Integer number, PriorityQueue<Integer> highHeap, PriorityQueue<Integer> lowHeap) {
        if (highHeap.size() < lowHeap.size()) {
            highHeap.add(number);
        } else {
            lowHeap.add(number);
        }
    }

    private static Integer extractMedian(PriorityQueue<Integer> highHeap, PriorityQueue<Integer> lowHeap) {
        if (highHeap.size() > lowHeap.size()) {
            return highHeap.peek();
        }
        return lowHeap.peek();
    }

}
