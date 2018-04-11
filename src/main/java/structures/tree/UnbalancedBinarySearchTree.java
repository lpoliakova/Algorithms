package structures.tree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UnbalancedBinarySearchTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {
    @Override
    public void insert(T element) {
        if (isEmpty()) {
            initRoot(element);
            return;
        }

        TreeNode<T> currentNode = getRoot();

        while(currentNode != null) {
            if (currentNode.getElement().equals(element)) {
                throw new IllegalArgumentException(ExceptionMessages.ALREADY_IN_TREE_MESSAGE);
            }

            if (currentNode.getElement().compareTo(element) > 0) {
                if (currentNode.hasLeftChild()) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    localInsertElement(currentNode, element, true);
                    return;
                }
            } else if (currentNode.getElement().compareTo(element) < 0) {
                if (currentNode.hasRightChild()) {
                    currentNode = currentNode.getRightChild();
                } else {
                    localInsertElement(currentNode, element, false);
                    return;
                }
            } else {
                throw new IllegalArgumentException(ExceptionMessages.EQUALS_AND_COMPARE_MISMATCH_MESSAGE);
            }
        }
    }

    @Override
    public void delete(T element) {
        TreeNode<T> nodeForDeletion = getNodeForElement(element);

        if (nodeForDeletion == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_ELEMENT_MESSAGE);
        }

        deleteNode(nodeForDeletion);
    }

    @Override
    public void delete(int position) {
        TreeNode<T> nodeForDeletion = getNodeAtPosition(position);

        deleteNode(nodeForDeletion);
    }

    private void localInsertElement(TreeNode<T> currentNode, T element, boolean insertLeft) {
        if (insertLeft) {
            currentNode.setLeftChild(new TreeNode<>(element, currentNode));
        } else {
            currentNode.setRightChild(new TreeNode<>(element, currentNode));
        }
        incrementNodeSizes(currentNode);
    }

    private void incrementNodeSizes(TreeNode<T> startNode) {
        TreeNode<T> currentNode = startNode;

        while (currentNode != null) {
            currentNode.incrementNodeSize();
            currentNode = currentNode.getParent();
        }
    }

    private void deleteNode(TreeNode<T> nodeForDeletion) {
        if (nodeForDeletion.hasLeftChild() && nodeForDeletion.hasRightChild()) {
            TreeNode<T> minNode = nodeForDeletion.getRightChild();

            while(minNode.hasLeftChild()) {
                minNode = minNode.getLeftChild();
            }

            nodeForDeletion.setElement(minNode.getElement());

            nodeForDeletion = minNode;
        }

        if (!nodeForDeletion.hasLeftChild() && !nodeForDeletion.hasRightChild()) {
            if (!nodeForDeletion.hasParent()) {
                deleteRoot();
                return;
            }

            replaceForParent(nodeForDeletion, null);
        } else if (nodeForDeletion.hasLeftChild()) {
            nodeForDeletion.getLeftChild().setParent(nodeForDeletion.getParent());
            replaceForParent(nodeForDeletion, nodeForDeletion.getLeftChild());
        } else if (nodeForDeletion.hasRightChild()) {
            nodeForDeletion.getRightChild().setParent(nodeForDeletion.getParent());
            replaceForParent(nodeForDeletion, nodeForDeletion.getRightChild());
        }
    }

    private void replaceForParent(TreeNode<T> nodeForDeletion, TreeNode<T> movingChild) {
        if (nodeForDeletion.getParent().getElement().compareTo(nodeForDeletion.getElement()) < 0) {
            nodeForDeletion.getParent().setRightChild(movingChild);
        } else if (nodeForDeletion.getParent().getElement().compareTo(nodeForDeletion.getElement()) > 0) {
            nodeForDeletion.getParent().setLeftChild(movingChild);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.EQUALS_AND_COMPARE_MISMATCH_MESSAGE);
        }
        decrementNodeSizes(nodeForDeletion.getParent());
    }

    private void decrementNodeSizes(TreeNode<T> startNode) {
        TreeNode<T> currentNode = startNode;

        while (currentNode != null) {
            currentNode.decrementNodeSize();
            currentNode = currentNode.getParent();
        }
    }
}
