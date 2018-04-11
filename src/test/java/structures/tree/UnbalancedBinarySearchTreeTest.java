package structures.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class UnbalancedBinarySearchTreeTest {
    private static final Integer[] NUMBERS = {10, 5, 567, 23, 56};

    @Test
    public void insertTest() {
        UnbalancedBinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        TreeNode<Integer> rootNode = tree.getRoot();
        Assert.assertEquals(NUMBERS[0], rootNode.getElement());

        TreeNode<Integer> leftRootChild = rootNode.getLeftChild();
        Assert.assertEquals(NUMBERS[1], leftRootChild.getElement());
        Assert.assertNull(leftRootChild.getLeftChild());
        Assert.assertNull(leftRootChild.getRightChild());

        TreeNode<Integer> rightRootChild = rootNode.getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRootChild.getElement());
        Assert.assertNull(rightRootChild.getRightChild());

        TreeNode<Integer> leftRightChild = rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[3], leftRightChild.getElement());
        Assert.assertNull(leftRightChild.getLeftChild());

        TreeNode<Integer> rightLeftChild = leftRightChild.getRightChild();
        Assert.assertEquals(NUMBERS[4], rightLeftChild.getElement());
        Assert.assertNull(rightLeftChild.getLeftChild());
        Assert.assertNull(rightLeftChild.getRightChild());
    }

    @Test
    public void deleteWithoutChildrenTest() {
        UnbalancedBinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        tree.delete(NUMBERS[NUMBERS.length - 1]);

        TreeNode<Integer> leftRightChild = tree.getRoot().getRightChild().getLeftChild();
        Assert.assertEquals(NUMBERS[3], leftRightChild.getElement());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());
    }

    @Test
    public void deleteWithOneChildTest() {
        UnbalancedBinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        tree.delete(NUMBERS[3]);

        TreeNode<Integer> rightRootChild = tree.getRoot().getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRootChild.getElement());
        Assert.assertNull(rightRootChild.getRightChild());

        TreeNode<Integer> leftRightChild = rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[4], leftRightChild.getElement());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());
    }

    @Test
    public void deleteWithTwoChildrenTest() {
        UnbalancedBinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        tree.delete(NUMBERS[0]);

        TreeNode<Integer> rootNode = tree.getRoot();
        Assert.assertEquals(NUMBERS[3], rootNode.getElement());

        TreeNode<Integer> leftRootChild = rootNode.getLeftChild();
        Assert.assertEquals(NUMBERS[1], leftRootChild.getElement());
        Assert.assertNull(leftRootChild.getLeftChild());
        Assert.assertNull(leftRootChild.getRightChild());

        TreeNode<Integer> rightRootChild = rootNode.getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRootChild.getElement());
        Assert.assertNull(rightRootChild.getRightChild());

        TreeNode<Integer> leftRightChild = rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[4], leftRightChild.getElement());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());
    }

    @Test
    public void deletePositionWithoutChildrenTest() {
        UnbalancedBinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        tree.delete(3);

        TreeNode<Integer> leftRightChild = tree.getRoot().getRightChild().getLeftChild();
        Assert.assertEquals(NUMBERS[3], leftRightChild.getElement());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());
    }

    @Test
    public void deletePositionWithOneChildTest() {
        UnbalancedBinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        tree.delete(2);

        TreeNode<Integer> rightRootChild = tree.getRoot().getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRootChild.getElement());
        Assert.assertNull(rightRootChild.getRightChild());

        TreeNode<Integer> leftRightChild = rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[4], leftRightChild.getElement());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());
    }

    @Test
    public void deletePositionWithTwoChildrenTest() {
        UnbalancedBinarySearchTree<Integer> tree = new UnbalancedBinarySearchTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        tree.delete(1);

        TreeNode<Integer> rootNode = tree.getRoot();
        Assert.assertEquals(NUMBERS[3], rootNode.getElement());

        TreeNode<Integer> leftRootChild = rootNode.getLeftChild();
        Assert.assertEquals(NUMBERS[1], leftRootChild.getElement());
        Assert.assertNull(leftRootChild.getLeftChild());
        Assert.assertNull(leftRootChild.getRightChild());

        TreeNode<Integer> rightRootChild = rootNode.getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRootChild.getElement());
        Assert.assertNull(rightRootChild.getRightChild());

        TreeNode<Integer> leftRightChild = rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[4], leftRightChild.getElement());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());
    }
}
