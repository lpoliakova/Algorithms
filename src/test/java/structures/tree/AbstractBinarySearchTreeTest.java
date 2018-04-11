package structures.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AbstractBinarySearchTreeTest {
    private static final Integer[] NUMBERS = {10, 5, 567, 23, 56};

    @Test
    public void containsTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Arrays.stream(NUMBERS).map(tree::contains).forEach(Assert::assertTrue);

        Arrays.stream(NUMBERS).map(n -> n + 1).map(tree::contains).forEach(Assert::assertFalse);
    }

    @Test
    public void getElementFromTreeTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        for (Integer num : NUMBERS) {
            Integer element = tree.getElementFromTree(num);
            Assert.assertEquals(num, element);
        }

        Arrays.stream(NUMBERS).map(n -> n + 1).map(tree::getElementFromTree).forEach(Assert::assertNull);
    }

    @Test
    public void getElementAtPositionTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Integer[] sortedNumbers = Arrays.copyOf(NUMBERS, NUMBERS.length);
        Arrays.sort(sortedNumbers);

        for (int i = 0; i < NUMBERS.length; i++) {
            Integer element = tree.getElementAtPosition(i);
            Assert.assertEquals(sortedNumbers[i], element);
        }

        try {
            tree.getElementAtPosition(-1);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(true);
        }

        try {
            tree.getElementAtPosition(NUMBERS.length);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void getPositionOfElementTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Integer[] sortedNumbers = Arrays.copyOf(NUMBERS, NUMBERS.length);
        Arrays.sort(sortedNumbers);

        for (int i = 0; i < NUMBERS.length; i++) {
            int position = tree.getPositionOfElement(sortedNumbers[i]);
            Assert.assertEquals(i, position);
        }

        try {
            tree.getPositionOfElement(NUMBERS[0] + 1);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void getMinTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Integer min = Arrays.stream(NUMBERS).min(Integer::compare).get();

        Assert.assertEquals(min, tree.getMin());
    }

    @Test
    public void getMaxTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Integer max = Arrays.stream(NUMBERS).max(Integer::compare).get();

        Assert.assertEquals(max, tree.getMax());
    }

    @Test
    public void getPredecessorTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Integer[] sortedNumbers = Arrays.copyOf(NUMBERS, NUMBERS.length);
        Arrays.sort(sortedNumbers);

        Assert.assertNull(tree.getPredecessor(sortedNumbers[0]));

        for (int i = 1; i < NUMBERS.length; i++) {
            Integer element = tree.getPredecessor(sortedNumbers[i]);
            Assert.assertEquals(sortedNumbers[i - 1], element);
        }
    }

    @Test
    public void getSuccessorTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Integer[] sortedNumbers = Arrays.copyOf(NUMBERS, NUMBERS.length);
        Arrays.sort(sortedNumbers);

        Assert.assertNull(tree.getSuccessor(sortedNumbers[NUMBERS.length - 1]));

        for (int i = 0; i < NUMBERS.length - 1; i++) {
            Integer element = tree.getSuccessor(sortedNumbers[i]);
            Assert.assertEquals(sortedNumbers[i + 1], element);
        }
    }

    @Test
    public void printTest() {
        BinarySearchTree<Integer> tree = createUnbalancedBinarySearchTree();

        Integer[] sortedNumbers = Arrays.copyOf(NUMBERS, NUMBERS.length);
        Arrays.sort(sortedNumbers);

        Assert.assertEquals(Arrays.toString(sortedNumbers), tree.print(true));

        Arrays.sort(sortedNumbers, (x, y) -> -Integer.compare(x, y));

        Assert.assertEquals(Arrays.toString(sortedNumbers), tree.print(false));
    }

    @Test
    public void sizeTest() {
        BinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();

        Assert.assertEquals(0, tree.size());

        tree = createUnbalancedBinarySearchTree();

        Assert.assertEquals(NUMBERS.length, tree.size());
    }

    @Test
    public void isEmptyTest() {
        BinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();

        Assert.assertTrue(tree.isEmpty());

        tree = createUnbalancedBinarySearchTree();

        Assert.assertFalse(tree.isEmpty());
    }

    private BinarySearchTree<Integer> createUnbalancedBinarySearchTree() {
        BinarySearchTree<Integer> testTree = new UnbalancedBinarySearchTree<>();

        Arrays.stream(NUMBERS).forEach(testTree::insert);

        return testTree;
    }
}
