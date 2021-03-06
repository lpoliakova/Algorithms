package structures.tree;

public abstract class AbstractBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private TreeNode<T> root = null;

    TreeNode<T> getRoot() {
        return root;
    }

    void setRoot(TreeNode<T> newRoot) {
        root = newRoot;
    }

    void deleteRoot() {
        root = null;
    }

    @Override
    public boolean contains(T element) {
        return (getElementFromTree(element) != null);
    }

    @Override
    public T getElementFromTree(T element) {
        TreeNode<T> elementNode = getNodeForElement(element);

        if (elementNode == null) {
            return null;
        }

        return elementNode.getElement();
    }

    @Override
    public T getElementAtPosition(int position) {
        return getNodeAtPosition(position).getElement();
    }

    @Override
    public int getPositionOfElement(T element) {
        TreeNode<T> elementNode = getNodeForElement(element);

        if (elementNode == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_ELEMENT_MESSAGE);
        }

        int position = 0;

        if (elementNode.hasLeftChild()) {
            position += elementNode.getLeftChild().getNodeSize();
        }

        TreeNode<T> currentNode = elementNode;

        while (currentNode.hasParent()) {
            if (currentNode.getParent().getElement().compareTo(currentNode.getElement()) < 0) {
                if (currentNode.getParent().hasLeftChild()) {
                    position += currentNode.getParent().getLeftChild().getNodeSize();
                }
                position++;
            }

            currentNode = currentNode.getParent();
        }

        return position;
    }

    @Override
    public T getMin() {
        if (root == null) {
            throw new IllegalStateException(ExceptionMessages.EMPTY_TREE_MESSAGE);
        }

        return getMinOfSubtree(root);
    }

    @Override
    public T getMax() {
        if (root == null) {
            throw new IllegalStateException(ExceptionMessages.EMPTY_TREE_MESSAGE);
        }

        return getMaxOfSubtree(root);
    }

    @Override
    public T getPredecessor(T element) {
        TreeNode<T> elementNode = getNodeForElement(element);

        if (elementNode == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_ELEMENT_MESSAGE);
        }

        if (elementNode.hasLeftChild()) {
            return getMaxOfSubtree(elementNode.getLeftChild());
        }

        TreeNode<T> currentNode = elementNode;

        while (currentNode.hasParent()) {
            if (currentNode.getParent().getElement().compareTo(elementNode.getElement()) < 0) {
                return currentNode.getParent().getElement();
            }

            currentNode = currentNode.getParent();
        }

        return null;
    }

    @Override
    public T getSuccessor(T element) {
        TreeNode<T> elementNode = getNodeForElement(element);

        if (elementNode == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_ELEMENT_MESSAGE);
        }

        if (elementNode.hasRightChild()) {
            return getMinOfSubtree(elementNode.getRightChild());
        }

        TreeNode<T> currentNode = elementNode;

        while (currentNode.hasParent()) {
            if (currentNode.getParent().getElement().compareTo(elementNode.getElement()) > 0) {
                return currentNode.getParent().getElement();
            }

            currentNode = currentNode.getParent();
        }

        return null;
    }

    @Override
    public String print(boolean isAscending) {
        StringBuilder builder = new StringBuilder();

        builder.append("[");

        subtreeToString(root, isAscending, builder);

        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");

        return builder.toString();
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }

        return root.getNodeSize();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    TreeNode<T> findInsertionNode(T element) {
        TreeNode<T> currentNode = getRoot();

        while(currentNode != null) {
            if (currentNode.getElement().equals(element)) {
                throw new IllegalArgumentException(ExceptionMessages.ALREADY_IN_TREE_MESSAGE);
            }

            if (currentNode.getElement().compareTo(element) > 0) {
                if (currentNode.hasLeftChild()) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    return currentNode;
                }
            } else if (currentNode.getElement().compareTo(element) < 0) {
                if (currentNode.hasRightChild()) {
                    currentNode = currentNode.getRightChild();
                } else {
                    return currentNode;
                }
            } else {
                throw new IllegalArgumentException(ExceptionMessages.EQUALS_AND_COMPARE_MISMATCH_MESSAGE);
            }
        }

        return null;
    }

    TreeNode<T> checkNodeForDeletionIfItHasTwoChildren(TreeNode<T> nodeForDeletion) {
        if (nodeForDeletion.hasLeftChild() && nodeForDeletion.hasRightChild()) {
            TreeNode<T> minNode = nodeForDeletion.getRightChild();

            while(minNode.hasLeftChild()) {
                minNode = minNode.getLeftChild();
            }

            nodeForDeletion.setElement(minNode.getElement());

            return minNode;
        }

        return nodeForDeletion;
    }

    void deleteNode(TreeNode<T> nodeForDeletion) {
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
    
    void incrementNodeSizes(TreeNode<T> startNode) {
        TreeNode<T> currentNode = startNode;

        while (currentNode != null) {
            currentNode.incrementNodeSize();
            currentNode = currentNode.getParent();
        }
    }

    void decrementNodeSizes(TreeNode<T> startNode) {
        TreeNode<T> currentNode = startNode;

        while (currentNode != null) {
            currentNode.decrementNodeSize();
            currentNode = currentNode.getParent();
        }
    }

    TreeNode<T> getNodeForElement(T element) {
        if (root == null) {
            return null;
        }

        TreeNode<T> currentNode = root;

        while(currentNode != null) {
            if (currentNode.getElement().equals(element)) {
                return currentNode;
            }

            if (currentNode.getElement().compareTo(element) > 0) {
                currentNode = currentNode.getLeftChild();
            } else if (currentNode.getElement().compareTo(element) < 0) {
                currentNode = currentNode.getRightChild();
            } else {
                throw new IllegalArgumentException(ExceptionMessages.EQUALS_AND_COMPARE_MISMATCH_MESSAGE);
            }
        }

        return null;
    }

    TreeNode<T> getNodeAtPosition(int position) {
        if (root == null) {
            throw new IllegalStateException(ExceptionMessages.EMPTY_TREE_MESSAGE);
        }

        if (position < 0 || position >= root.getNodeSize()) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_POSITION_MESSAGE);
        }

        TreeNode<T> currentNode = root;
        int currentNodePosition = position;

        while (true) {
            if (!currentNode.hasLeftChild() && currentNodePosition == 0) {
                return currentNode;
            } else if (!currentNode.hasLeftChild()) {
                currentNode = currentNode.getRightChild();
                currentNodePosition--;
            } else if (currentNode.getLeftChild().getNodeSize() > currentNodePosition) {
                currentNode = currentNode.getLeftChild();
            } else if (currentNode.getLeftChild().getNodeSize() == currentNodePosition) {
                return currentNode;
            } else {
                currentNodePosition = currentNodePosition - currentNode.getLeftChild().getNodeSize() - 1;
                currentNode = currentNode.getRightChild();
            }
        }
    }

    private T getMinOfSubtree(TreeNode<T> subtreeRoot) {
        TreeNode<T> currentNode = subtreeRoot;

        while(currentNode.hasLeftChild()) {
            currentNode = currentNode.getLeftChild();
        }

        return currentNode.getElement();
    }

    private T getMaxOfSubtree(TreeNode<T> subtreeRoot) {
        TreeNode<T> currentNode = subtreeRoot;

        while(currentNode.hasRightChild()) {
            currentNode = currentNode.getRightChild();
        }

        return currentNode.getElement();
    }

    private void subtreeToString(TreeNode<T> subtreeRoot, boolean isAscending, StringBuilder string) {
        if (subtreeRoot != null) {
            subtreeToString(isAscending ? subtreeRoot.getLeftChild() : subtreeRoot.getRightChild(), isAscending, string);

            string.append(subtreeRoot.getElement());
            string.append(", ");

            subtreeToString(isAscending ? subtreeRoot.getRightChild() : subtreeRoot.getLeftChild(), isAscending, string);
        }
    }
}
