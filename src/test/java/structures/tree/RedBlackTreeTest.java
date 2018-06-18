package structures.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RedBlackTreeTest {
    private static final Integer[] NUMBERS = {10, 5, 567, 23, 56, 37};

    @Test
    public void insertTest() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        RedBlackTreeNode<Integer> rootNode = (RedBlackTreeNode<Integer>)tree.getRoot();
        Assert.assertEquals(NUMBERS[0], rootNode.getElement());
        Assert.assertTrue(rootNode.isBlack());

        RedBlackTreeNode<Integer> leftRootChild = (RedBlackTreeNode<Integer>)rootNode.getLeftChild();
        Assert.assertEquals(NUMBERS[1], leftRootChild.getElement());
        Assert.assertTrue(leftRootChild.isBlack());
        Assert.assertNull(leftRootChild.getLeftChild());
        Assert.assertNull(leftRootChild.getRightChild());

        RedBlackTreeNode<Integer> rightRootChild = (RedBlackTreeNode<Integer>)rootNode.getRightChild();
        Assert.assertEquals(NUMBERS[4], rightRootChild.getElement());
        Assert.assertTrue(rightRootChild.isRed());

        RedBlackTreeNode<Integer> leftRightChild = (RedBlackTreeNode<Integer>)rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[3], leftRightChild.getElement());
        Assert.assertTrue(leftRightChild.isBlack());
        Assert.assertNull(leftRightChild.getLeftChild());

        RedBlackTreeNode<Integer> rightLeftRightChild = (RedBlackTreeNode<Integer>)leftRightChild.getRightChild();
        Assert.assertEquals(NUMBERS[5], rightLeftRightChild.getElement());
        Assert.assertTrue(rightLeftRightChild.isRed());
        Assert.assertNull(rightLeftRightChild.getLeftChild());
        Assert.assertNull(rightLeftRightChild.getRightChild());

        RedBlackTreeNode<Integer> rightRightChild = (RedBlackTreeNode<Integer>)rightRootChild.getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRightChild.getElement());
        Assert.assertTrue(rightRightChild.isBlack());
        Assert.assertNull(rightRightChild.getLeftChild());
        Assert.assertNull(rightRightChild.getRightChild());
    }

    @Test
    public void deleteTest() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Arrays.stream(NUMBERS).forEach(tree::insert);

        testFirstDelete(tree);
        testSecondDelete(tree);

        tree.delete(NUMBERS[4]);

        RedBlackTreeNode<Integer> rootNode = (RedBlackTreeNode<Integer>)tree.getRoot();
        Assert.assertEquals(NUMBERS[3], rootNode.getElement());
        Assert.assertTrue(rootNode.isBlack());

        RedBlackTreeNode<Integer> leftRootChild = (RedBlackTreeNode<Integer>)rootNode.getLeftChild();
        Assert.assertEquals(NUMBERS[0], leftRootChild.getElement());
        Assert.assertTrue(leftRootChild.isBlack());
        Assert.assertNull(leftRootChild.getLeftChild());
        Assert.assertNull(leftRootChild.getRightChild());

        RedBlackTreeNode<Integer> rightRootChild = (RedBlackTreeNode<Integer>)rootNode.getRightChild();
        Assert.assertEquals(NUMBERS[5], rightRootChild.getElement());
        Assert.assertTrue(rightRootChild.isBlack());
        Assert.assertNull(rightRootChild.getLeftChild());
        Assert.assertNull(rightRootChild.getRightChild());
    }

    private void testFirstDelete(RedBlackTree<Integer> tree){
        tree.delete(NUMBERS[1]);

        RedBlackTreeNode<Integer> rootNode = (RedBlackTreeNode<Integer>)tree.getRoot();
        Assert.assertEquals(NUMBERS[4], rootNode.getElement());
        Assert.assertTrue(rootNode.isBlack());

        RedBlackTreeNode<Integer> leftRootChild = (RedBlackTreeNode<Integer>)rootNode.getLeftChild();
        Assert.assertEquals(NUMBERS[3], leftRootChild.getElement());
        Assert.assertTrue(leftRootChild.isBlack());

        RedBlackTreeNode<Integer> leftLeftChild = (RedBlackTreeNode<Integer>)leftRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[0], leftLeftChild.getElement());
        Assert.assertTrue(leftLeftChild.isRed());
        Assert.assertNull(leftLeftChild.getLeftChild());
        Assert.assertNull(leftLeftChild.getRightChild());

        RedBlackTreeNode<Integer> rightLeftChild = (RedBlackTreeNode<Integer>)leftRootChild.getRightChild();
        Assert.assertEquals(NUMBERS[5], rightLeftChild.getElement());
        Assert.assertTrue(rightLeftChild.isRed());
        Assert.assertNull(rightLeftChild.getLeftChild());
        Assert.assertNull(rightLeftChild.getRightChild());

        RedBlackTreeNode<Integer> rightRootChild = (RedBlackTreeNode<Integer>)rootNode.getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRootChild.getElement());
        Assert.assertTrue(rightRootChild.isBlack());
        Assert.assertNull(rightRootChild.getLeftChild());
        Assert.assertNull(rightRootChild.getRightChild());
    }

    private void testSecondDelete(RedBlackTree<Integer> tree){
        tree.delete(NUMBERS[2]);

        RedBlackTreeNode<Integer> rootNode = (RedBlackTreeNode<Integer>)tree.getRoot();
        Assert.assertEquals(NUMBERS[3], rootNode.getElement());
        Assert.assertTrue(rootNode.isBlack());

        RedBlackTreeNode<Integer> leftRootChild = (RedBlackTreeNode<Integer>)rootNode.getLeftChild();
        Assert.assertEquals(NUMBERS[0], leftRootChild.getElement());
        Assert.assertTrue(leftRootChild.isBlack());
        Assert.assertNull(leftRootChild.getLeftChild());
        Assert.assertNull(leftRootChild.getRightChild());

        RedBlackTreeNode<Integer> rightRootChild = (RedBlackTreeNode<Integer>)rootNode.getRightChild();
        Assert.assertEquals(NUMBERS[4], rightRootChild.getElement());
        Assert.assertTrue(rightRootChild.isBlack());
        Assert.assertNull(rightRootChild.getRightChild());

        RedBlackTreeNode<Integer> leftRightChild = (RedBlackTreeNode<Integer>)rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[5], leftRightChild.getElement());
        Assert.assertTrue(leftRightChild.isRed());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());
    }
}
