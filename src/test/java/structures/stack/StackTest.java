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
}
