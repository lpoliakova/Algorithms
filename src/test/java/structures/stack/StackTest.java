package structures.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class StackTest {

    @Test
    public void stackEmpty() {
        Stack<String> stack = new Stack<>();

        Assert.assertTrue(stack.isEmpty());
    }

    @Test(expected = EmptyStackException.class)
    public void stackEmptyPeekTest() {
        Stack<String> stack = new Stack<>();

        stack.peek();
    }

    @Test(expected = EmptyStackException.class)
    public void stackEmptyPopTest() {
        Stack<String> stack = new Stack<>();

        stack.pop();
    }

    @Test
    public void stackPushPopTest() {
        Stack<String> stack = new Stack<>();

        String expected = "AAA";

        stack.push(expected);

        Assert.assertFalse(stack.isEmpty());

        String actual = stack.pop();

        Assert.assertEquals(expected, actual);
        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void stackPushPeekTest() {
        Stack<String> stack = new Stack<>();

        String expected = "AAA";

        stack.push(expected);

        Assert.assertFalse(stack.isEmpty());

        String actual = stack.peek();

        Assert.assertEquals(expected, actual);
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void stackWorkflowTest() {
        Stack<String> actual = new Stack<>();
        java.util.Stack<String> expected = new java.util.Stack<>();

        actual.push("AAA");
        expected.push("AAA");

        actual.push("BB");
        expected.push("BB");

        actual.push("CC");
        expected.push("CC");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.pop(), actual.pop());
        Assert.assertEquals(expected.size(), actual.size());

        actual.push("DDD");
        expected.push("DDD");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.pop(), actual.pop());
        Assert.assertEquals(expected.pop(), actual.pop());
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.peek(), actual.peek());

        actual.push("EE");
        expected.push("EE");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.pop(), actual.pop());
        Assert.assertEquals(expected.pop(), actual.pop());
        Assert.assertEquals(expected.size(), actual.size());

        actual.push("FFF");
        expected.push("FFF");

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.peek(), actual.peek());
        Assert.assertEquals(expected.pop(), actual.pop());
        Assert.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void queuEqualityTest() {
        Stack<String> first = new Stack<>();
        Stack<String> second = new Stack<>();

        first.push("AAA");
        second.push("AAA");

        first.push("BB");
        second.push("BB");

        first.push("CC");
        second.push("CC");

        Assert.assertTrue(first.equals(second));
        Assert.assertEquals(first.hashCode(), second.hashCode());

        first.pop();
        second.pop();

        Assert.assertTrue(first.equals(second));
        Assert.assertEquals(first.hashCode(), second.hashCode());

    }
}
