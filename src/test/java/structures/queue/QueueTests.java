package structures.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueTests {

    @Test
    public void queueEmpty() {
        Queue<String> queue = new Queue<>();

        Assert.assertTrue(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void queueEmptyPeekTest() {
        Queue<String> queue = new Queue<>();

        queue.peek();
    }

    @Test(expected = NoSuchElementException.class)
    public void queueEmptyPollTest() {
        Queue<String> queue = new Queue<>();

        queue.poll();
    }

    @Test
    public void queueOfferPollTest() {
        Queue<String> queue = new Queue<>();

        String expected = "AAA";

        queue.offer(expected);

        Assert.assertFalse(queue.isEmpty());

        String actual = queue.poll();

        Assert.assertEquals(expected, actual);
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void queueOfferPeekTest() {
        Queue<String> queue = new Queue<>();

        String expected = "AAA";

        queue.offer(expected);

        Assert.assertFalse(queue.isEmpty());

        String actual = queue.peek();

        Assert.assertEquals(expected, actual);
        Assert.assertFalse(queue.isEmpty());
    }

    @Test
    public void queueWorkflowTest() {
        Queue<String> actual = new Queue<>();
        java.util.Queue<String> expected = new LinkedList<>();

        actual.offer("AAA");
        expected.offer("AAA");

        actual.offer("BB");
        expected.offer("BB");

        actual.offer("CC");
        expected.offer("CC");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());

        actual.offer("DDD");
        expected.offer("DDD");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.peek(), actual.peek());

        actual.offer("EE");
        expected.offer("EE");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());

        actual.offer("FFF");
        expected.offer("FFF");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.peek(), actual.peek());
        Assert.assertEquals(expected.poll(), actual.poll());
        Assert.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void queuEqualityTest() {
        Queue<String> first = new Queue<>();
        Queue<String> second = new Queue<>();

        first.offer("AAA");
        second.offer("AAA");

        first.offer("BB");
        second.offer("BB");

        first.offer("CC");
        second.offer("CC");

        Assert.assertTrue(first.equals(second));
        Assert.assertEquals(first.hashCode(), second.hashCode());

        first.poll();
        second.poll();

        Assert.assertTrue(first.equals(second));
        Assert.assertEquals(first.hashCode(), second.hashCode());

    }
}
