package structures.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RedBlackTreeTest {
    private static final Integer[] NUMBERS = {10, 5, 567, 23, 56};

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
        Assert.assertTrue(rightRootChild.isBlack());

        RedBlackTreeNode<Integer> leftRightChild = (RedBlackTreeNode<Integer>)rightRootChild.getLeftChild();
        Assert.assertEquals(NUMBERS[3], leftRightChild.getElement());
        Assert.assertTrue(leftRightChild.isRed());
        Assert.assertNull(leftRightChild.getLeftChild());
        Assert.assertNull(leftRightChild.getRightChild());

        RedBlackTreeNode<Integer> rightRightChild = (RedBlackTreeNode<Integer>)rightRootChild.getRightChild();
        Assert.assertEquals(NUMBERS[2], rightRightChild.getElement());
        Assert.assertTrue(rightRightChild.isRed());
        Assert.assertNull(rightRightChild.getLeftChild());
        Assert.assertNull(rightRightChild.getRightChild());
    }
}
