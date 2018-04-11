package structures.tree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RedBlackTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {

    @Override
    public void insert(T element) {
        if (isEmpty()) {
            initRoot(new RedBlackTreeNode<T>(element));
            return;
        }

        TreeNode<T> parentForInsert = findInsertionNode(element);
        localInsertElement(parentForInsert, element);
    }

    @Override
    public void delete(T element) {
        TreeNode<T> nodeForDeletion = getNodeForElement(element);

        if (nodeForDeletion == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_ELEMENT_MESSAGE);
        }

        nodeForDeletion = checkNodeForDeletionIfItHasTwoChildren(nodeForDeletion);
        deleteNode(nodeForDeletion);
        //TODO: recolor nodes
    }

    @Override
    public void delete(int position) {
        TreeNode<T> nodeForDeletion = getNodeAtPosition(position);

        nodeForDeletion = checkNodeForDeletionIfItHasTwoChildren(nodeForDeletion);
        deleteNode(nodeForDeletion);
        //TODO: recolor nodes
    }

    private void localInsertElement(TreeNode<T> parentForInsert, T element) {
        RedBlackTreeNode<T> newNode = new RedBlackTreeNode<>(element, parentForInsert, false);
        if (parentForInsert.getElement().compareTo(element) > 0) {
            parentForInsert.setLeftChild(newNode);
        } else if (parentForInsert.getElement().compareTo(element) < 0) {
            parentForInsert.setRightChild(newNode);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.EQUALS_AND_COMPARE_MISMATCH_MESSAGE);
        }
        insertBackwardPropagation(newNode);
        incrementNodeSizes(parentForInsert);
    }

    private void insertBackwardPropagation(RedBlackTreeNode<T> newNode) {
        if (!newNode.hasParent()) {
            newNode.setBlack();
            return;
        }

        RedBlackTreeNode<T> parent = (RedBlackTreeNode<T>)newNode.getParent();
        if (parent.isBlack()) {
            return;
        }

        RedBlackTreeNode<T> parentParent = (RedBlackTreeNode<T>)newNode.getParent().getParent();
        if (parentParent.hasLeftChild() && parentParent.hasRightChild()) {
            RedBlackTreeNode<T> ppLeftChild = (RedBlackTreeNode<T>) parentParent.getLeftChild();
            RedBlackTreeNode<T> ppRightChild = (RedBlackTreeNode<T>) parentParent.getRightChild();
            if (ppLeftChild.isRed() && ppRightChild.isRed()) {
                parentParent.setRed();
                ppLeftChild.setBlack();
                ppRightChild.setBlack();
                insertBackwardPropagation(parentParent);
                return;
            }
        }

        boolean isRightToLeft = isLeftChild(parentParent, parent) && isRightChild(parent, newNode);
        boolean isLeftToRight = isRightChild(parentParent, parent) && isLeftChild(parent, newNode);
        if (isRightToLeft || isLeftToRight) {
            rotation(parent, newNode);
            insertBackwardPropagation(parent);
            return;
        }

        rotation(parentParent, parent);
        parentParent.setRed();
        parent.setBlack();
    }

    private void rotation(TreeNode<T> parent, TreeNode<T> child) {
        boolean rotateLeft = true;

        if (isRightChild(parent, child)) {
            rotateLeft = false;
        }

        TreeNode<T> movingChild = rotateLeft ? child.getRightChild() : child.getLeftChild();

        if (rotateLeft) {
            parent.setLeftChild(movingChild);
            child.setRightChild(parent);
        } else {
            parent.setRightChild(movingChild);
            child.setLeftChild(parent);
        }

        if (parent.hasParent()) {
            if (isLeftChild(parent.getParent(), child)) {
                parent.getParent().setLeftChild(child);
            } else if (isRightChild(parent.getParent(), child)) {
                parent.getParent().setRightChild(child);
            } else {
                throw new IllegalArgumentException(ExceptionMessages.EQUALS_AND_COMPARE_MISMATCH_MESSAGE);
            }
        }

        child.setParent(parent.getParent());
        parent.setParent(child);

        if (movingChild != null) {
            movingChild.setParent(parent);
        }
    }

    boolean isLeftChild(TreeNode<T> parent, TreeNode<T> child) {
        return parent.getElement().compareTo(child.getElement()) > 0;
    }

    boolean isRightChild(TreeNode<T> parent, TreeNode<T> child) {
        return parent.getElement().compareTo(child.getElement()) < 0;
    }
}
