package structures.tree;

public class RedBlackTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {

    @Override
    public void insert(T element) {
        if (isEmpty()) {
            setRoot(new RedBlackTreeNode<T>(element));
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
        deleteBackwardPropagation((RedBlackTreeNode<T>)nodeForDeletion);
    }

    @Override
    public void delete(int position) {
        TreeNode<T> nodeForDeletion = getNodeAtPosition(position);

        nodeForDeletion = checkNodeForDeletionIfItHasTwoChildren(nodeForDeletion);
        deleteNode(nodeForDeletion);
        deleteBackwardPropagation((RedBlackTreeNode<T>)nodeForDeletion);
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

    private void deleteBackwardPropagation(RedBlackTreeNode<T> deletedNode) {
        if (deletedNode.isRed()) {
            return;
        }

        // deletedNode is black
        RedBlackTreeNode<T> childOfDeletedNode = null;
        if (deletedNode.hasRightChild()) {
            childOfDeletedNode = (RedBlackTreeNode<T>)deletedNode.getRightChild();
        } else if (deletedNode.hasLeftChild()) {
            childOfDeletedNode = (RedBlackTreeNode<T>)deletedNode.getLeftChild();
        }
        if (childOfDeletedNode != null && childOfDeletedNode.isRed()) {
            childOfDeletedNode.setBlack();
            return;
        }

        // childOfDeletedNode is double black
        if (deletedNode.hasParent()) {
            doubleBlackPropagation((RedBlackTreeNode<T>) deletedNode.getParent(), isLeftChild(deletedNode.getParent(), deletedNode));
        }
    }

    private void doubleBlackPropagation(RedBlackTreeNode<T> parent, boolean doubleBlackIsLeft) {
        RedBlackTreeNode<T> sibling;
        if (doubleBlackIsLeft) {
            sibling = (RedBlackTreeNode<T>)parent.getRightChild();
        } else {
            sibling = (RedBlackTreeNode<T>)parent.getLeftChild();
        }

        if (sibling.isRed()) {
            rotation(parent, sibling);
            sibling.setBlack();
            parent.setRed();
            doubleBlackPropagation(parent, doubleBlackIsLeft);
            return;
        }

        // sibling is black
        RedBlackTreeNode<T> leftChildOfSibling = (RedBlackTreeNode<T>)sibling.getLeftChild();
        RedBlackTreeNode<T> rightChildOfSibling = (RedBlackTreeNode<T>)sibling.getRightChild();
        if ((leftChildOfSibling == null || leftChildOfSibling.isBlack())
                && (rightChildOfSibling == null || rightChildOfSibling.isBlack())) {
            sibling.setRed();
            if (parent.isRed()) {
                parent.setBlack();
            } else {
                if (parent.hasParent()) {
                    doubleBlackPropagation((RedBlackTreeNode<T>) parent.getParent(), isLeftChild(parent.getParent(), parent));
                }
            }
            return;
        }

        // at least one child of sibling is red
        boolean isLeftToRight = doubleBlackIsLeft && (rightChildOfSibling == null || rightChildOfSibling.isBlack());
        boolean isRightToLeft = !doubleBlackIsLeft && (leftChildOfSibling == null || leftChildOfSibling.isBlack());
        if (isLeftToRight || isRightToLeft) {
            RedBlackTreeNode<T> rotatingChild = doubleBlackIsLeft ? leftChildOfSibling : rightChildOfSibling;
            rotation(sibling, rotatingChild);
            sibling.setRed();
            rotatingChild.setBlack();
            doubleBlackPropagation(parent, doubleBlackIsLeft);
            return;
        }

        // nearest child of sibling is null or black, farthest is red
        rotation(parent, sibling);
        if (parent.isBlack()) {
            if (doubleBlackIsLeft) {
                rightChildOfSibling.setBlack();
            } else {
                leftChildOfSibling.setBlack();
            }
        }
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
        } else {
            setRoot(child);
        }

        child.setParent(parent.getParent());
        parent.setParent(child);

        if (movingChild != null) {
            movingChild.setParent(parent);
        }
    }

    private boolean isLeftChild(TreeNode<T> parent, TreeNode<T> child) {
        return parent.getElement().compareTo(child.getElement()) > 0;
    }

    private boolean isRightChild(TreeNode<T> parent, TreeNode<T> child) {
        return parent.getElement().compareTo(child.getElement()) < 0;
    }
}
