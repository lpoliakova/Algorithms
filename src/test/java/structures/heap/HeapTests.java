package structures.heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class HeapTests {

    @Test
    public void queueEmpty() {
        Heap<String> heap = new Heap<>();

        Assert.assertTrue(heap.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void queueEmptyPeekTest() {
        Heap<String> heap = new Heap<>();

        heap.peek();
    }

    @Test(expected = NoSuchElementException.class)
    public void queueEmptyPollTest() {
        Heap<String> heap = new Heap<>();

        heap.poll();
    }

    @Test
    public void queueOfferPollTest() {
        Heap<String> heap = new Heap<>();

        String expected = "AAA";

        heap.offer(expected);

        Assert.assertFalse(heap.isEmpty());

        String actual = heap.poll();

        Assert.assertEquals(expected, actual);
        Assert.assertTrue(heap.isEmpty());
    }

    @Test
    public void queueOfferPeekTest() {
        Heap<String> heap = new Heap<>();

        String expected = "AAA";

        heap.offer(expected);

        Assert.assertFalse(heap.isEmpty());

        String actual = heap.peek();

        Assert.assertEquals(expected, actual);
        Assert.assertFalse(heap.isEmpty());
    }

    @Test
    public void queueWorkflowTest() {
        Heap<Integer> actual = new Heap<>();
        PriorityQueue<Integer> expected = new PriorityQueue<>();

        actual.offer(4);
        expected.offer(4);

        actual.offer(5);
        expected.offer(2);

        actual.offer(2);
        expected.offer(5);

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());

        actual.offer(8);
        expected.offer(8);

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.peek(), actual.peek());

        actual.offer(3);
        expected.offer(3);

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());

        actual.offer(11);
        expected.offer(11);

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.peek(), actual.peek());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void queueEqualityTest() {
        Heap<Integer> first = new Heap<>();
        Heap<Integer> second = new Heap<>();

        first.offer(11);
        second.offer(11);

        first.offer(5);
        second.offer(7);

        first.offer(7);
        second.offer(5);

        Assert.assertTrue(first.equals(second));
        Assert.assertEquals(first.hashCode(), second.hashCode());

        first.poll();
        second.poll();

        Assert.assertTrue(first.equals(second));
        Assert.assertEquals(first.hashCode(), second.hashCode());

    }
}
